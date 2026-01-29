package com.example.demo.controller;
import com.example.demo.entity.Artist;

import com.example.demo.entity.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate; // <-- import novo
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.ArtistRepository;
import com.example.demo.service.MinioService;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final MinioService minioService;
    private final SimpMessagingTemplate messagingTemplate; // <-- novo campo

    public AlbumController(AlbumRepository albumRepository,
                           ArtistRepository artistRepository,
                           MinioService minioService,
                           SimpMessagingTemplate messagingTemplate) { // <-- injeta aqui
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.minioService = minioService;
        this.messagingTemplate = messagingTemplate;
    }

    // GET com paginação
    @GetMapping
    public Page<Album> getAlbums(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return albumRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - criar álbum
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album saved = albumRepository.save(album);

        // 🔔 Notifica o front pelo WebSocket
        messagingTemplate.convertAndSend("/topic/newAlbum", saved);

        return ResponseEntity.ok(saved);
    }

    // PUT - atualizar álbum
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        if (!albumRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        album.setId(id);
        Album updated = albumRepository.save(album);
        return ResponseEntity.ok(updated);
    }

    // DELETE - remover álbum
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        if (!albumRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        albumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Upload de uma única capa
    @PostMapping(value = "/{id}/cover", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadCover(@PathVariable Long id,
                                              @RequestParam("file") MultipartFile file) {
        try {
            String fileName = "album-" + id + "-" + file.getOriginalFilename();
            minioService.uploadFile(fileName, file.getBytes());
            URL url = minioService.generatePresignedUrl(fileName);
            return ResponseEntity.ok(url.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload: " + e.getMessage());
        }
    }

    // Upload de múltiplas capas
    @PostMapping(value = "/{id}/covers", consumes = "multipart/form-data")
    public ResponseEntity<List<String>> uploadMultipleCovers(@PathVariable Long id,
                                                             @RequestParam("files") List<MultipartFile> files) {
        try {
            List<String> urls = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = "album-" + id + "-" + file.getOriginalFilename();
                minioService.uploadFile(fileName, file.getBytes());
                URL url = minioService.generatePresignedUrl(fileName);
                urls.add(url.toString());
            }
            return ResponseEntity.ok(urls);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(List.of("Erro ao fazer upload: " + e.getMessage()));
        }
    }

    // Associar artista a álbum
    @PutMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Album> addArtistToAlbum(@PathVariable Long albumId,
                                                  @PathVariable Long artistId) {
        Optional<Album> albumOpt = albumRepository.findById(albumId);
        Optional<Artist> artistOpt = artistRepository.findById(artistId);

        if (albumOpt.isEmpty() || artistOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Album album = albumOpt.get();
        Artist artist = artistOpt.get();

        album.getArtists().add(artist);
        Album updated = albumRepository.save(album);

        return ResponseEntity.ok(updated);
    }

    // Buscar álbuns por nome de artista
    @GetMapping("/searchByArtist")
    public List<Album> getAlbumsByArtistName(@RequestParam String name) {
        return albumRepository.findByArtists_NameIgnoreCase(name);
    }

    // Buscar álbuns por nome de artista com ordenação asc/desc
    @GetMapping("/searchByArtistOrdered")
    public List<Album> getAlbumsByArtistNameOrdered(@RequestParam String name,
                                                    @RequestParam(defaultValue = "asc") String order) {
        Sort sort = order.equalsIgnoreCase("desc") ?
                Sort.by("title").descending() :
                Sort.by("title").ascending();

        return albumRepository.findByArtists_NameIgnoreCase(name, sort);
    }

    // Filtrar álbuns por tipo de artista
    @GetMapping("/filter")
    public List<Album> getAlbumsByArtistType(@RequestParam String type) {
        return albumRepository.findAlbumsByArtistType(type);
    }
}

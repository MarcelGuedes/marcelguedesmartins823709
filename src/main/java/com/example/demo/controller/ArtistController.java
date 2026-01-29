package com.example.demo.controller;
import com.example.demo.entity.Album;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Artist;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

    private final ArtistRepository artistRepository;

    // Injeção via construtor (boa prática)
    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // GET com ordenação
    @GetMapping
    public List<Artist> getArtists(@RequestParam(defaultValue = "asc") String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return artistRepository.findAll(Sort.by(direction, "name"));
    }


    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
@GetMapping("/{id}/albums")
public ResponseEntity<List<Album>> getAlbumsByArtist(@PathVariable Long id) {
    Optional<Artist> artist = artistRepository.findById(id);
    if (artist.isPresent()) {
        List<Album> albums = new ArrayList<>(artist.get().getAlbums());
        return ResponseEntity.ok(albums);
    } else {
        return ResponseEntity.notFound().build();
    }
}


    // POST - criar artista
    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        Artist saved = artistRepository.save(artist);
        return ResponseEntity.ok(saved);
    }

    // PUT - atualizar artista
    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        if (!artistRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artist.setId(id);
        Artist updated = artistRepository.save(artist);
        return ResponseEntity.ok(updated);
    }

    // DELETE - remover artista
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        if (!artistRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

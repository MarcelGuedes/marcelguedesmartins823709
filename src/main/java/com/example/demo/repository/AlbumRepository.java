package com.example.demo.repository;

import com.example.demo.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    // Busca álbuns por nome de artista (case-insensitive)
    List<Album> findByArtists_NameIgnoreCase(String name);

    // Busca álbuns por nome de artista com ordenação (asc/desc)
    List<Album> findByArtists_NameIgnoreCase(String name, Sort sort);

    // Busca álbuns por tipo de artista (singer ou band)
    @Query("SELECT DISTINCT a FROM Album a JOIN a.artists ar WHERE ar.type = :type")
    List<Album> findAlbumsByArtistType(@Param("type") String type);
}

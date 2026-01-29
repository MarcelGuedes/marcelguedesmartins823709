package com.example.demo.dto;


public class ArtistDTO {
    private Long id;
    private String name;

    public ArtistDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}

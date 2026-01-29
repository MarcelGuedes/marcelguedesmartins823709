package com.example.demo.dto;


public class AlbumDTO {
    private Long id;
    private String title;

    public AlbumDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
}

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "regionais")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // banco gera o ID automaticamente
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo = true; // valor padrão

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}

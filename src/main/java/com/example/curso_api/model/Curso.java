package com.example.curso_api.model;

import com.example.curso_api.controller.CursoController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private int cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference
    private Professor professor;

    public CursoController getNome() {
    }

    // Getters and Setters
}

package com.example.curso_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.curso_api.model.Professor;
import com.example.curso_api.service.ProfessorService;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listarProfessores() {
        return professorService.listarTodosProfessores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessorPorId(@PathVariable Long id) {
        return professorService.buscarProfessorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public Professor criarProfessor(@RequestBody Professor professor) {
        return professorService.salvarProfessor(professor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professorDetalhes) {
        return professorService.buscarProfessorPorId(id).map(professor -> {
            professor.setNome(professorDetalhes.getNome());
            professor.setEspecialidade(professorDetalhes.getEspecialidade());
            Professor professorAtualizado = professorService.salvarProfessor(professor);
            return ResponseEntity.ok(professorAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProfessor(@PathVariable Long id) {
        return professorService.buscarProfessorPorId(id).map(professor -> {
            professorService.deletarProfessor(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Professor> buscarProfessorPorEspecialidade(@RequestParam String especialidade) {
        return professorService.buscarProfessorPorEspecialidade(especialidade);
    }
}

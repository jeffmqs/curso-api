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

import com.example.curso_api.model.Aluno;
import com.example.curso_api.service.AlunoService;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoService.listarTodosAlunos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        return alunoService.salvarAluno(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoDetalhes) {
        return alunoService.buscarAlunoPorId(id).map(aluno -> {
            aluno.setNome(alunoDetalhes.getNome());
            aluno.setMatricula(alunoDetalhes.getMatricula());
            aluno.setCurso(alunoDetalhes.getCurso());
            Aluno alunoAtualizado = alunoService.salvarAluno(aluno);
            return ResponseEntity.ok(alunoAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAluno(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id).map(aluno -> {
            alunoService.deletarAluno(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Aluno> buscarAlunoPorNome(@RequestParam String nome) {
        return alunoService.buscarAlunoPorNome(nome);
    }
}

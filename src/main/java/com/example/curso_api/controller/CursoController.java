package com.example.curso_api.controller;

import com.example.curso_api.model.Curso;
import com.example.curso_api.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarTodosCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvarCurso(curso);
        return ResponseEntity.ok(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody Curso cursoDetalhes) {
        return cursoService.buscarCursoPorId(id).map(curso -> {
            curso.setNome(cursoDetalhes.getNome());
            curso.setDescricao(cursoDetalhes.getDescricao());
            curso.setCargaHoraria(cursoDetalhes.getCargaHoraria());
            Curso cursoAtualizado = cursoService.salvarCurso(curso);
            return ResponseEntity.ok(cursoAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCurso(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id).map(curso -> {
            cursoService.deletarCurso(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Curso> buscarCursoPorNome(@RequestParam String nome) {
        return cursoService.buscarCursoPorNome(nome);
    }
}

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

import com.example.curso_api.model.Categoria;
import com.example.curso_api.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarTodasCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.salvarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetalhes) {
        return categoriaService.buscarCategoriaPorId(id).map(categoria -> {
            categoria.setNome(categoriaDetalhes.getNome());
            Categoria categoriaAtualizada = categoriaService.salvarCategoria(categoria);
            return ResponseEntity.ok(categoriaAtualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id).map(categoria -> {
            categoriaService.deletarCategoria(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Categoria> buscarCategoriaPorNome(@RequestParam String nome) {
        return categoriaService.buscarCategoriaPorNome(nome);
    }
}

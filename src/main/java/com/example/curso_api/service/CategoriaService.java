package com.example.curso_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso_api.model.Categoria;
import com.example.curso_api.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Transactional
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Transactional
    public List<Categoria> buscarCategoriaPorNome(String nome) {
        return categoriaRepository.findByNomeContaining(nome);
    }
}

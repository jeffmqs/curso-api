package com.example.curso_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso_api.model.Curso;
import com.example.curso_api.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    @Transactional
    public Optional<Curso> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional
    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public void deletarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    @Transactional
    public List<Curso> buscarCursoPorNome(String nome) {
        return cursoRepository.findByNomeContaining(nome);
    }
}

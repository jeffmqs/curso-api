package com.example.curso_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso_api.model.Aluno;
import com.example.curso_api.repository.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    @Transactional
    public Optional<Aluno> buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }

    @Transactional
    public List<Aluno> buscarAlunoPorNome(String nome) {
        return alunoRepository.findByNomeContaining(nome);
    }
}

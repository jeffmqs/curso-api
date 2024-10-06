package com.example.curso_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso_api.model.Professor;
import com.example.curso_api.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    @Transactional
    public Optional<Professor> buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    @Transactional
    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public void deletarProfessor(Long id) {
        professorRepository.deleteById(id);
    }

    @Transactional
    public List<Professor> buscarProfessorPorEspecialidade(String especialidade) {
        return professorRepository.findByEspecialidadeContaining(especialidade);
    }
}

package com.example.curso_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso_api.model.Curso;
import com.example.curso_api.model.Professor;
import com.example.curso_api.repository.ProfessorRepository;
import com.example.curso_api.repository.CursoRepository;
import com.example.curso_api.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

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
    Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado"));
    List<Curso> cursos = cursoRepository.findByProfessorId(professor.getId());
    for (Curso curso : cursos) {
        curso.setProfessor(null);
        cursoRepository.save(curso);
    }
    
    professorRepository.delete(professor);
}


    @Transactional
    public List<Professor> buscarProfessorPorEspecialidade(String especialidade) {
        return professorRepository.findByEspecialidadeContaining(especialidade);
    }
}

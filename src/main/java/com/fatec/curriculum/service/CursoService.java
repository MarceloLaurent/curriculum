package com.fatec.curriculum.service;

import com.fatec.curriculum.model.Curso;
import com.fatec.curriculum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> getById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso updateCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }

}

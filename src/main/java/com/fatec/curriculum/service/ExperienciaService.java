package com.fatec.curriculum.service;

import com.fatec.curriculum.model.Experiencia;
import com.fatec.curriculum.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienciaService {

    ExperienciaRepository experienciaRepository;

    @Autowired
    public ExperienciaService(ExperienciaRepository experienciaRepository) {
        this.experienciaRepository = experienciaRepository;
    }

    public Experiencia saveExperiencia(Experiencia experiencia) {
        return experienciaRepository.save(experiencia);
    }

    public List<Experiencia> findAll() {
        return experienciaRepository.findAll();
    }

    public Optional<Experiencia> getById(Long id) {
        return experienciaRepository.findById(id);
    }

    public Experiencia updateExperiencia(Experiencia experiencia) {
        return experienciaRepository.save(experiencia);
    }

    public void deleteExperiencia(Long id) {
        experienciaRepository.deleteById(id);
    }

}

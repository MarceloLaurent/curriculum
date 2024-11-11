package com.fatec.curriculum.repository;

import com.fatec.curriculum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository <Curso, Long > {
}

package com.fatec.curriculum.controller;

import com.fatec.curriculum.model.Curso;
import com.fatec.curriculum.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @Operation(summary = "Adiciona um curso", method = "POST")
    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) throws Exception {
        return cursoService.saveCurso(curso);
    }

    @Operation(summary = "Retorna uma lista com todos os cursos", method = "GET")
    @GetMapping
    public List<Curso> getCursoLis() {
        return cursoService.findAll();
    }

    @Operation(summary = "Retorna um curso específico", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(cursoService.getById(id).orElseThrow(() -> new Exception("Curso não encontrado")));
    }

    @Operation(summary = "Atualiza os dados de um curso", method = "PUT")
    @PutMapping
    public Curso updateCurso(@RequestBody Curso curso) throws Exception {
        return cursoService.updateCurso(curso);
    }

    @Operation(summary = "Deleta um curso específico", method = "DELETE")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        try {
            cursoService.deleteCurso(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Objects.requireNonNull(HttpStatus.NOT_FOUND);
        }
    }

}

package com.fatec.curriculum.controller;

import com.fatec.curriculum.model.Experiencia;
import com.fatec.curriculum.service.ExperienciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;

    @Operation(summary = "Adiciona uma experiencia", method = "POST")
    @PostMapping
    public Experiencia createExperiencia(@RequestBody Experiencia experiencia) throws Exception {
        return experienciaService.saveExperiencia(experiencia);
    }

    @Operation(summary = "Retorna uma lista com todas as experiencias", method = "GET")
    @GetMapping
    public List<Experiencia> getExperienciaList() {
        return experienciaService.findAll();
    }

    @Operation(summary = "Retorna uma experiencia específica", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> getExperienciaById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(experienciaService.getById(id).orElseThrow(() -> new Exception("Experiencia não encontrada")));
    }

    @Operation(summary = "Atualiza os dados de uma experiencia", method = "PUT")
    @PutMapping
    public Experiencia updateExperiencia(@RequestBody Experiencia experiencia) throws Exception {
        return experienciaService.updateExperiencia(experiencia);
    }


    @Operation(summary = "Deleta uma experiencia específica", method = "DELETE")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        try {
            experienciaService.deleteExperiencia(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Objects.requireNonNull(HttpStatus.NOT_FOUND);
        }
    }

}

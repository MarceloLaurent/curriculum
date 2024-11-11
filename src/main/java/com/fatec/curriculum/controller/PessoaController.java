package com.fatec.curriculum.controller;

import com.fatec.curriculum.model.Pessoa;
import com.fatec.curriculum.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Operation(summary = "Adiciona uma pessoa", method = "POST")
    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) throws Exception {
        return pessoaService.savePessoa(pessoa);
    }

    @Operation(summary = "Retorna uma lista com todas as pessoas", method = "GET")
    @GetMapping
    public List<Pessoa> getPessoaList() {
        return pessoaService.findAll();
    }

    @Operation(summary = "Retorna uma pessoa específica", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(pessoaService.getById(id).orElseThrow(() -> new Exception("Pessoa não encontrada")));
    }

    @Operation(summary = "Atualiza os dados de uma pessoa", method = "PUT")
    @PutMapping
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa) throws Exception {
        return pessoaService.updatePessoa(pessoa);
    }

    @Operation(summary = "Deleta uma pessoa específica", method = "DELETE")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        try {
            pessoaService.deletePessoa(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Objects.requireNonNull(HttpStatus.NOT_FOUND);
        }
    }

}

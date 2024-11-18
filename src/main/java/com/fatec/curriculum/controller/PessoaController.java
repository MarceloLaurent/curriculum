package com.fatec.curriculum.controller;

import com.fatec.curriculum.model.Pessoa;
import com.fatec.curriculum.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Operation(summary = "Adiciona uma pessoa", method = "POST")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Pessoa> createPessoaComFoto(
            @RequestParam("nome") String nome,
            @RequestParam("contato") String contato,
            @RequestParam(value = "endereco", required = false) String endereco,
            @RequestParam("dataNascimento") String dataNascimento,
            @RequestParam(value = "linkedin", required = false) String linkedin,
            @RequestParam(value = "github", required = false) String github,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {

        try {
            // Criando a entidade Pessoa
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setContato(contato);
            pessoa.setEndereco(endereco);
            pessoa.setDataNascimento(dataNascimento);
            pessoa.setLinkedin(linkedin);
            pessoa.setGithub(github);
            pessoa.setEmail(email);
            pessoa.setSenha(senha);

            if (foto != null && !foto.isEmpty()) {
                pessoa.setFoto(foto.getBytes());
            }

            // Salva a pessoa via serviço
            Pessoa pessoaSalva = pessoaService.savePessoa(pessoa);

            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

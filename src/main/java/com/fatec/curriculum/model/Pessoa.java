package com.fatec.curriculum.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String contato;
    private String dataNascimento;
    private String linkedin;
    private String Github;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Experiencia> experiencias;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Curso> cursos;

}

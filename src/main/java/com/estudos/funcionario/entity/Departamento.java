package com.estudos.funcionario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Departamento é obrigatorio")
    private String nome;

    //@OneToMany(mappedBy = "departamento")
    @OneToMany(mappedBy = "departamento")
    @JsonIgnore
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Departamento() {
    }

    public Departamento(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Funcionario> getFuncionarios() {
        return this.funcionarios;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
}

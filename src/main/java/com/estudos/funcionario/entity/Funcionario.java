package com.estudos.funcionario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.Date;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Funcionario é obrigatorio")
    private String nome;

    @Positive(message = "O salario precisa ser maior que zero")
    private double salario;

    private Date dataAdmissao;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public Funcionario() {
    }

    public Funcionario(String nome, double salario, Date dataAdmissao) {
        this.nome = nome;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
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

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getDataAdmissao() {
        return this.dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        String nomeDepartamento = (this.departamento != null) ? this.departamento.getNome() : "sem Departamento";
        return this.id + " - " + this.nome + " - Departamento: " + nomeDepartamento;
    }
}

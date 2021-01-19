package com.spring.models;


import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.Period;

public class Cliente {

    @Id
    private String id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Integer idade;

    public Integer calcIdade(LocalDate dataNascimento) {
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        return periodo.getYears();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}

package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;

@Entity
public class Categoria extends AbstractEntity{
    @Column
    @NotBlank
    private String nome;

    public Categoria(@NotBlank String nomeCategoria){
        this.nome = nomeCategoria;
    }

    public Categoria(){}


    public String getNomeCategoria(){
        return this.nome;
    }

    public void setNomeCategoria(String getNomeCategoria){
        this.nome = nome;
    }
}

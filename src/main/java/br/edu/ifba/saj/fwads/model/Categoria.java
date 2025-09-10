package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jakarta.persistence.Entity;

@Entity
public class Categoria extends AbstractEntity{
    @Column
    @NotBlank
    private String nomeCategoria;

    public Categoria(String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria(){
        return this.nomeCategoria;
    }

    public void setNomeCategoria(String getNomeCategoria){
        this.nomeCategoria = nomeCategoria;
    }
}

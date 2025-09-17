package br.edu.ifba.saj.fwads.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor extends AbstractEntity {
    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;

    @Column
    @NotNull
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor")
    @NotNull
    private List<Livro> livrosDisponiveis = new ArrayList<>();

    public Autor(
            @NotBlank @Size(min = 5) String nome,
            @NotNull LocalDate dataNascimento,
            List<Livro> livrosDisponiveis
    ) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.livrosDisponiveis = livrosDisponiveis != null ? livrosDisponiveis : new ArrayList<>();
    }

    public Autor(){}

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public LocalDate getdataNascimento(){
        return this.dataNascimento;
    }

    public void setDataNascimento(){
        this.dataNascimento = dataNascimento;
    }

    public List<Livro> getLivrosDisponiveis(){
        return this.livrosDisponiveis;
    }

    public void setLivrosDisponiveis(List<Livro> livrosDisponiveis) {
        this.livrosDisponiveis = livrosDisponiveis;
    }
    
}

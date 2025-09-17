package br.edu.ifba.saj.fwads.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Livro extends AbstractEntity {

    @Column
    @NotBlank
    @Size(min = 5)
    private String nome;

    @Column
    @NotNull
    private LocalDate dataLancamento;

    @ManyToOne
    @NotNull
    private Autor autor;

    @Column
    @NotNull
    private int qntPaginas;

    @ManyToMany
    @NotEmpty
    private List<Categoria> categoria;

    public Livro(
            @NotBlank @Size(min = 5) String nome,
            @NotNull LocalDate dataLancamento,
            @NotNull Autor autor,
            @NotNull int qntPaginas,
            @NotEmpty List<Categoria> categoria
    ) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.autor = autor;
        this.qntPaginas = qntPaginas;
        this.categoria = categoria;
    }

    public Livro(){}


  public String getNome(){
    return this.nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public LocalDate getDataLacamento(){
    return this.dataLancamento;
  }

  public void setDataLancamento(LocalDate dataLancamento){
    this.dataLancamento = dataLancamento;
  }

  public Autor getAutor(){
    return this.autor;
  }

  public void setAutor(Autor autor){
    this.autor = autor;
  }

  public int getQntPaginas(){
    return this.qntPaginas;
  }

  public void setQntPagina(int qntPaginas){
    this.qntPaginas = qntPaginas;
  }

  public List<Categoria> getCategoria(){
    return this.categoria;
  }

  public void setCateoria(List<Categoria> categoria){
    this.categoria = categoria;
  }

}

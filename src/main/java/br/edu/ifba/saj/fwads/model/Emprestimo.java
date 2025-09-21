package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import jakarta.persistence.ManyToOne; 

@Entity
public class Emprestimo extends AbstractEntity{

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @ManyToOne
    @NotNull
    private Livro livroEmprestado;

    @Column
    @NotNull
    private LocalDate dataEmprestimo;

    @Column
    @NotNull
    private LocalDate dataDevolucao;

    @Column
    @NotBlank
    private String status;

    public Emprestimo(
            @NotNull Usuario usuario,
            @NotNull Livro livroEmprestado,
            @NotNull LocalDate dataEmprestimo,
            @NotNull LocalDate dataDevolução,
            @NotBlank String status
    ) {
        this.usuario = usuario;
        this.livroEmprestado = livroEmprestado;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolução;
        this.status = status;
    }

    public Emprestimo(){}



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public void setLivroEmprestado(Livro livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolução(LocalDate dataDevolução) {
        this.dataDevolucao = dataDevolução;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario +
                ", livroEmprestado=" + livroEmprestado +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolução=" + dataDevolucao +
                ", status='" + status + '\'' +
                '}';
    }
}








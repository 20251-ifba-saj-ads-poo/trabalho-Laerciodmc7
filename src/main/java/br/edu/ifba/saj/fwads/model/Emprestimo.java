package br.edu.ifba.saj.fwads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import jakarta.persistence.ManyToOne; 

@Entity
public class Emprestimo extends AbstractEntity{

    @Column
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @Column
    @NotNull
    private Livro livroEmprestado;
    @Column
    @NotNull
    private LocalDate dataEmprestimo;
    @Column
    @NotNull
    private LocalDate dataDevolução;
    @Column
    @NotBlank
    private String status;

    public Emprestimo(Usuario usuario, Livro livroEmprestado, LocalDate dataEmprestimo, LocalDate dataDevolução, String status) {
        this.usuario = usuario;
        this.livroEmprestado = livroEmprestado;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolução = dataDevolução;
        this.status = status;
    }

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

    public LocalDate getDataDevolução() {
        return dataDevolução;
    }

    public void setDataDevolução(LocalDate dataDevolução) {
        this.dataDevolução = dataDevolução;
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
                ", dataDevolução=" + dataDevolução +
                ", status='" + status + '\'' +
                '}';
    }
}








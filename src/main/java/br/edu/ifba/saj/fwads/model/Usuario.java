package br.edu.ifba.saj.fwads.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Usuario extends AbstractEntity {

    @Column
    @NotBlank
    @Size(min = 5)
    private String nomeCompleto;
    @Column
    @NotBlank
    @Size(min = 5)
    private String userName;
    @Column
    @NotBlank
    private String cpf;
    @Column
    @NotBlank
    private String senha;
    @Column
    @NotEmpty
    @OneToOne
    private ArrayList<Emprestimo> emprestimos;
    @Column
    @NotNull
    private int limiteEmprestimo;


    public Usuario(String nomeCompleto, String userName, String cpf, String senha, ArrayList<Emprestimo> emprestimos, int limiteEmprestimo) {
        this.nomeCompleto = nomeCompleto;
        this.userName = userName;
        this.cpf = cpf;
        this.senha = senha;
        this.emprestimos = emprestimos;
        this.limiteEmprestimo = limiteEmprestimo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public int getLimiteEmprestimo() {
        return limiteEmprestimo;
    }

    public void setLimiteEmprestimo(int limiteEmprestimo) {
        this.limiteEmprestimo = limiteEmprestimo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", userName='" + userName + '\'' +
                ", dataNascimento="  +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", emprestimos=" + emprestimos +
                ", limiteEmprestimo=" + limiteEmprestimo +
                '}';
    }
}

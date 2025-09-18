package br.edu.ifba.saj.fwads.model;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty
    private List<Emprestimo> emprestimos;

    public Usuario(
            @NotBlank @Size(min = 5) String nomeCompleto,
            @NotBlank @Size(min = 5) String userName,
            @NotBlank String cpf,
            @NotBlank String senha,
            @NotEmpty List<Emprestimo> emprestimos
    ) {
        this.nomeCompleto = nomeCompleto;
        this.userName = userName;
        this.cpf = cpf;
        this.senha = senha;
        this.emprestimos = emprestimos;
    }

    public Usuario(){

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

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public void addEmprestimo(Emprestimo emprestimo){
        //if(!estaEmprestado)
        this.emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo){
        this.emprestimos.remove(emprestimo);
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
                '}';
    }
}

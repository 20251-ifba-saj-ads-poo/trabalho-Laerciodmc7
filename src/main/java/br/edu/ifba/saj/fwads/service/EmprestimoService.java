package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.EmprestimoInvalidoException;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService extends Service<Emprestimo> {

    UsuarioService usuarioService = new UsuarioService();

    public EmprestimoService(){super(Emprestimo.class);}

    public void validaEmprestimo(Livro livro, Usuario usuario) throws Exception{

        Usuario usuarios = usuarioService.getUsuarioLogado();

        if (usuario == null) {
            throw new EmprestimoInvalidoException("Nenhum usuário logado. Por favor, faça o login novamente.");
        }

        if (livro == null) {
            throw new EmprestimoInvalidoException("Selecione um livro para emprestar.");
        }

        // Validação do limite de empréstimos
        if (usuario.getEmprestimos().size() >= 3){
            throw new EmprestimoInvalidoException("Você já atingiu seu limite de " + 3 + " empréstimos.");
        }

        // Validação se o livro já está emprestad
        if (usuario.getEmprestimos().stream().anyMatch(e -> e.getLivroEmprestado().equals(livro))) {
            throw new EmprestimoInvalidoException("Este livro já está na sua lista de empréstimos.");
        }
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setLivroEmprestado(livro);
        novoEmprestimo.setDataEmprestimo(LocalDate.now());
        novoEmprestimo.setDataDevolução(LocalDate.now().plusDays(7));
        novoEmprestimo.setStatus("Emprestado");
        usuario.addEmprestimo(novoEmprestimo);
        create(novoEmprestimo);

        new Alert(Alert.AlertType.INFORMATION,
                "Livro:"+ novoEmprestimo.getLivroEmprestado()+"Emprestado com sucesso !").showAndWait();
    }

    public void verificaDevolução(Emprestimo emprestimo, Livro livro) throws Exception{
        LocalDate hoje = LocalDate.now();
        if(hoje.isAfter(emprestimo.getDataDevolucao())){
            throw new EmprestimoInvalidoException("Devolução em atraso. Por favor devolva o livro" + livro.getNome());
        }
    }





}

package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.EmprestimoInvalidoException;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmprestimoService extends Service<Emprestimo> {

    private final UsuarioService usuarioService;
    private final Service<Livro> livroMerger;

    public EmprestimoService(UsuarioService usuarioService) {
        super(Emprestimo.class);
        this.usuarioService = usuarioService;
        this.livroMerger = new Service<>(Livro.class);
    }

    public void validaEmprestimo(Usuario usuario, Livro livro) throws EmprestimoInvalidoException {

        if (usuario == null || livro == null) {
            throw new EmprestimoInvalidoException("Usuário não logado ou livro não selecionado.");
        }

        Usuario managedUsuario = usuarioService.update(usuario);
        Livro managedLivro = livroMerger.update(livro);

        // A lógica de validação deve ser feita na lista de empréstimos:
        if (managedUsuario.getEmprestimos().size() >= 3){ // USANDO SEU LIMITE FIXO DE 3
            throw new EmprestimoInvalidoException("Você já atingiu seu limite de 3 empréstimos.");
        }

        if (managedUsuario.getEmprestimos().stream().anyMatch(e -> e.getLivroEmprestado().equals(managedLivro))) {
            throw new EmprestimoInvalidoException("Este livro já está na sua lista de empréstimos.");
        }

        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setUsuario(managedUsuario);
        novoEmprestimo.setLivroEmprestado(managedLivro);
        novoEmprestimo.setDataEmprestimo(LocalDate.now());
        novoEmprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
        novoEmprestimo.setStatus("Emprestado");

        //PERSISTÊNCIA
        // Adiciona à lista gerenciada e persiste
        managedUsuario.getEmprestimos().add(novoEmprestimo);
        create(novoEmprestimo);

        // ATUALIZAÇÃO DA SESSÃO GLOBAL
        UsuarioService.setUsuarioLogado(managedUsuario);
    }
}
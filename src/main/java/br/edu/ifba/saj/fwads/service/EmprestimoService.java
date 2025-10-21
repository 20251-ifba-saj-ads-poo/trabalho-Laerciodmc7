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

    // Construtor corrigido para injetar dependências (necessário para o merge)
    public EmprestimoService(UsuarioService usuarioService) {
        super(Emprestimo.class);
        this.usuarioService = usuarioService;
        this.livroMerger = new Service<>(Livro.class); // Usa o Service genérico para Livro
    }

    public void validaEmprestimo(Usuario usuario, Livro livro) throws EmprestimoInvalidoException {

        // 1. Validações de entrada (Checagem de Null)
        if (usuario == null || livro == null) {
            throw new EmprestimoInvalidoException("Usuário não logado ou livro não selecionado.");
        }

        // 2. CORREÇÃO CRÍTICA: RE-ANEXAR OBJETOS com MERGE/UPDATE
        // Isso resolve o erro de chave estrangeira 23506 ao reintroduzir os objetos na transação.
        Usuario managedUsuario = usuarioService.update(usuario);
        Livro managedLivro = livroMerger.update(livro);

        // 3. Validação do Limite e Duplicidade (Usando managedUsuario)
        if (managedUsuario.getEmprestimos() == null) {
            managedUsuario.setEmprestimos(new ArrayList<>());
        }
        if (managedUsuario.getEmprestimos().size() >= 3 ){
            throw new EmprestimoInvalidoException("Você já atingiu seu limite de " + 3 + " empréstimos.");
        }
        if (managedUsuario.getEmprestimos().stream().anyMatch(e -> e.getLivroEmprestado().equals(managedLivro))) {
            throw new EmprestimoInvalidoException("Este livro já está na sua lista de empréstimos.");
        }

        // 4. CRIAÇÃO E PERSISTÊNCIA
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setUsuario(managedUsuario);
        novoEmprestimo.setLivroEmprestado(managedLivro);
        novoEmprestimo.setDataEmprestimo(LocalDate.now());
        novoEmprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
        novoEmprestimo.setStatus("Emprestado");

        // 5. Persiste e atualiza o objeto gerenciado
        managedUsuario.getEmprestimos().add(novoEmprestimo);
        create(novoEmprestimo);
    }
}
package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.EmprestimoInvalidoException;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmprestimoService extends Service<Emprestimo> {

    private static final int LIMITE_EMPRESTIMOS = 3;
    private static final String STATUS_EMPRESTADO = "Emprestado";
    private static final String STATUS_DEVOLVIDO = "Devolvido";

    public EmprestimoService() {
        super(Emprestimo.class);
    }

    // Valida e cria um novo empréstimo
    public void validaEmprestimo(Usuario usuario, Livro livro)
            throws EmprestimoInvalidoException {

        if (usuario == null || usuario.getId() == null) {
            throw new EmprestimoInvalidoException("Usuário inválido.");
        }

        if (livro == null || livro.getId() == null) {
            throw new EmprestimoInvalidoException("Livro inválido.");
        }

        List<Emprestimo> emprestimosAtivos = buscarEmprestimosAtivosPorUsuario(usuario);

        if (emprestimosAtivos.size() >= LIMITE_EMPRESTIMOS) {
            throw new EmprestimoInvalidoException(
                "Você já atingiu o limite de " + LIMITE_EMPRESTIMOS + " empréstimos."
            );
        }

        boolean livroJaEmprestado = emprestimosAtivos.stream()
            .anyMatch(e -> e.getLivroEmprestado().getId().equals(livro.getId()));

        if (livroJaEmprestado) {
            throw new EmprestimoInvalidoException(
                "Este livro já está na sua lista de emprestimos."
            );
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivroEmprestado(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
        emprestimo.setStatus(STATUS_EMPRESTADO);

        create(emprestimo);
    }

    // Conta o número de empréstimos feitos por um usuário
    public long contarEmprestimosPorUsuario(Usuario usuario) {

    if (usuario == null || usuario.getId() == null) {
        throw new IllegalArgumentException("Usuário inválido.");
    }

    return repository.findByQuery(
        "SELECT e FROM Emprestimo e WHERE e.usuario.id = :id",
        Map.of("id", usuario.getId())
    ).size();
    }

    // Processa a devolução de um empréstimo
    public void devolver(Emprestimo emprestimo) {

        if (emprestimo == null || emprestimo.getId() == null) {
            throw new IllegalArgumentException("Empréstimo inválido.");
        }

        if (STATUS_DEVOLVIDO.equalsIgnoreCase(emprestimo.getStatus())) {
            return;
        }

        emprestimo.setStatus(STATUS_DEVOLVIDO);
        emprestimo.setDataDevolucao(LocalDate.now());

        update(emprestimo);
    }

    // Busca empréstimos ativos de um usuário
    public List<Emprestimo> buscarEmprestimosAtivosPorUsuario(Usuario usuario) {

    if (usuario == null || usuario.getId() == null) {
        throw new IllegalArgumentException("Usuário inválido.");
    }

    return repository.findByQuery(
        "SELECT e FROM Emprestimo e " +
        "WHERE e.usuario.id = :id " +
        "AND e.status = :status " +
        "AND e.dataDevolucao >= :hoje",
        Map.of(
            "id", usuario.getId(),
            "status", STATUS_EMPRESTADO,
            "hoje", LocalDate.now()
        )
    );
}
    // Busca todos os empréstimos de um usuário
    public List<Emprestimo> buscarTodosPorUsuario(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        }

        return repository.findByQuery(
            "SELECT e FROM Emprestimo e WHERE e.usuario = :usuario",
            Map.of("usuario", usuario)
        );
    }
}

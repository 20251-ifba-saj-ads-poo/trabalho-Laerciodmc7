package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.EmprestimoInvalidoException;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Usuario;
import java.util.Map;
import java.time.LocalDate;

public class EmprestimoService extends Service<Emprestimo> {

    public EmprestimoService(){super(Emprestimo.class);}

    public void validaEmprestimo(Emprestimo emprestimo, Usuario usuario, Livro livro) throws Exception{

        if (usuario == null || emprestimo == null || emprestimo.getLivroEmprestado() == null) {
            throw new EmprestimoInvalidoException("Dados do empréstimo inválidos.");
        }

        if(usuario.getEmprestimos().size() == 3){
            throw new EmprestimoInvalidoException("Você já atingiu seu limite de emprestimos.");
        }

        if (!findByMap(Map.of("ID", livro.getId())).isEmpty()) {
            throw new EmprestimoInvalidoException("Este livro já esta na sua lista de emprestimos.");
        }

        //emprestimo.setDataDevolução();
        //gerando emprestimo
        create(emprestimo);
        //adicionando na lista de emprestimos
        usuario.addEmprestimo(emprestimo);
    }

    public void verificaDevolução(Emprestimo emprestimo, Livro livro) throws Exception{
        LocalDate hoje = LocalDate.now();
        if(hoje.isAfter(emprestimo.getDataDevolução())){
            throw new EmprestimoInvalidoException("Devolução em atraso. Por favor devolva o livro" + livro.getNome());
        }
    }





}

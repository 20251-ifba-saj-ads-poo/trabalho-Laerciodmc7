package br.edu.ifba.saj.fwads.service;

import br.edu.ifba.saj.fwads.exception.BuscaInvalidaException;
import br.edu.ifba.saj.fwads.model.Categoria;
import br.edu.ifba.saj.fwads.model.Livro;
import java.util.List;
import java.util.stream.Collectors;

public class BuscaService extends Service<Livro>{

    // Campo estático para armazenar a lista de resultados da busca
    private static List<Livro> resultadosDaBusca;

    public static List<Livro> getResultadosDaBusca() {
        return resultadosDaBusca;
    }

    public static void setResultadosDaBusca(List<Livro> resultados) {
        resultadosDaBusca = resultados;
    }


    public BuscaService(){super(Livro.class);}

    public List<Livro> buscaValida(String pesquisa) throws BuscaInvalidaException {
        List<Livro> livros = findAll();
        List<Livro> resultados = livros.stream()
                .filter(livro ->
                        //Busca pelo nome
                        livro.getNome().toLowerCase().contains(pesquisa.toLowerCase()) ||
                             //Busca pelo autor
                                livro.getAutor().getNome().toLowerCase().contains(pesquisa.toLowerCase()) ||
                                // Busca pela categoria
                                livro.getCategoria().stream()
                                        .anyMatch(categoria -> categoria.getNomeCategoria().toLowerCase().contains(pesquisa.toLowerCase()))).collect(Collectors.toList());

        if (resultados.isEmpty()) {
            throw new BuscaInvalidaException("Nenhum livro encontrado para o termo de busca: '" + pesquisa + "'.");
        }
        return resultados;
    }

}

package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.BuscaInvalidaException;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.service.BuscaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchLivroController {

    @FXML
    private TextField txPesquisa;

    private String resultado;

    public String getResultado(){
        return resultado;
    }

    public void setResultado(String resultado){
        this.resultado = resultado;
    }

    public String getTxPesquisa(){
        return txPesquisa.getText();
    }

    private BuscaService buscaService = new BuscaService();

    public void setBuscaService(BuscaService buscaService) {
        this.buscaService = buscaService;
    }

    @FXML
    void buscaLivro() throws BuscaInvalidaException {
        String pesquisa = txPesquisa.getText();

        try {
            List<Livro> resultados = buscaService.buscaValida(pesquisa);
            // Salva os resultados em um lugar central (por exemplo, a classe App)
            buscaService.setResultadosDaBusca(resultados);
            // Navega para a tela de resultados
            App.setRoot("controller/PegarEmprestimo.fxml");

        } catch (BuscaInvalidaException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void limparTela(ActionEvent event) {
        App.setRoot("controller/Master.fxml");
    }
}
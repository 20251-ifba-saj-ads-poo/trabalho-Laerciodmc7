package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.BuscaInvalidaException;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.service.BuscaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchLivroController {

    @FXML
    private TextField txPesquisar;


    private BuscaService buscaService;

    @FXML
    private TableView<Livro> tabelaResultados;

    public void setBuscaService(BuscaService buscaService) {
        this.buscaService = buscaService;
    }

    @FXML
    void buscaLivro(ActionEvent event) {
        String pesquisa = txPesquisar.getText();
        if (pesquisa == null || pesquisa.isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Nenhum parametro de busca foi encontrado").showAndWait();
            return;
        }

        try {
            List<Livro> resultados = buscaService.buscaValida(pesquisa);
            tabelaResultados.getItems().setAll(resultados);

            if (resultados.isEmpty()) {
                new Alert(Alert.AlertType.INFORMATION, "Nenhum livro encontrado para: '" + pesquisa + "'.").showAndWait();
            }

        } catch (BuscaInvalidaException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void limparTela(ActionEvent event) {
        txPesquisar.setText("");
        App.setRoot("controller/Master.fxml");
    }

}
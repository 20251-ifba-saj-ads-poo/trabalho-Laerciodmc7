package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.BuscaInvalidaException;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.service.BuscaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchLivroController {

    @FXML
    private TextField txPesquisa;

    private BuscaService buscaService = new BuscaService();

    public void setBuscaService(BuscaService buscaService) {
        this.buscaService = buscaService;
    }

    @FXML
    void buscaLivro(ActionEvent event) throws BuscaInvalidaException {
        String pesquisa = txPesquisa.getText();

        try {
            List<Livro> resultados = buscaService.buscaValida(pesquisa);
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
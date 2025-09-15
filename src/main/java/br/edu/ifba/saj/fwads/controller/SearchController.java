package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.BuscaInvalidaException;
import br.edu.ifba.saj.fwads.model.Autor;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.service.BuscaService;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class SearchController {
    @FXML
    private Button bntBuscar;

    @FXML
    private Button bntCancelar;

    @FXML
    private TextField txAutorBusca;

    @FXML
    private TextField txCategoriaBusca;

    @FXML
    private TextField txNomeBusca;

    private MasterController masterController;
    private ListAutorController listAutorController;

    private Service<Livro> serviceLivro = new Service<>(Livro.class);
    private BuscaService buscaService = new BuscaService();
    
    public void setMasterController(MasterController masterController) {
        this.masterController = masterController;
    }

    public void setListAutorController(ListAutorController listAutorController) {
        this.listAutorController = listAutorController;
    }

    @FXML
    public void buscarAutor(ActionEvent event) throws BuscaInvalidaException {
        App.setRoot("controller/ListLivro.fxml");
    }

    @FXML
    public void limparTela(ActionEvent event) {

    }

}
package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Autor;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchController {
    @FXML
    private TextField txNome;
    @FXML
    private TextField txEmail;
    @FXML
    private TextField txCPF;

    private MasterController masterController;
    private ListAutorController listAutorController;

    private Service<Livro> serviceLivro = new Service<>(Livro.class);
    
    public void setMasterController(MasterController masterController) {
        this.masterController = masterController;
    }

    public void setListAutorController(ListAutorController listAutorController) {
        this.listAutorController = listAutorController;
    }

}
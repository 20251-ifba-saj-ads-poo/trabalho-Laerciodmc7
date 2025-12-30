package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SobreController {

    @FXML
    private Button bntSair;

    @FXML
    void sair(ActionEvent event) {
        App.setRoot("Controller/Master.fxml");
    }
}

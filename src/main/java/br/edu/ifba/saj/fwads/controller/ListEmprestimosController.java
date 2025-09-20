package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListEmprestimosController {

    @FXML
    private TableColumn<Livro, String> tblNome;

    @FXML
    private TableColumn<Livro, String> tblDataEmprestimo;

    @FXML
    private TableColumn<Livro, String> tblDataDevolução;

    @FXML
    private TableColumn<Livro, String> tblStatus;

    @FXML
    private TableView tblEmprestimos;

    @FXML
    public void initialize() {
        tblNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblDataEmprestimo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDataEmprestimo());
        tblDataDevolução.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDataDevolucao());
        loadLivroList();
    }

    public void loadLivroList(){
        tblEmprestimos.setItems(FXCollections.observableList(new Service(Livro.class).findAll()));
    }

    @FXML
    public void devolver(){

    }

    @FXML
    public void showHome(){
        App.setRoot("controller/Master.fxml");
    }



}


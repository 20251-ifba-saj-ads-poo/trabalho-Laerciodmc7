package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.ifba.saj.fwads.model.Emprestimo;

public class ListEmprestimosController {

    @FXML
    private TableColumn<Emprestimo, String> tblNome;

    @FXML
    private TableColumn<Emprestimo, String> tblDataEmprestimo;

    @FXML
    private TableColumn<Emprestimo, String> tblDataDevolução;

    @FXML
    private TableColumn<Emprestimo, String> tblStatus;

    @FXML
    private TableView tblEmprestimos;

    Usuario usuario = new Usuario();

    @FXML
    public void initialize() {
        tblNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblDataEmprestimo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDataEmprestimo())));
        tblDataDevolução.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDataDevolucao())));
        loadLivroList();
    }

    public void loadLivroList(){
        tblEmprestimos.setItems(FXCollections.observableArrayList(usuario.getEmprestimos()));
    }

    @FXML
    public void devolver(){

    }

    @FXML
    public void showHome(){
        App.setRoot("controller/Master.fxml");
    }



}


package br.edu.ifba.saj.fwads.controller;

import java.util.ArrayList;

import br.edu.ifba.saj.fwads.model.Autor;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.service.Service;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

public class CadLivroController {
   @FXML
    private TextField TxUserName;

    @FXML
    private TextField txCpf;

    @FXML
    private TextField txNome;

    @FXML
    private PasswordField txSenha;


    private ListLivroController listLivroController;

    private Service<Usuario> serviceUsuario = new Service<>(Usuario.class);

    public void setListLivroController(ListLivroController listLivroController) {
        this.listLivroController = listLivroController;
    }

    @FXML
    void salvarUsuario(ActionEvent event) {
        //criando novo usuario (instanciando a classe Usuario.java)
        Usuario novoUsuario = new Usuario(txNome.getText(), TxUserName.getText(),
          txCpf.getText(), txSenha.getText(), new ArrayList<>(), 3);
        serviceUsuario.create(novoUsuario);
        new Alert(AlertType.INFORMATION, 
        "Usuario:"+novoUsuario.getUserName()+" cadastrado com sucesso!").showAndWait();
        limparTela();
        if (listLivroController!=null) {
            listLivroController.loadLivroList();
        }
    }


    @FXML
    private void limparTela() {
        txNome.setText("");
        TxUserName.setText("");
        txCpf.setText("");
        txSenha.setText("");
        //new Alert(AlertType.INFORMATION, serviceLivro.findAll().toString()).showAndWait();
    }

    

}

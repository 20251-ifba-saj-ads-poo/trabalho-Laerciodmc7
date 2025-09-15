package br.edu.ifba.saj.fwads.controller;

import java.util.ArrayList;

import br.edu.ifba.saj.fwads.exception.CadastroInvalidoException;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.service.BuscaService;
import br.edu.ifba.saj.fwads.service.Service;
import br.edu.ifba.saj.fwads.service.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadUsuarioController {
   @FXML
    private TextField TxUserName;

    @FXML
    private TextField txCpf;

    @FXML
    private TextField txNome;

    @FXML
    private PasswordField txSenha;


    private ListEmprestimosController listLivroController;

    private UsuarioService usuarioService = new UsuarioService();

    public void setListLivroController(ListEmprestimosController listLivroController) {
        this.listLivroController = listLivroController;
    }

    @FXML
    void salvarUsuario(ActionEvent event) throws CadastroInvalidoException {
        //instancia a classe usuario associando as entradas na tela de cadastro com os atributos da classe
        Usuario novoUsuario = new Usuario(txNome.getText(), TxUserName.getText(),
          txCpf.getText(), txSenha.getText(), new ArrayList<Emprestimo>(), 3);
            usuarioService.validaCadastro(novoUsuario); //valida o cadastro do usuario
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

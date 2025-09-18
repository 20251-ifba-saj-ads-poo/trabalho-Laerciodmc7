package br.edu.ifba.saj.fwads.controller;

import java.util.ArrayList;

import br.edu.ifba.saj.fwads.App;
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
    private TextField txUserName;

    @FXML
    private TextField txCpf;

    @FXML
    private TextField txNomeCompleto;

    @FXML
    private PasswordField txSenha;


    private ListEmprestimosController listLivroController;

    private UsuarioService usuarioService = new UsuarioService();

    public void setListLivroController(ListEmprestimosController listLivroController) {
        this.listLivroController = listLivroController;
    }

    @FXML
    void salvarUsuario(ActionEvent event) throws CadastroInvalidoException {
        try {
            //instancia a classe usuario associando as entradas na tela de cadastro com os atributos da classe
            Usuario novoUsuario = new Usuario(txNomeCompleto.getText(), txUserName.getText(),
                    txCpf.getText(), txSenha.getText(), new ArrayList<Emprestimo>());
            usuarioService.validaCadastro(novoUsuario); //valida o cadastro do usuario
            new Alert(AlertType.INFORMATION,
                    "Usuario:"+novoUsuario.getUserName()+" cadastrado com sucesso!").showAndWait();
            limparTela();
            if (listLivroController!=null) {
                listLivroController.loadLivroList();
            }
        }
        catch (CadastroInvalidoException e){
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e){
            e.printStackTrace();
            new Alert(AlertType.ERROR, "Erro inesperado, favor entra em contato com a equipe de desenvolvimento").showAndWait();
        }
    }


    @FXML
    private void limparTela() {
        txNomeCompleto.setText("");
        txUserName.setText("");
        txCpf.setText("");
        txSenha.setText("");
        App.setRoot("controller/Login.fxml");
        //new Alert(AlertType.INFORMATION, serviceLivro.findAll().toString()).showAndWait();
    }

    

}

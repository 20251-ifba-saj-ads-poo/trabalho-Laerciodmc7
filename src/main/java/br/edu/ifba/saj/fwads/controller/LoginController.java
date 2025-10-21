/**
 * Sample Skeleton for 'Login.fxml' Controller Class
 */

package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.exception.LoginInvalidoException;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.service.UsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML // fx:id="txSenha"
    private PasswordField txSenha; // Value injected by FXMLLoader

    @FXML // fx:id="txUsuario"
    private TextField txUsuario;

    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    void entrar(ActionEvent event) {
        try {
            Usuario usuario = usuarioService.validaLogin(txUsuario.getText(), txSenha.getText());

            // 1. Armazene o usuário na Sessão (para outras telas)
            usuarioService.setUsuarioLogado(usuario);

            // 2. Navegue para a tela Master
            App.setRoot("controller/Master.fxml");

        } catch (LoginInvalidoException e) {
            new Alert(AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e){
            // AQUI ESTÁ A CORREÇÃO: Força a exibição do erro no console
            e.printStackTrace();
            new Alert(AlertType.ERROR, "ERRO: Falha ao autenticar. Verifique o console para detalhes.").showAndWait();
        }
    }

    @FXML
    public void criarUsuario(ActionEvent event){
        App.setRoot("controller/CadUsuario.fxml");
    }


    @FXML
    void limparCampos(ActionEvent event) {
        txUsuario.setText("");
        txSenha.setText("");
        //new Alert(AlertType.INFORMATION, usuarioService.findAll().toString()).showAndWait();

    }

}

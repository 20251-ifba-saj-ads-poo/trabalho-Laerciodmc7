package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class MasterController {

    @FXML
    private Button bntBuscar;

    @FXML
    private Button bntEmprestimos;

    @FXML
    private Button bntMeuPerfil;

    @FXML
    private Button bntSair;

    @FXML
    private Button bntSobre;

    @FXML
    private BorderPane masterPane;

    @FXML
    private VBox menu;

    @FXML
    private Circle userPicture;

    private Usuario usuarioLogado;

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @FXML
    void showHome(ActionEvent event) {
        limparBotoes(event.getSource());
        masterPane.setCenter(new Pane());

    }

    //chama a tela "SearchController"
    @FXML
    public void buscar(ActionEvent event) {
        limparBotoes(event.getSource());
        App.setRoot("controller/CadAutor.fxml");
    }

    @FXML
    public void showEmprestimos(ActionEvent event) {
        limparBotoes(event.getSource());
        App.setRoot("controller/ListLivro.fxml");
    }

    @FXML
    public void showCadastro(ActionEvent event){
        limparBotoes(event.getSource());
        App.setRoot("controller/ListCadastro.fxml");
    }


    @FXML
    public void showSobre(ActionEvent event){

    }



    @FXML
    public void sair(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja realmente sair??", ButtonType.YES, ButtonType.NO);
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    App.setRoot("controller/Login.fxml");
                });
    }



    private void limparBotoes(Object source) {
        menu.getChildren().forEach((node) -> {
                    if (node instanceof Button btn) {
                        node.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false);
                    }
                }

        );
        if (source instanceof Button btn) {
            btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        }
    }

    public Object showFXMLFile(String resourceName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourceName));
            Pane fxmlCarregado = loader.load();
            masterPane.setCenter(fxmlCarregado);
            return loader.getController();

        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Erro ao carregar o arquivo " + resourceName).showAndWait();
            e.printStackTrace();
        }
        return null;
    }

}

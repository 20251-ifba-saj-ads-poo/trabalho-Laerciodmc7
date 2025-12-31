package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

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
        App.setRoot("controller/SearchLivro.fxml");
    }

    @FXML
    public void showEmprestimos(ActionEvent event) {
        limparBotoes(event.getSource());
        App.setRoot("controller/ListLivro.fxml");
    }

    @FXML
    public void showCadastro(ActionEvent event) throws IOException {

        limparBotoes(event.getSource());

        FXMLLoader loader = new FXMLLoader(
                App.class.getResource("controller/CadUsuario.fxml")
        );

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.setTitle("Cadastro de Usuário");
        stage.showAndWait();
    }




    @FXML
    public void showSobre(ActionEvent event){
        App.setRoot("controller/Sobre.fxml");

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

package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.awt.event.ActionEvent;

public class ListAutorController {
    @FXML
    private TableView<Usuario> tabelaUsuario;

    @FXML
    private TableColumn<Usuario, String> columnNome;

    @FXML
    private TableColumn<Usuario, String> columnUserName;

    @FXML
    private TableColumn<Usuario, String> columnCPF;

    @FXML
    private TableColumn<Usuario, String> columnSenha;


    private static Usuario usuarioLogado;

    public Usuario getUsuarioLogado(){
        return this.usuarioLogado;
    }


    @FXML
    public void initialize() {
        // Associa as colunas aos atributos da classe Usuario
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpfUsuario"));
        columnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

         usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            tabelaUsuario.setItems(FXCollections.observableArrayList(usuarioLogado));
        } else {
            System.out.println("Nenhum usuário logado.");
        }
    }

    public void loadAutorList() {
        //tblAutor.setItems(FXCollections.observableList(new Service(Usuario.class).findAll()));
    }

    @FXML
   public void showMenu(ActionEvent event){
        App.setRoot("controller/Master.fxml");
   }


    @FXML
    public void showNovoAutor() {
        
        /*Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/CadAutor.fxml"), 800, 600);            
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL); 
        SearchLivroController controller = (SearchLivroController) App.getController();controller.setListAutorController(this);
        stage.showAndWait();*/
    }

}

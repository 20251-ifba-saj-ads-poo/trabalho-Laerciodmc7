package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.service.UsuarioService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListEmprestimosController {

    @FXML private TableColumn<Emprestimo, String> tblNome;
    @FXML private TableColumn<Emprestimo, String> tblDataEmprestimo;
    @FXML private TableColumn<Emprestimo, String> tblDataDevolucao;
    @FXML private TableColumn<Emprestimo, String> tblStatus;
    @FXML private TableView<Emprestimo> tblEmprestimos;

    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        tblNome.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getLivroEmprestado() != null
                        ? cellData.getValue().getLivroEmprestado().getNome()
                        : "N/A"
        ));

        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblDataEmprestimo.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getDataEmprestimo() != null
                        ? String.valueOf(cellData.getValue().getDataEmprestimo())
                        : "N/A"
        ));
        tblDataDevolucao.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getDataDevolucao() != null
                        ? String.valueOf(cellData.getValue().getDataDevolucao())
                        : "N/A"
        ));

        loadLivroList();
    }

    public void loadLivroList(){
        // Apenas leia o objeto da sessão, que agora está sincronizado.
        Usuario usuario = UsuarioService.getUsuarioLogado();

        if (usuario != null && usuario.getEmprestimos() != null) {
            tblEmprestimos.setItems(FXCollections.observableArrayList(usuario.getEmprestimos()));
        } else {
            tblEmprestimos.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    public void devolver(){
        // Lógica de devolução
    }

    @FXML
    public void showHome(ActionEvent event){
        App.setRoot("controller/Master.fxml");
    }
}
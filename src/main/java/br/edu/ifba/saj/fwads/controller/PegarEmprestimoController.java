package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.SessionContext;
import br.edu.ifba.saj.fwads.exception.BuscaInvalidaException;
import br.edu.ifba.saj.fwads.exception.EmprestimoInvalidoException;
import br.edu.ifba.saj.fwads.model.Livro;
import br.edu.ifba.saj.fwads.model.Usuario;
import br.edu.ifba.saj.fwads.service.BuscaService;
import br.edu.ifba.saj.fwads.service.EmprestimoService;
import br.edu.ifba.saj.fwads.service.UsuarioService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PegarEmprestimoController {

    @FXML
    private Button bntEmprestar;

    @FXML
    private TableColumn<Livro, String> tblAutor;

    @FXML
    private TableColumn<Livro, String> tblCategoria;

    @FXML
    private TableColumn<Livro, String> tblData;

    @FXML
    private TableColumn<Livro, Integer> tblPaginas;

    @FXML
    private TableColumn<Livro, String> tblTitulo;

    @FXML
    private TableView<Livro> tblLivros;

    private ObservableList<Livro>livros = FXCollections.observableArrayList();

    BuscaService buscaService = new BuscaService();
    UsuarioService usuarioService = new UsuarioService();
    EmprestimoService emprestimoService = new EmprestimoService();
    LoginController loginController = new LoginController();


    @FXML
    public void initialize() throws BuscaInvalidaException {

        List<Livro> resultados = buscaService.getResultadosDaBusca();

        tblTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tblAutor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAutor().getNome()));

        tblCategoria.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCategoria()
                                .stream().map(c -> c.getNomeCategoria())
                                .collect(Collectors.joining(", "))
                ));

        tblData.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        String.valueOf(cellData.getValue().getDataLacamento())
                ));

        tblPaginas.setCellValueFactory(new PropertyValueFactory<>("qntPaginas"));

        tblLivros.setItems(FXCollections.observableArrayList(resultados));

    }


    @FXML
    void emprestar(ActionEvent event) {
        // Pega o livro selecionado da tabela
        Livro selecionado = tblLivros.getSelectionModel().getSelectedItem();
         Usuario usuario = SessionContext.getUsuarioLogado();

        try {
            emprestimoService.validaEmprestimo(usuario, selecionado);
            new Alert(Alert.AlertType.INFORMATION, "Livro emprestado com sucesso!").showAndWait();
        } catch (EmprestimoInvalidoException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "ERRO! Não foi possível realizar o empréstimo.").showAndWait();
            e.printStackTrace();
        }
    }

    public void showHome(){
        App.setRoot("controller/Master.fxml");
    }
}

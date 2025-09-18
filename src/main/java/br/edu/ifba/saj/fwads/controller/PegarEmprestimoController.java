package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.model.Livro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
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

    private ObservableList<Livro> livros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tblTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tblAutor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAutor().getNome()));

        tblCategoria.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getCategoria()
                                .stream()
                                .map(c -> c.getNomeCategoria())
                                .collect(Collectors.joining(", "))
                ));

        tblData.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getDataLacamento() != null
                                ? cellData.getValue().getDataLacamento().toString()
                                : ""
                ));

        tblPaginas.setCellValueFactory(new PropertyValueFactory<>("qntPaginas"));

        tblLivros.setItems(livros);
    }

    public void setLivros(List<Livro> resultados) {
        livros.setAll(resultados);
    }

    @FXML
    void emprestar(ActionEvent event) {
        Livro selecionado = tblLivros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            System.out.println("Emprestando livro: " + selecionado.getNome());
            //chamr service para registrar emprestimo
        }
    }
}

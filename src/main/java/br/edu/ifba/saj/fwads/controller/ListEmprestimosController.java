package br.edu.ifba.saj.fwads.controller;

import br.edu.ifba.saj.fwads.App;
import br.edu.ifba.saj.fwads.model.Emprestimo;
import br.edu.ifba.saj.fwads.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.ifba.saj.fwads.SessionContext;
import br.edu.ifba.saj.fwads.service.EmprestimoService; 

public class ListEmprestimosController {

    @FXML private TableColumn<Emprestimo, String> tblNome;
    @FXML private TableColumn<Emprestimo, String> tblDataEmprestimo;
    @FXML private TableColumn<Emprestimo, String> tblDataDevolucao;
    @FXML private TableColumn<Emprestimo, String> tblStatus;
    @FXML private TableView<Emprestimo> tblEmprestimos;

    private EmprestimoService emprestimoService;

    // Inicializa o controlador e configura as colunas da tabela
    @FXML
    public void initialize() {
        emprestimoService = new EmprestimoService();

        tblNome.setCellValueFactory(cellData ->
            new SimpleStringProperty(
                cellData.getValue().getLivroEmprestado() != null
                    ? cellData.getValue().getLivroEmprestado().getNome()
                    : "N/A"
            )
        );

        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblDataEmprestimo.setCellValueFactory(cellData ->
            new SimpleStringProperty(
                cellData.getValue().getDataEmprestimo() != null
                    ? cellData.getValue().getDataEmprestimo().toString()
                    : "N/A"
            )
        );

        tblDataDevolucao.setCellValueFactory(cellData ->
            new SimpleStringProperty(
                cellData.getValue().getDataDevolucao() != null
                    ? cellData.getValue().getDataDevolucao().toString()
                    : "N/A"
            )
        );

        loadEmprestimos();
    }

    // Carrega os empréstimos ativos do usuário logado
    public void loadEmprestimos() {
       Usuario usuario = SessionContext.getUsuarioLogado();

        tblEmprestimos.setItems(
        FXCollections.observableArrayList(
        emprestimoService.buscarEmprestimosAtivosPorUsuario(usuario)));
    }

    // Devolve o livro selecionado na tabela
    @FXML
    public void devolver() {
    Emprestimo selecionado = tblEmprestimos
        .getSelectionModel()
        .getSelectedItem();

    if (selecionado == null) {
        new Alert(
            Alert.AlertType.WARNING,
            "Selecione um empréstimo para devolver."
        ).showAndWait();
        return;
    }

    try {
        emprestimoService.devolver(selecionado);
        loadEmprestimos();

        new Alert(
            Alert.AlertType.INFORMATION,
            "Livro devolvido com sucesso."
        ).showAndWait();

    } catch (Exception e) {
        e.printStackTrace();
        new Alert(
            Alert.AlertType.ERROR,
            "Erro ao devolver o livro."
        ).showAndWait();
    }
    }

    // Navega de volta para a tela inicial
    @FXML
    public void showHome(ActionEvent event) {
        App.setRoot("controller/Master.fxml");
    }
}

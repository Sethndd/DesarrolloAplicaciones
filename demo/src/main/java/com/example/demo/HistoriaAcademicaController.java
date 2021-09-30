package com.example.demo;

import DataBaseConnection.HistoriaAcademicaDAO;
import POJO.HistoriaAcademica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoriaAcademicaController  extends Controller implements Initializable {
    public TableView<HistoriaAcademica> tbHistoriasAcademicas;
    public TableColumn<HistoriaAcademica, String> tcEstudiante;
    public TableColumn<HistoriaAcademica, String> tcColegioAnterior;
    public TableColumn<HistoriaAcademica, String> tcAño;
    public TableColumn<HistoriaAcademica, String> tcGrado;
    public ObservableList<HistoriaAcademica> historiaAcademicasObs;
    public List<HistoriaAcademica> historiaAcademicas;
    private HistoriaAcademica historiaAcademica;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcEstudiante.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("nombreEstudiante"));
        tcColegioAnterior.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("colegioAnterior"));
        tcAño.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("año"));
        tcGrado.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("grado"));
    }

    public void llenarTabla(){
        historiaAcademicas = HistoriaAcademicaDAO.getHistoriasAcademicasActivas();
        historiaAcademicasObs = FXCollections.observableArrayList(historiaAcademicas);
        tbHistoriasAcademicas.setItems(historiaAcademicasObs);
    }

    public void clicHistoriaAcademica(MouseEvent mouseEvent) {
        historiaAcademica = tbHistoriasAcademicas.getSelectionModel().getSelectedItem();
    }

    public void clicAgregar(ActionEvent actionEvent) {
        //TODO OPEN WINDOW
    }

    public void clicConsultar(ActionEvent actionEvent) {
        //TODO OPEN WINDOW
    }

    public void clicBorrar(ActionEvent actionEvent) {
        //TODO ALERT CONFIRMATION
    }
}

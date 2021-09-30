package com.example.demo;

import DataBaseConnection.HistoriaAcademicaDAO;
import POJO.HistoriaAcademica;
import Utilidades.UtilVentanas;
import com.mysql.cj.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.Optional;
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

        llenarTabla();
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
        DetallesHistoriaAcademicaController ventana = new DetallesHistoriaAcademicaController(this, "Agregar");
        UtilVentanas.iniciarVentana(tbHistoriasAcademicas, ventana, "DetallesHistoriaAcademica.fxml", UtilVentanas.NO_CERRAR);
    }

    public void clicConsultar(ActionEvent actionEvent) {
        if (historiaAcademica != null){
            DetallesHistoriaAcademicaController ventana = new DetallesHistoriaAcademicaController(historiaAcademica, "Consultar");
            UtilVentanas.iniciarVentana(tbHistoriasAcademicas, ventana, "DetallesHistoriaAcademica.fxml", UtilVentanas.NO_CERRAR);
        } else {
            UtilVentanas.alerta("Asegúrese de seleccionar una Historia Académica", Alert.AlertType.WARNING);
        }
    }

    public void clicBorrar(ActionEvent actionEvent) {
        if (historiaAcademica != null) {
            Optional<ButtonType> respuesta = UtilVentanas.alertaPregunta("¿Seguro que desea borrar la Historia Academica Seleccionada?");
            if(respuesta.get() == ButtonType.OK){
                HistoriaAcademicaDAO.borrar(historiaAcademica);
                UtilVentanas.alerta("Realizado", Alert.AlertType.INFORMATION);
                llenarTabla();
            }
        } else {
            UtilVentanas.alerta("Asegúrese de seleccionar una Historia Académica", Alert.AlertType.WARNING);
        }
    }
}

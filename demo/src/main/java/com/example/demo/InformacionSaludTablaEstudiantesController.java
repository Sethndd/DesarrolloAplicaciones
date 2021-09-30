package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import DataBaseConnection.InformacionSaludDAO;
import POJO.Estudiante;
import Utilidades.UtilVentanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Utilidades.UtilVentanas.iniciarVentana;

public class InformacionSaludTablaEstudiantesController implements Initializable, Controller {
    @FXML
    public TableView<Estudiante> tbEstudiantes;
    @FXML
    public TableColumn<Estudiante, String> tcPrimApe;
    @FXML
    public TableColumn<Estudiante, String> tcSegApe;
    @FXML
    public TableColumn<Estudiante, String> tcPrimNom;
    @FXML
    public TableColumn<Estudiante, String> tcSegNom;
    @FXML
    private Button buttonRegistrarInfo;
    @FXML
    private Button buttonVerInfo;

    ObservableList<Estudiante> estudiantesObs;
    List<Estudiante> estudiantes;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        estudiantes = new ArrayList<>();
        llenarTabla();
    }

    @FXML
    protected void ClickRegistrarInformacion(ActionEvent actionEvent){
        Estudiante estudiante = tbEstudiantes.getSelectionModel().getSelectedItem();
        if(estudiante == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Seleccione un estudiante.");
            alert.show();
        }
        else if(InformacionSaludDAO.getInformacionSaludeByIdEstudiante(estudiante.getId()) != null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ya existe un registro previo.");
            alert.show();
        }else{
            RegistrarInformacionSaludController registro = new RegistrarInformacionSaludController("Registro", estudiante);
            UtilVentanas.iniciarVentana(tbEstudiantes, registro, "RegistrarInformacionSalud.fxml", false);
        }
    }

    @FXML
    protected void ClickVerInformacion(ActionEvent actionEvent){
        Estudiante estudiante = tbEstudiantes.getSelectionModel().getSelectedItem();
        if(estudiante == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Seleccione un estudiante.");
            alert.show();
        }
        else if(InformacionSaludDAO.getInformacionSaludeByIdEstudiante(estudiante.getId()) != null){
            DetallesInformacionSaludController detalles = new DetallesInformacionSaludController("Detalles", estudiante);
            UtilVentanas.iniciarVentana(tbEstudiantes, detalles, "DetallesInformacionSalud.fxml", false);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No hay datos registrados para este estudiante.");
            alert.show();
        }
    }

    private void llenarTabla() {
        tcPrimApe.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("primerApe"));
        tcSegApe.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("segundoApe"));
        tcPrimNom.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("primerNom"));
        tcSegNom.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("segundoNom"));

        estudiantes = EstudianteDAO.getEstudiantesActivos();
        estudiantesObs =  FXCollections.observableArrayList(estudiantes);
        tbEstudiantes.setItems(estudiantesObs);
    }
}

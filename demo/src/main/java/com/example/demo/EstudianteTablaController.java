package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import POJO.Estudiante;
import Utilidades.UtilVentanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EstudianteTablaController implements Initializable, Controller{
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
    public TableColumn<Estudiante, String> tcOrigen;
    ObservableList<Estudiante> estudiantesObs;
    List<Estudiante> estudiantes;

    @FXML
    protected void agregar(ActionEvent actionEvent) {
        DetallesEstudianteController detalles = new DetallesEstudianteController("Agregar");
        UtilVentanas.iniciarVentana(tbEstudiantes, detalles, "DetallesEstudiante.fxml", false);
        llenarTabla();
    }

    @FXML
    protected void consultarPorBtn(ActionEvent actionEvent) {
        consultar();
    }

    @FXML
    public void consultarPorTb(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2){
            consultar();
        }
    }

    @FXML
    protected void borrar(ActionEvent actionEvent) {
        Estudiante estudiante = tbEstudiantes.getSelectionModel().getSelectedItem();
        if(estudiante == null){
            UtilVentanas.alerta("Seleccione un estudiante primero.", Alert.AlertType.WARNING);
        }
        else{
            EstudianteDAO.borrar(estudiante);
            UtilVentanas.alerta("Estudiante borrado exitosamente", Alert.AlertType.INFORMATION);
            llenarTabla();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        estudiantes = new ArrayList<>();
        llenarTabla();
    }

    private void llenarTabla() {
        tcPrimApe.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("primerApe"));
        tcSegApe.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("segundoApe"));
        tcPrimNom.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("primerNom"));
        tcSegNom.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("segundoNom"));
        tcOrigen.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("origen"));

        estudiantes = EstudianteDAO.getEstudiantesActivos();
        estudiantesObs =  FXCollections.observableArrayList(estudiantes);
        tbEstudiantes.setItems(estudiantesObs);
    }

    private void consultar(){
        Estudiante estudiante = tbEstudiantes.getSelectionModel().getSelectedItem();
        if(estudiante == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Seleccione un estudiante primero.");
            alert.show();
        }
        else{
            DetallesEstudianteController detalles = new DetallesEstudianteController("Consultar", estudiante);
            UtilVentanas.iniciarVentana(tbEstudiantes, detalles, "DetallesEstudiante.fxml", false);
        }
    }
}

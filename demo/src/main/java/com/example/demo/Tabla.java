package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import POJO.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Tabla extends Controller implements Initializable{
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
    ObservableList<Estudiante> estudiantesObs;
    List<Estudiante> estudiantes;

    @FXML
    protected void agregar(ActionEvent actionEvent) {
        DetallesEstudiante detalles = new DetallesEstudiante("Agregar");
        abrirVentana(detalles, "DetallesEstudiante");
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
            alerta("Seleccione un estudiante primero.");
        }
        else{
            EstudianteDAO.borrar(estudiante);
            alerta("Estudiante borrado exitosamente");
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
            DetallesEstudiante detalles = new DetallesEstudiante("Consultar", estudiante);
            abrirVentana(detalles, "DetallesEstudiante");
        }
    }

    private void abrirVentana(Controller controller, String nombreFXML){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Tabla.class.getResource(nombreFXML + ".fxml"));
        fxmlLoader.setController(controller);
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 300, 300);
            stage.setTitle(nombreFXML);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void alerta(String texto){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(texto);
        alert.show();
    }
}

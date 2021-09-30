package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import DataBaseConnection.HistoriaAcademicaDAO;
import POJO.Estudiante;
import POJO.HistoriaAcademica;
import Utilidades.UtilVentanas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class DetallesHistoriaAcademicaController implements Initializable, Controller {

    public ComboBox<Estudiante> cbEstudiante;
    public TextField tfColegioAnterior;
    public TextField tfAño;
    public TextField tfGrado;
    public Button btnAceptar;
    public Button btnCancelar;
    private HistoriaAcademicaController historiaAcademicaController;
    private HistoriaAcademica historiaAcademica;
    private String tipoVentana;

    public DetallesHistoriaAcademicaController(HistoriaAcademicaController historiaAcademicaController, String tipoVentana ){
        this.historiaAcademicaController = historiaAcademicaController;
        this.tipoVentana = tipoVentana;
    }

    public DetallesHistoriaAcademicaController(HistoriaAcademica historiaAcademica, String tipoVentana){
        this.historiaAcademica = historiaAcademica;
        this.tipoVentana = tipoVentana;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tipoVentana.equals("Consultar")){
            cbEstudiante.setEditable(false);
            tfColegioAnterior.setEditable(false);
            tfAño.setEditable(false);
            tfGrado.setEditable(false);

            Estudiante estudiante = EstudianteDAO.getEstudianteById(historiaAcademica.getIdEstudiante());
            cbEstudiante.getSelectionModel().select(estudiante);
            tfColegioAnterior.setText(historiaAcademica.getColegioAnterior());
            tfAño.setText(historiaAcademica.getAño());
            tfGrado.setText(historiaAcademica.getGrado());

            btnCancelar.setVisible(false);
            btnCancelar.setManaged(false);
        } else {
            List<Estudiante> estudiantes = EstudianteDAO.getEstudiantesActivos();
            ObservableList<Estudiante> estudiantesObs = FXCollections.observableArrayList(estudiantes);
            cbEstudiante.setItems(estudiantesObs);
        }
    }

    public void clicAceptar(ActionEvent actionEvent) {
        if (tipoVentana.equals("Agregar")) {
            if (validarCampos()){
                Estudiante estudiante = cbEstudiante.getSelectionModel().getSelectedItem();
                HistoriaAcademica historiaAcademica = new HistoriaAcademica();
                historiaAcademica.setColegioAnterior(tfColegioAnterior.getText());
                historiaAcademica.setAño(tfAño.getText());
                historiaAcademica.setGrado(tfGrado.getText());
                historiaAcademica.setIdEstudiante(estudiante.getId());

                HistoriaAcademicaDAO.guardar(historiaAcademica);

                UtilVentanas.alerta("Registro completado.", Alert.AlertType.INFORMATION);
                historiaAcademicaController.llenarTabla();
            } else {
                UtilVentanas.alerta("Asegúrese de Llenar todos los campos.", Alert.AlertType.WARNING);
            }
        }
        cerrarVentana();
    }

    public void clicCancelar(ActionEvent actionEvent) {
        cerrarVentana();
    }

    private boolean validarCampos(){
        return cbEstudiante.getSelectionModel().getSelectedItem() != null &&
                !tfColegioAnterior.getText().isEmpty() &&
                !tfAño.getText().isEmpty() &&
                !tfGrado.getText().isEmpty();
    }

    private void cerrarVentana(){
        UtilVentanas.cerrar(btnAceptar);
    }
}

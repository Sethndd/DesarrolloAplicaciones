package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import DataBaseConnection.TutorDAO;
import POJO.Estudiante;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import POJO.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TutorRegistrarController extends Controller implements Initializable {
    public ComboBox cb_nombreEstudiante;
    public TextField tf_primerNombreTutor;
    public TextField tf_segundoNombreTutor;
    public TextField tf_apelldioPatTutor;
    public TextField tf_apellidoMatTutor;
    public TextField tf_correoElectronico;
    public TextField tf_telefonoFijo;
    public TextField tf_telefonoCelular;
    public Button btnCancelar;
    public Button btnAceptar;
    ObservableList<Estudiante> estudiantesObs;
    List<Estudiante> estudiantes;

    public TutorRegistrarController() {}

    public void clicCancelar(ActionEvent actionEvent) {
        Stage cancelar = (Stage)tf_correoElectronico.getScene().getWindow();
        cancelar.close();
    }

    public void clicAceptar(ActionEvent actionEvent) {
        if (this.validar()) {
            Tutor tutor = new Tutor();
            Estudiante estudiante = (Estudiante) cb_nombreEstudiante.getSelectionModel().getSelectedItem();
            tutor.setPrimerNombreTutor(tf_primerNombreTutor.getText());
            tutor.setIdEstudiante(estudiante.getId());
            tutor.setSegundoNombreTutor(tf_segundoNombreTutor.getText());
            tutor.setApellidoPatTutor(tf_apelldioPatTutor.getText());
            tutor.setApellidoMatTutor(tf_apellidoMatTutor.getText());
            tutor.setCorreoElectronico(tf_correoElectronico.getText());
            tutor.setTelefonoCelular(tf_telefonoCelular.getText());
            tutor.setTelefonoFijo(tf_telefonoFijo.getText());
            tutor.setActivo(true);

            TutorDAO.guardar(tutor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Registro realizado");
            alert.show();

            Stage cancelar = (Stage)tf_correoElectronico.getScene().getWindow();
            cancelar.close();
        }

    }

    public boolean validar() {
        return this.cb_nombreEstudiante.getSelectionModel().getSelectedItem() != null &&
                !this.tf_apelldioPatTutor.getText().isEmpty() &&
                !this.tf_apellidoMatTutor.getText().isEmpty() &&
                !this.tf_correoElectronico.getText().isEmpty() &&
                !this.tf_primerNombreTutor.getText().isEmpty() &&
                !this.tf_segundoNombreTutor.getText().isEmpty() &&
                !this.tf_telefonoCelular.getText().isEmpty() &&
                !this.tf_telefonoFijo.getText().isEmpty();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.estudiantes = EstudianteDAO.getEstudiantesActivos();
        this.estudiantesObs = FXCollections.observableArrayList(this.estudiantes);
        this.cb_nombreEstudiante.setItems(this.estudiantesObs);

    }
}

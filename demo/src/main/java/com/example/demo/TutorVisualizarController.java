package com.example.demo;

import POJO.Tutor;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TutorVisualizarController implements Initializable, Controller {
    public Label lb_telefonoMovil;
    public Button btnRegresar;
    public Label lb_telefonoCasa;
    public Label lb_correoElectronico;
    public Label lb_apellidoMat;
    public Label lb_apellidoPat;
    public Label lb_segundoNombre;
    public Label lb_primerNombre;
    public Label lb_nombreEstudiante;

    private Tutor tutor;

    public TutorVisualizarController(Tutor tutor) {
        this.tutor = tutor;
    }

    public void clicRegresar(ActionEvent actionEvent) {
        Stage regresar = (Stage)btnRegresar.getScene().getWindow();
        regresar.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lb_nombreEstudiante.setText(tutor.getNombreEstudiante());
        lb_primerNombre.setText(tutor.getPrimerNombreTutor());
        lb_segundoNombre.setText(tutor.getSegundoNombreTutor());
        lb_apellidoPat.setText(tutor.getApellidoPatTutor());
        lb_apellidoMat.setText(tutor.getApellidoMatTutor());
        lb_correoElectronico.setText(tutor.getCorreoElectronico());
        lb_telefonoCasa.setText(tutor.getTelefonoFijo());
        lb_telefonoMovil.setText(tutor.getTelefonoCelular());
    }
}

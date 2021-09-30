package com.example.demo;

import Utilidades.UtilVentanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Controller {
    @FXML
    private Button buttonEstudiantes;
    @FXML
    private Button buttonTutores;
    @FXML
    private Button buttonSalud;
    @FXML
    private Button buttonColegios;
    @FXML
    private Button buttonInfoAcademica;

    @FXML
    protected void clickEstudiantes(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonEstudiantes, new EstudianteTablaController(), "EstudianteTabla.fxml", UtilVentanas.NO_CERRAR);
    }
    @FXML
    protected void ClickTutores(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonEstudiantes, new TutorTablaController(), "TutorTabla.fxml", UtilVentanas.NO_CERRAR);
    }
    @FXML
    protected void ClickInfoSalud(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonSalud, new InformacionSaludTablaEstudiantesController(), "InformacionSaludTablaEstudiantes.fxml", UtilVentanas.NO_CERRAR);
    }
    @FXML
    protected void clickColegios(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonSalud, new ColegioController(), "Colegio.fxml", UtilVentanas.NO_CERRAR);
    }
    @FXML
    protected void ClickInfoAcademica(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonInfoAcademica, new HistoriaAcademicaController(), "HistoriaAcademicaTabla.fxml", UtilVentanas.NO_CERRAR);
    }
}

package com.example.demo;

import Utilidades.UtilVentanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController extends Controller implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    protected void clickEstudiantes(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonEstudiantes, new EstudianteTablaController(), "EstudianteTabla.fxml", UtilVentanas.NO_CERRAR);
    }
    @FXML
    protected void ClickTutores(ActionEvent actionEvent){

    }
    @FXML
    protected void ClickInfoSalud(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonSalud, new InformacionSaludTablaEstudiantesController(), "InformacionSaludTablaEstudiantes.fxml", UtilVentanas.NO_CERRAR);
    }
    @FXML
    protected void clickColegios(ActionEvent actionEvent){

    }
    @FXML
    protected void ClickInfoAcademica(ActionEvent actionEvent){
        UtilVentanas.iniciarVentana(buttonInfoAcademica, new HistoriaAcademicaController(), "HistoriaAcademicaTabla.fxml", UtilVentanas.NO_CERRAR);
    }
}

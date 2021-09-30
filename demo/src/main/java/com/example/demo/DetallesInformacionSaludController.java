package com.example.demo;

import DataBaseConnection.InformacionSaludDAO;
import POJO.Estudiante;
import POJO.InformacionSalud;
import Utilidades.UtilVentanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallesInformacionSaludController implements Initializable, Controller {
    @FXML
    private Label labelNombreEstudiante;
    @FXML
    private Label labelNumeroSS;
    @FXML
    private Label labelNombreEntidad;
    @FXML
    private Label labelTipoSangre;

    private InformacionSalud informacionSalud;
    private Estudiante estudiante;
    private String tipoVentana;

    public DetallesInformacionSaludController(String tipoVentana, Estudiante estudiante) {
        this.tipoVentana = tipoVentana;
        this.estudiante = estudiante;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelNombreEstudiante.setText(estudiante.toString());
        this.labelNumeroSS.setText(String.valueOf(InformacionSaludDAO.getInformacionSaludeByIdEstudiante(estudiante.getId()).getNumeroSeguroSocial()));
        this.labelNombreEntidad.setText(InformacionSaludDAO.getInformacionSaludeByIdEstudiante(estudiante.getId()).getNombreEntidad());
        this.labelTipoSangre.setText(InformacionSaludDAO.getInformacionSaludeByIdEstudiante(estudiante.getId()).getGrupoSanguineo());
    }
}

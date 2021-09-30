package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import POJO.Estudiante;
import Utilidades.UtilVentanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class DetallesEstudianteController implements Initializable, Controller {
    @FXML
    private TextField tfPrimApe;
    @FXML
    private TextField tfSegApe;
    @FXML
    private TextField tfPrimNom;
    @FXML
    private TextField tfSegNom;
    @FXML
    private TextField tfOrigen;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    private Estudiante estudiante;
    private String tipoVentana;

    public DetallesEstudianteController(String tipoVentana) {
        this.tipoVentana = tipoVentana;
        this.estudiante = null;
    }

    public DetallesEstudianteController(String tipoVentana, Estudiante estudiante) {
        this.tipoVentana = tipoVentana;
        this.estudiante = estudiante;
    }

    @FXML
    protected void aceptar(ActionEvent actionEvent){
        if(tipoVentana.equals("Agregar")){
            if(verificarDatos()){
                Estudiante estudiante = new Estudiante(
                        tfPrimApe.getText(),
                        tfSegApe.getText(),
                        tfPrimNom.getText(),
                        tfSegNom.getText(),
                        true,
                        tfOrigen.getText());
                EstudianteDAO.guardar(estudiante);
            }
            else{
                UtilVentanas.alerta("No puede dejar los campos en blanco.", Alert.AlertType.WARNING);
            }
        }
        cerrarVentana();
    }

    @FXML
    protected void cancelar(ActionEvent actionEvent){
        if (UtilVentanas.alertaPregunta("¿Desea cancelar el registro?").get() == ButtonType.OK) {
            cerrarVentana();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(tipoVentana.equals("Consultar")){
            tfPrimApe.setEditable(false);
            tfPrimApe.setText(estudiante.getPrimerApe());
            tfSegApe.setEditable(false);
            tfSegApe.setText(estudiante.getSegundoApe());
            tfPrimNom.setEditable(false);
            tfPrimNom.setText(estudiante.getPrimerNom());
            tfSegNom.setEditable(false);
            tfSegNom.setText(estudiante.getSegundoNom());
            tfOrigen.setEditable(false);
            tfOrigen.setText(estudiante.getOrigen());

            btnCancelar.setVisible(false);
            btnCancelar.setManaged(false);
        }
    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        stage.close();
    }

    private boolean verificarDatos() {
        if(tfPrimApe.getText().isEmpty())
            return false;
        if(tfSegApe.getText().isEmpty())
            return false;
        if(tfPrimNom.getText().isEmpty())
            return false;

        return true;
    }
}

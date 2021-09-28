package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import POJO.Estudiante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class DetallesEstudiante extends Controller implements Initializable {
    @FXML
    private TextField tfPrimApe;
    @FXML
    private TextField tfSegApe;
    @FXML
    private TextField tfPrimNom;
    @FXML
    private TextField tfSegNom;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Estudiante estudiante;
    @FXML
    private String tipoVentana;

    public DetallesEstudiante(String tipoVentana) {
        this.tipoVentana = tipoVentana;
        this.estudiante = null;
    }

    public DetallesEstudiante(String tipoVentana, Estudiante estudiante) {
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
                        true);
                EstudianteDAO.guardar(estudiante);
            }
            else{
                alerta("No puede dejar los campos en blanco.");
            }
        }
        cerrarVentana();
    }

    @FXML
    protected void cancelar(ActionEvent actionEvent){
        if (alertaPregunta("¿Desea cancelar el registro?").get() == ButtonType.OK) {
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

            btnCancelar.setVisible(false);
            btnCancelar.setManaged(false);
        }
    }

    private void cerrarVentana(){
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        stage.close();
    }

    private void alerta(String texto){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(texto);
        alert.show();
    }

    private Optional<ButtonType> alertaPregunta(String pregunta){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(pregunta);

        return alert.showAndWait();
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

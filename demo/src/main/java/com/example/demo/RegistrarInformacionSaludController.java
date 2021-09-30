package com.example.demo;

import DataBaseConnection.InformacionSaludDAO;
import POJO.Estudiante;
import POJO.InformacionSalud;
import Utilidades.UtilVentanas;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class RegistrarInformacionSaludController extends Controller implements Initializable {
    @FXML
    private Label labelNombreEstudiante;
    @FXML
    private TextField txtFieldNumeroSS;
    @FXML
    private TextField txtFieldNombreEntidad;
    @FXML
    private ComboBox comboBoxTipoDeSangre;
    @FXML
    private Button buttonRegistrar;

    private ObservableList<String> tipoDeSangre = FXCollections.observableArrayList();

    private Estudiante estudiante;
    private String tipoVentana;

    public RegistrarInformacionSaludController(String tipoVentana, Estudiante estudiante) {
        this.tipoVentana = tipoVentana;
        this.estudiante = estudiante;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelNombreEstudiante.setText(estudiante.toString());

        tipoDeSangre.add("O negativo");
        tipoDeSangre.add("O positivo");
        tipoDeSangre.add("A negativo");
        tipoDeSangre.add("A positivo");
        tipoDeSangre.add("B negativo");
        tipoDeSangre.add("B positivo");
        tipoDeSangre.add("AB negativo");
        tipoDeSangre.add("AB positivo");

        if (null != comboBoxTipoDeSangre) {
            comboBoxTipoDeSangre.setItems(tipoDeSangre);
        }
    }

    @FXML
    protected void clickRegistrar(ActionEvent actionEvent){

        if(this.txtFieldNumeroSS.getText().isEmpty() || this.txtFieldNombreEntidad.getText().isEmpty() ||
                this.comboBoxTipoDeSangre.getSelectionModel().getSelectedItem() == null){

            Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
            alertaConfirmacion.setHeaderText(null);
            alertaConfirmacion.setTitle("Alerta");
            alertaConfirmacion.setContentText("Aegúrese de llenar todos los campos.");
            alertaConfirmacion.showAndWait();
        }else{
            InformacionSalud informacionSalud = new InformacionSalud();
            informacionSalud.setNumeroSeguroSocial(Integer.parseInt(this.txtFieldNumeroSS.getText()));
            informacionSalud.setNombreEntidad(this.txtFieldNombreEntidad.getText());
            informacionSalud.setGrupoSanguineo((String) this.comboBoxTipoDeSangre.getSelectionModel().getSelectedItem());
            informacionSalud.setIdEstudiante(estudiante.getId());

            try{
                InformacionSaludDAO informacionSaludDAO = new InformacionSaludDAO();
                informacionSaludDAO.guardar(informacionSalud);

                Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                alertaConfirmacion.setHeaderText(null);
                alertaConfirmacion.setTitle("Información");
                alertaConfirmacion.setContentText("Registro exitoso.");
                alertaConfirmacion.showAndWait();

                UtilVentanas.cerrar(buttonRegistrar);
            }catch (Exception ex){
                Alert alertaConfirmacion = new Alert(Alert.AlertType.WARNING);
                alertaConfirmacion.setHeaderText(null);
                alertaConfirmacion.setTitle("Alerta");
                alertaConfirmacion.setContentText("Error en la conexión." + ex);
                alertaConfirmacion.showAndWait();
            }
        }
    }
}

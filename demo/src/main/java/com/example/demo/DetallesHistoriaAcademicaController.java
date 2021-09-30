package com.example.demo;

import DataBaseConnection.EstudianteDAO;
import DataBaseConnection.HistoriaAcademicaDAO;
import POJO.Estudiante;
import POJO.HistoriaAcademica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class DetallesHistoriaAcademicaController  implements Initializable {

    public ComboBox<Estudiante> cbEstudiante;
    public TextField tfColegioAnterior;
    public TextField tfAño;
    public TextField tfGrado;
    public Button btnAceptar;
    public Button btnCancelar;
    private HistoriaAcademicaController historiaAcademicaController;
    private HistoriaAcademica historiaAcademica;
    private String tipoVentana;

    public DetallesHistoriaAcademicaController(HistoriaAcademicaController historiaAcademicaController, HistoriaAcademica historiaAcademica, String tipoVentana ){
        this.historiaAcademicaController = historiaAcademicaController;
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

//            ArrayList<HistoriaAcademica> arrayList = new ArrayList<>();
//            arrayList.add(historiaAcademica);
//            ObservableList<HistoriaAcademica> historiaAcademicasObs = FXCollections.observableArrayList(arrayList);
//
//            cbEstudiante.setItems(historiaAcademicasObs);
            Estudiante estudiante; //TODO = EstudianteDAO.getEstudianteById(historiaAcademica.getIdEstudiante());
            //cbEstudiante.getSelectionModel().select(estudiante);
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
                //TODO ADD ALERT DONE
            } else {
                //TODO ADD ALERT EMPTY FIELDS
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
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        stage.close();
    }
}

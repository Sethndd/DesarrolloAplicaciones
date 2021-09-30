package com.example.demo;

import DataBaseConnection.TutorDAO;
import POJO.Tutor;
import Utilidades.UtilVentanas;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

public class TutorTablaController implements Initializable, Controller {
    public TableColumn<Tutor, String> tc_primernombre;
    public TableColumn<Tutor, String> tc_segundonombre;
    public TableColumn<Tutor, String> tc_apellidopaterno;
    public TableColumn<Tutor, String> tc_apellidomaterno;
    public TableColumn<Tutor, String> tc_correoelectronico;
    public TableColumn<Tutor, String> tc_telefonocasa;
    public TableColumn<Tutor, String> tc_telefonomovil;
    public TableColumn<Tutor, String> tc_estudiante;
    public Button bt_consultar;
    public TableView<Tutor> tv_tutores;
    ObservableList<Tutor> tutorObs;
    List<Tutor> tutores;

    private Tutor tutor;

    public TutorTablaController() {}

    public void clicConsultar(ActionEvent actionEvent) {
        if(tutor == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Seleccione un tutor");
            alert.show();
        }else {
            TutorVisualizarController tutorVisualizarController = new TutorVisualizarController(tutor);
            UtilVentanas.iniciarVentana(this.tv_tutores, tutorVisualizarController, "TutorVisualizar.fxml", false);
        }
    }

    public void clicRegistrar(ActionEvent actionEvent) {
        TutorRegistrarController tutorRegistrarController = new TutorRegistrarController();
        UtilVentanas.iniciarVentana(this.tv_tutores, tutorRegistrarController, "TutorRegistrar.fxml", false);
        this.llenarTabla();
    }

    public void clicBorrar(ActionEvent actionEvent) {
        if(tutor == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Seleccione un tutor");
            alert.show();
        }else{
            TutorDAO.borrar(tutor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Usuario borrado exitosamete");
            alert.show();

            this.llenarTabla();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tutores = new ArrayList();
        this.llenarTabla();
    }

    public void llenarTabla() {
        this.tc_primernombre.setCellValueFactory(new PropertyValueFactory("primerNombreTutor"));
        this.tc_segundonombre.setCellValueFactory(new PropertyValueFactory("segundoNombreTutor"));
        this.tc_apellidopaterno.setCellValueFactory(new PropertyValueFactory("apellidoPatTutor"));
        this.tc_apellidomaterno.setCellValueFactory(new PropertyValueFactory("apellidoMatTutor"));
        this.tc_correoelectronico.setCellValueFactory(new PropertyValueFactory("correoElectronico"));
        this.tc_telefonocasa.setCellValueFactory(new PropertyValueFactory("telefonoFijo"));
        this.tc_telefonomovil.setCellValueFactory(new PropertyValueFactory("telefonoCelular"));
        this.tc_estudiante.setCellValueFactory(new PropertyValueFactory("nombreEstudiante"));
        this.tutores = TutorDAO.getTutores();
        this.tutorObs = FXCollections.observableArrayList(this.tutores);
        this.tv_tutores.setItems(this.tutorObs);
    }


    public void clicTutor(javafx.scene.input.MouseEvent mouseEvent) {
        tutor = this.tv_tutores.getSelectionModel().getSelectedItem();
    }
}

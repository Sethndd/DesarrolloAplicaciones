package com.example.demo;

import DataBaseConnection.ColegioDAO;
import POJO.Colegio;
import Utilidades.UtilVentanas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Utilidades.UtilVentanas.alerta;
import static Utilidades.UtilVentanas.cerrar;

public class ColegioController implements Initializable, Controller {

    @FXML public Button botonCancelar;
    @FXML private Button botonEliminar;
    @FXML private Button botonRegistrar;
    @FXML private Button botonLimpiar;
    @FXML private TableView<Colegio> colegioTableView;
    @FXML private TableColumn<Colegio, Integer> columnaIdColegio;
    @FXML private TableColumn<Colegio, String> columnaTipoColegio;
    @FXML private TableColumn<Colegio, String> columnaCiudad;
    @FXML private TableColumn<Colegio, String> columnaNombre;
    @FXML private TableColumn<Colegio, Integer> columnaEstado;
    @FXML private ComboBox<String> tipoColegioComboBox;
    @FXML private TextField ciudadTextField;
    @FXML private TextField nombreTextField;
    private ObservableList<Colegio> listaColegios;
    private ObservableList<String> elementosComboBoxTipoColegio;
    private final ColegioDAO colegioDAO;

    public ColegioController() {
        this.colegioDAO = new ColegioDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botonLimpiar.setDisable(true);
        llenarComboBox();
        llenarCamposConsultar();
        listaColegios = FXCollections.observableArrayList();
        colegioDAO.llenarTablaColegio(listaColegios);
        colegioTableView.setItems(listaColegios);
        enlazarColumnasConAtributos();
        validarTextFields();
        validarLargoTextFields(99);
    }

    private void llenarComboBox() {
        elementosComboBoxTipoColegio = FXCollections.observableArrayList("Privado", "Publico");
        tipoColegioComboBox.getItems().setAll(elementosComboBoxTipoColegio);
    }

    public void clicEliminar(ActionEvent actionEvent) {
        if (obtenerValorDeCelda() != 0) {
            int idColegio = obtenerValorDeCelda();
            Optional<ButtonType> respuesta = UtilVentanas.alertaPregunta
                    ("¿Está seguro que desea eliminar el registro seleccionado?");
            if (respuesta.get() == ButtonType.OK) {
                Colegio colegio = new Colegio();
                colegio.setIdColegio(idColegio);
                ColegioDAO colegioDAO = new ColegioDAO();
                if (colegioDAO.eliminarColegio(colegio)) {
                    UtilVentanas.alerta("¡Se ha eliminado el registro exitosamente!", Alert.AlertType.INFORMATION);
                    refrescarTableView();
                    //botonLimpiar.setDisable(true);
                } else {
                    UtilVentanas.alerta("Algo ha salido mal. Lamentamos las molestias que esto pueda ocasionarle. "
                            , Alert.AlertType.ERROR);
                }
            }
        } else {
            UtilVentanas.alerta("No se seleccionó ningún registro. Debe seleccionar un registro para eliminar"
                    , Alert.AlertType.INFORMATION);
        }
    }

    public void clicRegistrar(ActionEvent actionEvent) {
        if (!estanCamposVacios()) {
            String tipoColegio = (String) this.tipoColegioComboBox.getValue();
            String ciudad = this.ciudadTextField.getText().trim();
            String nombre = this.nombreTextField.getText().trim();
            guardarColegio(tipoColegio, ciudad, nombre);
        } else {
            UtilVentanas.alerta("Campos vacíos. Debe llenar todos los campos", Alert.AlertType.WARNING);
        }
    }

    private void guardarColegio(String tipoColegio, String ciudad, String nombre) {
        Colegio colegio = new Colegio();
        colegio.setTipoColegio(tipoColegio);
        colegio.setCiudad(ciudad);
        colegio.setNombre(nombre);
        colegio.setEstado(true);
        ColegioDAO colegioDAO = new ColegioDAO();
        Optional<ButtonType> respuesta = UtilVentanas.alertaPregunta
                ("¿Está seguro que desea realizar el registro con estos datos?");
        if(respuesta.get() == ButtonType.OK){
            if (colegioDAO.agregarColegio(colegio)) {
                UtilVentanas.alerta("¡Se ha realizado el registro exitosamente!", Alert.AlertType.INFORMATION);
                refrescarTableView();
                limpiarTextField();
            } else {
                UtilVentanas.alerta("Algo ha salido mal. Lamentamos las molestias que esto pueda ocasionarle. "
                        , Alert.AlertType.ERROR);
            }
        }
    }

    public final Integer obtenerValorDeCelda() {
        int idColegio = 0;
        if (colegioTableView.getSelectionModel().getSelectedItem() != null) {
            TablePosition posicion = (TablePosition) colegioTableView.getSelectionModel()
                    .getSelectedCells().get(0);
            int fila = posicion.getRow();
            Colegio elemento = (Colegio) colegioTableView.getItems().get(fila);
            TableColumn columna = columnaIdColegio;
            idColegio = (Integer) columna.getCellObservableValue(elemento).getValue();
        }
        return idColegio;
    }

    private int getIndexListaTipoColegio(String tipoColegio) {
        int valor = 0;
        if (elementosComboBoxTipoColegio.size() > 0) {
            for (int i = 0; i < elementosComboBoxTipoColegio.size(); i++) {
                String get = elementosComboBoxTipoColegio.get(i);
                if (get == null ? tipoColegio == null : get.equals(tipoColegio)) {
                    return i;
                }
            }
        }
        return valor;
    }

    private void llenarCamposConsultar() {
        colegioTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Colegio>() {
                    @Override
                    public void changed(ObservableValue<? extends Colegio> observable
                            , Colegio valorAnterior, Colegio valorSeleccionado) {
                        if (valorSeleccionado != null) {
                            int posTipoUsuario = getIndexListaTipoColegio(valorSeleccionado.getTipoColegio());
                            tipoColegioComboBox.getSelectionModel().select(posTipoUsuario);
                            ciudadTextField.setText(valorSeleccionado.getCiudad());
                            nombreTextField.setText(valorSeleccionado.getNombre());
                            botonLimpiar.setDisable(false);
                            botonRegistrar.setDisable(true);
                            botonEliminar.setDisable(false);
                        }
                    }
                });
    }

    public void clicLimpiar(ActionEvent actionEvent) {
        limpiarTextField();
    }

    public final void validarTextFields() {
        nombreTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable
                    , String valorViejo, String valorNuevo) {
                if (!valorNuevo.matches("^[a-zA-Z0-9]+$")) {
                    nombreTextField.setText(valorNuevo.replaceAll("[^\\s|a-zA-Z0-9]", ""));
                }
            }
        });
        ciudadTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable
                    , String valorViejo, String valorNuevo) {
                if (!valorNuevo.matches("^[a-zA-Z0-9]+$")) {
                    nombreTextField.setText(valorNuevo.replaceAll("[^\\sa-zA-Z0-9]", ""));
                }
            }
        });
    }

    public void validarLargoTextFields(final int tamañoMaximo) {
        nombreTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (nombreTextField.getText().length() > tamañoMaximo) {
                        nombreTextField.setText(nombreTextField.getText()
                                .substring(0, tamañoMaximo));
                        alerta("Límite excedido. El límite es de 99 caracteres", Alert.AlertType.WARNING);
                    }
                }
            }
        });
        ciudadTextField.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (ciudadTextField.getText().length() > tamañoMaximo) {
                        ciudadTextField.setText(ciudadTextField.getText()
                                .substring(0, tamañoMaximo));
                        alerta("Límite excedido. El límite es de 99 caracteres", Alert.AlertType.WARNING);
                    }
                }
            }
        });
    }

    private void enlazarColumnasConAtributos() {
        columnaIdColegio.setCellValueFactory(new PropertyValueFactory<Colegio, Integer>("idColegio"));
        columnaTipoColegio.setCellValueFactory(new PropertyValueFactory<Colegio, String> ("tipoColegio"));
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<Colegio, String>("ciudad"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Colegio, String>("nombre"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<Colegio, Integer> ("estado"));
    }

    private void limpiarTextField() {
        tipoColegioComboBox.setValue(null);
        ciudadTextField.setText("");
        nombreTextField.setText("");
        botonLimpiar.setDisable(true);
        botonRegistrar.setDisable(false);
        botonEliminar.setDisable(true);
    }

    private void refrescarTableView() {
        listaColegios.clear();
        listaColegios = FXCollections.observableArrayList();
        colegioDAO.llenarTablaColegio(listaColegios);
        colegioTableView.setItems(listaColegios);
        enlazarColumnasConAtributos();
        //botonRegistrar.setDisable(true);
    }

    private boolean estanCamposVacios() {
        return (nombreTextField.getText().trim().isEmpty() ||
                ciudadTextField.getText().trim().isEmpty() ||
                tipoColegioComboBox.getValue() == null );
    }

    public void clicCancelar(ActionEvent actionEvent) {
        cerrar(botonCancelar);
    }
}

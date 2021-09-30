package Utilidades;

import com.example.demo.Controller;
import com.example.demo.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class UtilVentanas {
    public static final boolean CERRAR = true;
    public static final boolean NO_CERRAR = false;

    public static void iniciarVentana(Control control, Controller controller, String nombreVentana, boolean cerrarVentana){
        try{
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(nombreVentana));
            loader.setController(controller);
            Stage stage = new Stage();
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            if(cerrarVentana){
                cerrar(control);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void cerrar(Control control) {
        Stage stage = (Stage) control.getScene().getWindow();
        stage.close();
    }

    public static void alerta(String texto, Alert.AlertType tipoAlerta){
        Alert alert = new Alert(tipoAlerta);
        alert.setHeaderText(texto);
        alert.show();
    }

    public static Optional<ButtonType> alertaPregunta(String pregunta){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(pregunta);

        return alert.showAndWait();
    }
}

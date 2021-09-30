package Utilidades;

import com.example.demo.Controller;
import com.example.demo.EstudianteTablaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControladorVentanas {
    public static void abrirYEsperar(Controller controller, String nombreFXML){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(EstudianteTablaController.class.getResource(nombreFXML + ".fxml"));
        fxmlLoader.setController(controller);
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
            stage.setTitle(nombreFXML);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void alerta(String texto){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(texto);
        alert.show();
    }

    public static Optional<ButtonType> alertaPregunta(String pregunta){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(pregunta);

        return alert.showAndWait();
    }
}

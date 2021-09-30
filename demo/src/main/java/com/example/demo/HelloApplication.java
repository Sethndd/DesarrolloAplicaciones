package com.example.demo;

import Utilidades.UtilVentanas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        UtilVentanas.iniciarVentana(new Button(), new MenuPrincipalController(), "MenuPrincipal.fxml", false);
    }

    public static void main(String[] args) {
        launch();
    }
}
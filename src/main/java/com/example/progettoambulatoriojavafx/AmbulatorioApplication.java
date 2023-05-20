package com.example.progettoambulatoriojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AmbulatorioApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AmbulatorioApplication.class.getResource("AmbulatorioMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Pronto Soccorso");
        stage.setScene(scene);
        //stage.getIcons().add(new Image("C:\\Users\\giuse\\Desktop\\gabriele\\informatica\\Progetto Ambulatorio JavaFX 2\\src\\main\\resources\\com\\example\\progettoambulatoriojavafx\\sfondo.png"));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

/*
* finestra ambulatario
* */
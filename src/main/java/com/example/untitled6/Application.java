package com.example.untitled6;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    public static ApplicationLogic applicationLogic;

    static {
        applicationLogic = new ApplicationLogic();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("UI.fxml")));
        Scene scene = new Scene(parent, 1590, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
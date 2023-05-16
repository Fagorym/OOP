package ru.nsu.fit.oop.veber.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SnakeGameFXML extends Application implements MyApplication {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main.fxml")));
        primaryStage.setTitle("Snake game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void startGameProcess() {
        launch();
    }
}

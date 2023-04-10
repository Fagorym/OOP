package ru.nsu.fit.oop.veber;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.application.SnakeGame;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        args[0] = "graphical";
        switch (args[0]) {
            case "graphical" -> launch();
            case "console" -> new SnakeGame().launch();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
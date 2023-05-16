package ru.nsu.fit.oop.veber.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Scene manager.
 */
public class SceneManager {
    /**
     * Function, that changed current scene.
     *
     * @param fxmlPath path for fxml document for new scene.
     * @param scene    current scene.
     */
    public void changeScene(String fxmlPath, Scene scene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlPath));
            Parent newRoot = fxmlLoader.load();

            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function for exiting from game.
     *
     * @param scene current scene.
     */

    public void exit(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}

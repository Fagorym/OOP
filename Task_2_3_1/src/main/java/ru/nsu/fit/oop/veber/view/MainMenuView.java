package ru.nsu.fit.oop.veber.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuView extends AbstractView implements Initializable {


    @FXML
    private Button exitButton;

    public MainMenuView() {
        super();
    }

    @FXML
    void handleSettingsButton() {
        sceneManager.changeScene("settings.fxml", exitButton.getScene());
    }

    @FXML
    void handlePlayButton() {
        sceneManager.changeScene("game.fxml", exitButton.getScene());
    }

    @FXML
    void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package ru.nsu.fit.oop.veber.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Main menu view.
 */

public class MainMenuView extends AbstractView implements Initializable {


    @FXML
    public Button settingsButton;

    @FXML
    public Button playButton;

    @FXML
    public AnchorPane rootPane;
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
        sceneManager.exit(exitButton.getScene());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

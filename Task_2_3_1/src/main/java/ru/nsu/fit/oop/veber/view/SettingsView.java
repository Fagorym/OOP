package ru.nsu.fit.oop.veber.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

public class SettingsView extends AbstractView {
    @FXML
    public Slider gameSpeedSlider;
    @FXML
    public Button saveButton;
    @FXML
    public Button backButton;
    @FXML
    public AnchorPane settingsPane;

    @FXML
    public void handleSaveButton() {
        double value = gameSpeedSlider.getValue();
        GameConfiguration.getGameConfiguration().setGameSpeed(value);
    }

    @FXML
    public void handleBackButton() {
        sceneManager.changeScene("main.fxml", gameSpeedSlider.getScene());
    }
}

package ru.nsu.fit.oop.veber.view;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

public class SettingsView extends AbstractView {
    public Slider gameSpeedSlider;
    public Button saveButton;
    public Button backButton;
    public AnchorPane settingsPane;

    public void handleSaveButton() {
        double value = gameSpeedSlider.getValue();
        GameConfiguration.getGameConfiguration().setGameSpeed(value);
        sceneManager.changeScene("main.fxml", gameSpeedSlider.getScene());
    }

    public void handleBackButton() {
        sceneManager.changeScene("main.fxml", gameSpeedSlider.getScene());
    }
}

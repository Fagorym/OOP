package ru.nsu.fit.oop.veber.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

import java.util.HashMap;
import java.util.Map;

public class SettingsView extends AbstractView {
    private final Map<String, Color> nameToColor;
    @FXML
    public Slider gameSpeedSlider;
    @FXML
    public Button saveButton;
    @FXML
    public Button backButton;
    @FXML
    public AnchorPane settingsPane;
    @FXML
    public ChoiceBox<String> colorChoice;

    public SettingsView() {
        nameToColor = new HashMap<>();
        nameToColor.put("Красная", Color.RED);
        nameToColor.put("Зеленая", Color.GREEN);
        nameToColor.put("Синяя", Color.BLUE);
    }

    @FXML
    public void handleSaveButton() {
        double value = gameSpeedSlider.getValue();
        GameConfiguration.getGameConfiguration().setGameSpeed(value);
        String colorString = colorChoice.getValue();
        Color color = nameToColor.get(colorString);
        GameConfiguration.getGameConfiguration().setSnakeColor(color);
    }

    @FXML
    public void handleBackButton() {
        sceneManager.changeScene("main.fxml", gameSpeedSlider.getScene());
    }
}

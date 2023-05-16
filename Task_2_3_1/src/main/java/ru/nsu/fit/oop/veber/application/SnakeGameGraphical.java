package ru.nsu.fit.oop.veber.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.view.GameView;
import ru.nsu.fit.oop.veber.view.GraphicalView;

public class SnakeGameGraphical extends Application implements MyApplication {
    @Override
    public void start(Stage stage) {
        GameView view = new GraphicalView(stage);

    }

    @Override
    public void startGameProcess() {
        launch();
    }
}

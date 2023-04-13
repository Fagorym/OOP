package ru.nsu.fit.oop.veber.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterGraphical;

public class SnakeGameGraphical extends Application implements MyApplication {
    private static final int TIMER_TICK = 1000000000;

    @Override
    public void start(Stage stage) {
        Presenter presenter = new PresenterGraphical(stage);
        presenter.startGameProcess();
    }

    @Override
    public void startGameProcess() {
        launch();
    }
}

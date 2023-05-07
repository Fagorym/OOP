package ru.nsu.fit.oop.veber.view;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.dto.GraphicalDto;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterGraphic;
import ru.nsu.fit.oop.veber.timer.GraphicalTimer;
import ru.nsu.fit.oop.veber.timer.Timer;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameViewImpl extends AbstractView implements Initializable, GameView {

    @FXML
    public Canvas gameCanvas;
    @FXML
    public VBox box;
    Presenter presenter;

    public GameViewImpl() {
        super();
    }

    @FXML
    void handleKeyPressed(KeyEvent keyEvent) {
        presenter.processKeyInput(keyEvent);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.presenter = new PresenterGraphic(this);
        box.setOnKeyPressed(presenter::processKeyInput);
        gameCanvas.setOnKeyPressed(presenter::processKeyInput);
        presenter.start();
    }

    @Override
    public void endGame() {
        Stage stage = (Stage) gameCanvas.getScene().getWindow();
        stage.close();
    }

    @Override
    public void clearScreen() {
        gameCanvas.getGraphicsContext2D().setFill(Color.WHITE);
        gameCanvas.getGraphicsContext2D().fillRect(0, 0, 1000, 1000);
    }

    @Override
    public void render() {
        List<GraphicalDto> dtoList = presenter.getDtoList();
        dtoList.forEach(this::render);
    }

    private void render(GraphicalDto dto) {
        gameCanvas.getGraphicsContext2D().setFill(dto.getColor());
        gameCanvas.getGraphicsContext2D().fillRect(
                dto.getX() * dto.getBlockSize(),
                dto.getY() * dto.getBlockSize(),
                dto.getBlockSize(),
                dto.getBlockSize()
        );
    }

    @Override
    public Timer setTimer(Runnable gameStep) {
        return new GraphicalTimer(new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                double TIMER_TICK = 100_000_000 / GameConfiguration.getGameConfiguration().getGameSpeed();
                if (now - lastTick > TIMER_TICK || lastTick == 0) {
                    lastTick = now;
                    gameStep.run();
                }
            }
        });
    }
}

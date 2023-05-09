package ru.nsu.fit.oop.veber.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.dto.graphical.GraphicalDto;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterGraphic;
import ru.nsu.fit.oop.veber.timer.GraphicalTimer;
import ru.nsu.fit.oop.veber.timer.Timer;

import java.util.List;

public class GraphicalView implements GameView {


    private final Presenter presenter;
    private final Scene scene;

    private final GraphicsContext context;
    private final int TIMER_TICK = 1_000_000_00;

    private final double SCREEN_LENGTH = 1000;
    private final double SCREEN_HEIGHT = 1000;


    public GraphicalView(Stage stage) {
        this.presenter = new PresenterGraphic(this);

        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_LENGTH, SCREEN_HEIGHT);
        context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        scene = new Scene(root, SCREEN_LENGTH, SCREEN_HEIGHT);

        scene.setOnKeyPressed(presenter::processKeyInput);
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.setResizable(true);
        stage.show();

        presenter.start();

    }

    @Override
    public void endGame() {
        clearScreen();
        context.setFill(Color.BLACK);
        context.setFont(Font.font(32));
        context.fillText("GAME OVER", SCREEN_LENGTH / 2, SCREEN_HEIGHT / 2);
        context.fillText("PRESS ANY KEY TO END", SCREEN_LENGTH / 2, SCREEN_HEIGHT / 2 + 50);
        scene.setOnKeyPressed((event) -> System.exit(0));

    }

    @Override
    public void clearScreen() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, SCREEN_LENGTH, SCREEN_HEIGHT);

    }

    @Override
    public void render() {
        List<GraphicalDto> dtoList = presenter.getDtoList();
        dtoList.forEach(this::render);
    }

    public void render(GraphicalDto dto) {
        context.setFill(dto.getColor());
        context.fillRect(dto.getX() * dto.getBlockSize(),
                dto.getY() * dto.getBlockSize(),
                dto.getBlockSize(),
                dto.getBlockSize());
    }

    @Override
    public Timer setTimer(Runnable step) {
        return new GraphicalTimer(new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > TIMER_TICK || lastTick == 0) {
                    lastTick = now;
                    step.run();
                }
            }
        });
    }

    @Override
    public void pause() {

    }
}

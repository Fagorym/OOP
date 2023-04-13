package ru.nsu.fit.oop.veber.application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SnakeGameGraphical extends Application implements MyApplication {
    private static final int TIMER_TICK = 1000000000;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Canvas canvas = new Canvas(600, 800);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 600, 800);

        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > TIMER_TICK / 500 || lastTick == 0) {
                    lastTick = now;
                }
            }
        }.start();

        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void startGameProcess() {
        launch();
    }
}

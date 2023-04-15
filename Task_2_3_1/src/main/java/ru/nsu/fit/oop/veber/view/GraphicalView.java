package ru.nsu.fit.oop.veber.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterImpl;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

public class GraphicalView implements View {


    private final Presenter presenter;

    private final AnimationTimer timer;

    private final Scene scene;

    private final GraphicsContext context;
    private final int TIMER_TICK = 1_000_000_00;

    private final int SCREEN_LENGTH;
    private final int SCREEN_HEIGHT;

    public GraphicalView(Stage stage) {
        GameConfiguration gameConfiguration = GameConfiguration.getINSTANCE();
        SCREEN_LENGTH = gameConfiguration.getGRAPHIC_SCREEN_LENGTH();
        SCREEN_HEIGHT = gameConfiguration.getGRAPHIC_SCREEN_HEIGHT();
        this.presenter = new PresenterImpl(this, SCREEN_HEIGHT, SCREEN_LENGTH);

        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_LENGTH, SCREEN_HEIGHT);
        context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        scene = new Scene(root, SCREEN_LENGTH, SCREEN_HEIGHT);

        timer = new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > TIMER_TICK || lastTick == 0) {
                    lastTick = now;
                    presenter.startGameProcess();
                }
            }
        };

        timer.start();
        scene.setOnKeyPressed(presenter::processKeyInput);
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.setResizable(true);
        stage.show();

    }

    @Override
    public void renderSnake(Snake snake) {
        context.setFill(Color.LIGHTGREEN);
        drawObject(snake.getHeadBlock());
        for (SnakeBlock bodyBlock : snake.getBody()) {
            drawObject(bodyBlock);
        }
        drawObject(snake.getTailBlock());
    }

    @Override
    public void endGame() {
        clearScreen();
        context.setFill(Color.BLACK);
        context.setFont(Font.font(32));
        context.fillText("GAME OVER", SCREEN_LENGTH / 2, SCREEN_HEIGHT / 2);
        context.fillText("PRESS ANY KEY TO END", SCREEN_LENGTH / 2, SCREEN_HEIGHT / 2 + 50);
        timer.stop();
        scene.setOnKeyPressed((event) -> System.exit(0));

    }

    @Override
    public void clearScreen() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, SCREEN_LENGTH, SCREEN_HEIGHT);

    }

    @Override
    public void refreshScreen() {
    }

    @Override
    public void renderFood(Food food) {
        context.setFill(Color.ORANGERED);
        drawObject(food);
    }

    @Override
    public void renderBackground(Box box) {
        context.setFill(Color.GREY);
        for (Cell cell : box.getCells()) {
            drawObject(cell);
        }
    }


    private void drawObject(GeometricalObject obj) {
        final int DEFAULT_BLOCK_SIZE = 10;
        context.fillRect(obj.getX() * DEFAULT_BLOCK_SIZE,
                obj.getY() * DEFAULT_BLOCK_SIZE,
                DEFAULT_BLOCK_SIZE,
                DEFAULT_BLOCK_SIZE);
    }
}

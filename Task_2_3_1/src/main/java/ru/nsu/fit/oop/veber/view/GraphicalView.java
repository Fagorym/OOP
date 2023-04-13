package ru.nsu.fit.oop.veber.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterImpl;

public class GraphicalView implements View {

    private final Stage stage;

    private final Presenter presenter;

    private final GraphicsContext context;
    private final int TIMER_TICK = 1000000000;

    private final int DEFAULT_BLOCK_SIZE = 10;


    public GraphicalView(Stage stage) {
        this.stage = stage;
        this.presenter = new PresenterImpl(this);

        VBox root = new VBox();
        Canvas canvas = new Canvas(1280, 1024);
        context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1280, 1024);

        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > TIMER_TICK || lastTick == 0) {
                    lastTick = now;
                    presenter.startGameProcess();
                }
            }
        }.start();

        scene.setOnKeyPressed((keyEvent) -> presenter.processKeyInput(keyEvent.getCharacter().charAt(0)));
        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.setResizable(true);
        stage.show();

    }

    @Override
    public void renderSnake(Snake snake) {
        context.setFill(Color.LIGHTGREEN);
        drawObject(snake.getHeadBlock());
    }

    @Override
    public void endGame() {
    }

    @Override
    public void clearScreen() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, 1024, 1280);

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
        context.fillRect(obj.getX() * DEFAULT_BLOCK_SIZE,
                obj.getY() * DEFAULT_BLOCK_SIZE,
                DEFAULT_BLOCK_SIZE,
                DEFAULT_BLOCK_SIZE);
    }
}

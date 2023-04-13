package ru.nsu.fit.oop.veber.presenter;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.CollisionChecker;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.view.GraphicalView;
import ru.nsu.fit.oop.veber.view.View;

public class PresenterGraphical implements Presenter {

    private final Snake snake;
    private final Food food;
    private final Box box;
    private final CollisionChecker collisionChecker;
    private final Stage stage;
    private final int TIMER_TICK = 1000;
    private View graphicalView;

    public PresenterGraphical(Stage stage) {
        snake = new Snake(10, 10);
        food = new Food(3, 5);
        box = new Box(50, 15);
        collisionChecker = new CollisionChecker();
        collisionChecker.addObjects(box.getCells());
        collisionChecker.addObject(food);
        collisionChecker.addObject(snake.getTailBlock());
        this.stage = stage;
    }

    @Override
    public void processKeyInput(Character character) {

    }

    @Override
    public void startGameProcess() {
        VBox root = new VBox();
        Canvas canvas = new Canvas(600, 800);
        GraphicsContext context = canvas.getGraphicsContext2D();
        graphicalView = new GraphicalView(this, context);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 600, 800);

        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (now - lastTick > TIMER_TICK || lastTick == 0) {
                    lastTick = now;
                    graphicalView.renderSnake(snake);
                    snake.move();
                }
            }
        }.start();

        stage.setScene(scene);
        stage.setTitle("Snake Game");
        stage.setResizable(false);
        stage.show();

    }
}

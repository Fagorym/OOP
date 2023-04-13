package ru.nsu.fit.oop.veber.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.presenter.Presenter;

public class GraphicalView implements View {

    private final Presenter presenter;

    private final int TIMER_TICK = 1000000000;
    private GraphicsContext context;


    public GraphicalView(Presenter presenter, GraphicsContext context) {
        this.presenter = presenter;
        this.context = context;
    }

    private void createScene(Stage stage) {
    }

    @Override
    public void renderSnake(Snake snake) {
        context.setFill(Color.LIGHTGREEN);
        context.fillRect(snake.getHeadBlock().getX(), snake.getHeadBlock().getY(), 1, 1);
    }

    @Override
    public void gameProcess(Snake snake, Food food, Box box) {

    }

    @Override
    public void endGame() {
    }
}

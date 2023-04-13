package ru.nsu.fit.oop.veber.presenter;

import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.CollisionChecker;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.View;

public class PresenterImpl implements Presenter {

    private final View view;
    private final Snake snake;
    private final Food food;
    private final Box box;
    private final CollisionChecker collisionChecker;


    public PresenterImpl(View view) {
        this.view = view;
        snake = new Snake(10, 10);
        food = new Food(3, 5);
        box = new Box(50, 15);
        collisionChecker = new CollisionChecker();
        collisionChecker.addObjects(box.getCells());
        collisionChecker.addObject(food);
        collisionChecker.addObject(snake.getTailBlock());
    }

    @Override
    public void processKeyInput(KeyEvent event) {

        Direction direction = switch (event.getCode()) {
            case RIGHT, D -> Direction.right;
            case LEFT, A -> Direction.left;
            case UP, W -> Direction.up;
            case DOWN, S -> Direction.down;
            default -> null;
        };
        snake.setDirection(direction);
    }

    @Override
    public void startGameProcess() {
        collisionChecker.removeObject(snake.getTailBlock());
        collisionChecker.addObject(snake.getHeadBlock());
        snake.move();
        switch (collisionChecker.checkCollision(snake.getHeadBlock())) {
            case SNAKE, WALL, NOTHING -> {
            }
            case FOOD -> {
                collisionChecker.removeObject(food);
                food.generate(box, collisionChecker);
                collisionChecker.addObject(food);
                collisionChecker.addObject(snake.generateNewSnakeBlock());
            }
        }
        view.clearScreen();
        view.renderFood(food);
        view.renderBackground(box);
        view.renderSnake(snake);
        view.refreshScreen();
    }
}

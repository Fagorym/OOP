package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.CollisionChecker;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.ConsoleView;
import ru.nsu.fit.oop.veber.view.View;

import java.util.ResourceBundle;


public class PresenterConsole implements Presenter {

    private final ResourceBundle bundle = ResourceBundle.getBundle("config");
    private final Snake snake;

    private final Food food;

    private final Box box;

    private final CollisionChecker collisionChecker;
    private final View consoleView;

    public PresenterConsole() {
        snake = new Snake(10, 10);
        food = new Food(3, 5);
        box = new Box(50, 15);
        collisionChecker = new CollisionChecker();
        collisionChecker.addObjects(box.getCells());
        collisionChecker.addObject(food);
        collisionChecker.addObject(snake.getTailBlock());
        consoleView = new ConsoleView(this);
    }

    public void startGameProcess() {
        boolean gameProcessActive = true;
        while (gameProcessActive) {
            consoleView.gameProcess(snake, food, box);
            collisionChecker.removeObject(snake.getTailBlock());
            collisionChecker.addObject(snake.getHeadBlock());
            snake.move();
            switch (collisionChecker.checkCollision(snake.getHeadBlock())) {
                case SNAKE, WALL -> gameProcessActive = false;
                case FOOD -> {
                    collisionChecker.removeObject(food);
                    food.generate(box, collisionChecker);
                    collisionChecker.addObject(food);
                    collisionChecker.addObject(snake.generateNewSnakeBlock());
                }
                case NOTHING -> {
                }
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        consoleView.endGame();
    }


    @Override
    public void processKeyInput(Character event) {

        Direction direction = switch (event) {
            case 'w' -> Direction.up;
            case 'd' -> Direction.right;
            case 's' -> Direction.down;
            default -> Direction.left;
        };
        snake.setDirection(direction);
    }
}

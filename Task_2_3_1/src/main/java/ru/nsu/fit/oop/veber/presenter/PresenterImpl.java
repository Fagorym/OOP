package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.CollisionChecker;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;
import ru.nsu.fit.oop.veber.view.View;

public class PresenterImpl implements Presenter {

    private final View view;
    private final Snake snake;
    private final Food food;
    private final Box box;
    private final CollisionChecker collisionChecker;


    public PresenterImpl(View view, int screenHeight, int screenLength) {
        GameConfiguration gameConfiguration = GameConfiguration.getINSTANCE();
        this.view = view;
        snake = new Snake(gameConfiguration.getSNAKE_INITIAL_COORDINATE_X(), gameConfiguration.getFOOD_INITIAL_COORDINATE_Y());
        food = new Food(gameConfiguration.getFOOD_INITIAL_COORDINATE_X(), gameConfiguration.getFOOD_INITIAL_COORDINATE_Y());
        box = new Box(50, 30);
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
    public void processKeyInput(KeyStroke stroke) {
        Direction direction = switch (stroke.getCharacter()) {
            case 'W' -> Direction.up;
            case 'D' -> Direction.right;
            case 'S' -> Direction.down;
            case 'A' -> Direction.left;
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
            case SNAKE, WALL -> {
                view.endGame();
                return;
            }
            case FOOD -> {
                collisionChecker.removeObject(food);
                food.generate(box, collisionChecker);
                collisionChecker.addObject(snake.generateNewSnakeBlock());
            }
            case NOTHING -> {

            }
        }
        view.clearScreen();
        view.renderFood(food);
        view.renderBackground(box);
        view.renderSnake(snake);
        view.refreshScreen();
    }
}

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
            case RIGHT, D -> Direction.RIGHT;
            case LEFT, A -> Direction.LEFT;
            case UP, W -> Direction.UP;
            case DOWN, S -> Direction.DOWN;
            default -> null;
        };
        snake.setDirection(direction);
    }

    @Override
    public void processKeyInput(KeyStroke stroke) {
        Direction direction = switch (stroke.getCharacter()) {
            case 'W' -> Direction.UP;
            case 'D' -> Direction.RIGHT;
            case 'S' -> Direction.DOWN;
            case 'A' -> Direction.LEFT;
            default -> null;
        };
        snake.setDirection(direction);
    }

    @Override
    public void makeGameStep() {
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

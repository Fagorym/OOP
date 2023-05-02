package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.dto.BaseDto;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.renderer.Converter;
import ru.nsu.fit.oop.veber.timer.Timer;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPresenter implements Presenter {
    protected final Environment environment;
    protected final Snake snake;
    protected final View view;

    protected final Food food;

    protected final Box box;

    protected final CollisionChecker collisionChecker;

    protected final Timer timer;

    public AbstractPresenter(View view) {
        this.view = view;
        this.environment = new Environment();
        snake = environment.getSnake();
        food = environment.getFood();
        box = environment.getBox();
        collisionChecker = environment.getCollisionChecker();
        timer = view.setTimer(this::makeGameStep);
    }

    @Override
    public void makeGameStep() {
        collisionChecker.removeObject(snake.getTailBlock());
        collisionChecker.addObject(snake.getHeadBlock());
        snake.move();
        switch (collisionChecker.checkCollision(snake.getHeadBlock())) {
            case SNAKE, WALL -> {
                view.endGame();
                timer.stop();
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
        view.render();
    }

    @Override
    public void start() {
        timer.start();
    }

    protected <T extends BaseDto> List<T> getDtoList(Converter<T> converter) {
        List<T> dtoList = new ArrayList<>();
        dtoList.add(converter.convert(snake.getHeadBlock()));
        dtoList.addAll(snake.getBody().stream().map(converter::convert).toList());
        dtoList.add(converter.convert(snake.getTailBlock()));
        dtoList.add(converter.convert(food));
        dtoList.addAll(box.getWalls().stream().map(converter::convert).toList());
        return dtoList;
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
}

package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.dto.BaseDto;
import ru.nsu.fit.oop.veber.model.*;
import ru.nsu.fit.oop.veber.renderer.Converter;
import ru.nsu.fit.oop.veber.timer.Timer;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.GameView;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPresenter implements Presenter {
    protected final Environment environment;
    protected final Snake snake;
    protected final GameView view;

    protected final List<Food> food;
    protected final Box box;
    protected final CollisionChecker collisionChecker;
    protected final Timer timer;
    protected List<ComputedSnake> computedSnakes;

    public AbstractPresenter(GameView view) {
        this.view = view;
        this.environment = new Environment();
        snake = environment.getSnake();
        food = environment.getFood();
        box = environment.getBox();
        computedSnakes = environment.getComputedSnake();
        collisionChecker = environment.getCollisionChecker();
        timer = view.setTimer(this::makeGameStep);
    }

    @Override
    public void makeGameStep() {
        computedSnakes = computedSnakes.stream().filter(ComputedSnake::isNotDead).toList();
        //collisionChecker.removeObject(snake.getTailBlock());
        //collisionChecker.addObject(snake.getHeadBlock());
        // snake.move();
        computedSnakes.forEach(
                snake -> {
                    collisionChecker.removeObject(snake.getTailBlock());
                    collisionChecker.addObject(snake.getHeadBlock());
                    snake.computeDirection(environment.getCollisionChecker().getObjects());
                    snake.move();
                    snake.checkCollision(collisionChecker, box);
                }
        );
        /*BoxElement collisionElement = collisionChecker.checkCollision(snake.getHeadBlock());
        switch (collisionElement.getObjectType()) {
            case SNAKE, WALL -> {
                view.endGame();
                timer.stop();
                return;
            }
            case FOOD -> {
                Food food = (Food) collisionElement;
                collisionChecker.removeObject(food);
                food.getNewCoordinates(box, collisionChecker);
                collisionChecker.addObject(snake.generateNewSnakeBlock());
            }
            case NOTHING -> {

            }
        }

         */
        view.clearScreen();
        view.render();
    }

    @Override
    public void start() {
        timer.start();
    }

    protected <T extends BaseDto> List<T> getDtoList(Converter<T> converter) {
        List<T> dtoList = new ArrayList<>();
        //dtoList.add(converter.convert(snake.getHeadBlock()));
        //dtoList.addAll(snake.getBody().stream().map(converter::convert).toList());
        //dtoList.add(converter.convert(snake.getTailBlock()));
        for (ComputedSnake snake : computedSnakes) {
            dtoList.add(converter.convert(snake.getHeadBlock()));
            dtoList.addAll(snake.getBody().stream().map(converter::convert).toList());
            dtoList.add(converter.convert(snake.getTailBlock()));
        }
        dtoList.addAll(food.stream().map(converter::convert).toList());
        dtoList.addAll(box.getWalls().stream().map(converter::convert).toList());
        return dtoList;
    }

    @Override
    public void processKeyInput(KeyEvent event) {
        switch (event.getCode()) {
            case RIGHT, D -> snake.setDirection(Direction.RIGHT);
            case LEFT, A -> snake.setDirection(Direction.LEFT);
            case UP, W -> snake.setDirection(Direction.UP);
            case DOWN, S -> snake.setDirection(Direction.DOWN);
            case ESCAPE -> {
                view.pause();
                timer.stop();
            }
        }
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

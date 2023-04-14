package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.CollisionChecker;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.View;

import java.util.ResourceBundle;


public class PresenterConsole implements Presenter {

    private final ResourceBundle bundle = ResourceBundle.getBundle("config");
    private final Snake snake;

    private final Food food;

    private final Box box;

    private final CollisionChecker collisionChecker;
    private final View view;

    public PresenterConsole(View view) {
        snake = new Snake(10, 10);
        food = new Food(3, 5);
        box = new Box(50, 15);
        collisionChecker = new CollisionChecker();
        collisionChecker.addObjects(box.getCells());
        collisionChecker.addObject(food);
        collisionChecker.addObject(snake.getTailBlock());
        this.view = view;
    }


    public void startGameProcess() {
        boolean gameProcessActive = true;
        while (gameProcessActive) {
            // view.gameProcess(snake, food, box);
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
        view.endGame();
    }


    @Override
    public void processKeyInput(KeyEvent event) {
        System.out.println(event);
        Direction direction = switch (event.getCode()) {
            default -> Direction.left;
        };
        snake.setDirection(direction);
    }

    @Override
    public void processKeyInput(KeyStroke stroke) {

    }
}

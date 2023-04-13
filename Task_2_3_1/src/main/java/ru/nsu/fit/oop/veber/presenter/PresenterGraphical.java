package ru.nsu.fit.oop.veber.presenter;

import javafx.scene.input.KeyEvent;
import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.CollisionChecker;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;

public class PresenterGraphical implements Presenter {

    private final Snake snake;
    private final Food food;
    private final Box box;
    private final CollisionChecker collisionChecker;

    public PresenterGraphical() {
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

    }


    @Override
    public void startGameProcess() {


    }
}

package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.model.Box;
import ru.nsu.fit.oop.veber.model.Food;
import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.view.ConsoleView;
import ru.nsu.fit.oop.veber.view.View;


public class PresenterConsole implements Presenter {
    private final Snake snake;

    private final Food food;

    private final Box box;
    private final View consoleView;

    public PresenterConsole() {
        snake = new Snake(5, 5);
        food = new Food(3, 5);
        box = new Box(30, 15);
        consoleView = new ConsoleView(this);
    }

    public void startGameProcess() {
        boolean gameProcessActive = true;
        while (gameProcessActive) {
            consoleView.gameProcess(snake, food, box);
            snake.checkFoodCollision(food);
            gameProcessActive = !snake.checkBoxCollision(box);
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

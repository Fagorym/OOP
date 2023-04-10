package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;


public class PresenterConsole implements Presenter {
    private final Snake snake;

    public PresenterConsole() {
        snake = new Snake(5, 5);

    }

    @Override
    public void moveSnake(Direction direction) {
        switch (direction) {
            case up -> snake.setY(snake.getY() + 1);
            case down -> snake.setY(snake.getY() - 1);
            case left -> snake.setX(snake.getX() - 1);
            case right -> snake.setX(snake.getX() + 1);
            //FIXME: Поворот неправильный
        }
    }

    @Override
    public void rotateSnake(Direction direction) {
// TODO: сделать поворот
    }

    @Override
    public void processKeyInput(int event) {

    }
}

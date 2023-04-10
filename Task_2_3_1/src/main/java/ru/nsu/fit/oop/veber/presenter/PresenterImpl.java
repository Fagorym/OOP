package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.model.Snake;
import ru.nsu.fit.oop.veber.utils.Direction;
import ru.nsu.fit.oop.veber.utils.Strategy;
import ru.nsu.fit.oop.veber.view.ConsoleView;
import ru.nsu.fit.oop.veber.view.GraphicalView;
import ru.nsu.fit.oop.veber.view.View;


public class PresenterImpl implements Presenter {
    private final Snake snake;
    private final View view;

    public PresenterImpl(Strategy strategy) {
        snake = new Snake();
        switch (strategy) {
            case console -> view = new ConsoleView();
            case graphical -> view = new GraphicalView(this);
            default -> throw new IllegalArgumentException();
        }

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

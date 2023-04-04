package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.utils.Direction;

import java.awt.event.KeyEvent;

public interface Presenter {
    void moveSnake(Direction direction);

    void rotateSnake(Direction direction);

    void processKeyInput(KeyEvent event);
}

package ru.nsu.fit.oop.veber.presenter;

import ru.nsu.fit.oop.veber.utils.Direction;

public interface Presenter {
    void moveSnake(Direction direction);

    void rotateSnake(Direction direction);

    void processKeyInput(Character character);

    void startGameProcess();
}

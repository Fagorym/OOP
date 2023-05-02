package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.timer.Timer;

public interface View {

    void endGame();

    void clearScreen();

    void render();

    Timer setTimer(Runnable gameStep);
}

package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.model.BoxElement;
import ru.nsu.fit.oop.veber.timer.Timer;

public interface View {

    void endGame();

    void clearScreen();

    void refreshScreen();

    void render(BoxElement boxElement);

    Timer setTimer(Runnable gameStep);
}

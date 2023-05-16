package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.timer.Timer;

/**
 * Interface of the game screen view.
 * Must be connected with timer, can be paused, can be ended, etc.
 */
public interface GameView {

    /**
     * Function, that ended the game.
     */
    void endGame();

    /**
     * Function, that clears the game screen.
     */
    void clearScreen();

    /**
     * Function, that render the game screen.
     */
    void render();

    /**
     * Function, that set the timer and returns it.
     *
     * @param gameStep callback function, that we used for game step.
     * @return settled timer.
     */
    Timer setTimer(Runnable gameStep);

    /**
     * Function, that paused the game.
     */
    void pause();
}

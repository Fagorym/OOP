package ru.nsu.fit.oop.veber.application;

import ru.nsu.fit.oop.veber.view.ConsoleView;
import ru.nsu.fit.oop.veber.view.GameView;

public class SnakeGameConsole implements MyApplication {

    public SnakeGameConsole() {
        GameView consoleView = new ConsoleView();
    }


    @Override
    public void startGameProcess() {

    }
}

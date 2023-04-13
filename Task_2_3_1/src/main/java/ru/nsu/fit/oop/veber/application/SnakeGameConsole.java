package ru.nsu.fit.oop.veber.application;

import ru.nsu.fit.oop.veber.view.ConsoleView;
import ru.nsu.fit.oop.veber.view.View;

public class SnakeGameConsole implements MyApplication {

    private final View consoleView;

    public SnakeGameConsole() {
        consoleView = new ConsoleView();
    }


    @Override
    public void startGameProcess() {

    }
}

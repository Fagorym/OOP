package ru.nsu.fit.oop.veber.application;

import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterConsole;

public class SnakeGame implements Application {

    private final Presenter presenter;

    public SnakeGame() {
        presenter = new PresenterConsole();
    }

    @Override
    public void launch() {
        presenter.startGameProcess();


    }
}

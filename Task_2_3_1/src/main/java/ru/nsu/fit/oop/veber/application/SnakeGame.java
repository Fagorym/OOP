package ru.nsu.fit.oop.veber.application;

import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterConsole;
import ru.nsu.fit.oop.veber.view.ConsoleView;
import ru.nsu.fit.oop.veber.view.View;

public class SnakeGame implements Application {

    public SnakeGame() {
        Presenter presenter = new PresenterConsole();
        View view = new ConsoleView();


    }

    @Override
    public void launch() {


    }
}

package ru.nsu.fit.oop.veber.application;

import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterImpl;
import ru.nsu.fit.oop.veber.utils.Strategy;
import ru.nsu.fit.oop.veber.view.GraphicalView;
import ru.nsu.fit.oop.veber.view.View;

public class SnakeGame implements Application {

    public SnakeGame() {
        Presenter presenter = new PresenterImpl(Strategy.graphical);
        View view = new GraphicalView(presenter);


    }

    @Override
    public void launch() {
    }
}

package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.presenter.Presenter;
import ru.nsu.fit.oop.veber.presenter.PresenterImpl;
import ru.nsu.fit.oop.veber.utils.Strategy;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new PresenterImpl(Strategy.console);
    }
}
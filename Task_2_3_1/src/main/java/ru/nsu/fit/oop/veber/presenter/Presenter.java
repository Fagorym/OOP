package ru.nsu.fit.oop.veber.presenter;

import javafx.scene.input.KeyEvent;

public interface Presenter {
    void processKeyInput(KeyEvent event);

    void startGameProcess();
}

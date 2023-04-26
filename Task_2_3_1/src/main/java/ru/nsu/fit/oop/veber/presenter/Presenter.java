package ru.nsu.fit.oop.veber.presenter;

import com.googlecode.lanterna.input.KeyStroke;
import javafx.scene.input.KeyEvent;

public interface Presenter {
    void processKeyInput(KeyEvent event);

    void processKeyInput(KeyStroke stroke);

    void makeGameStep();
}

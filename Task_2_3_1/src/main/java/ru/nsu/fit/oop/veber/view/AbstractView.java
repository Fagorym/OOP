package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.utils.SceneManager;

abstract public class AbstractView {
    protected SceneManager sceneManager;

    public AbstractView() {
        sceneManager = new SceneManager();
    }
}

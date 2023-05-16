package ru.nsu.fit.oop.veber.timer;

import javafx.animation.AnimationTimer;

public class GraphicalTimer implements Timer {
    private final AnimationTimer animationTimer;

    public GraphicalTimer(AnimationTimer timer) {
        this.animationTimer = timer;
    }

    @Override
    public void start() {
        animationTimer.start();
    }

    @Override
    public void stop() {
        animationTimer.stop();
    }
}

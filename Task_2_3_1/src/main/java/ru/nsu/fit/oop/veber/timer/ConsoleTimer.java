package ru.nsu.fit.oop.veber.timer;

public class ConsoleTimer implements Timer {
    private final Thread thread;

    public ConsoleTimer(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void start() {
        thread.start();
    }
}

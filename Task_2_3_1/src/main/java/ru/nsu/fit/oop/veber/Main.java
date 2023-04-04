package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.application.Application;
import ru.nsu.fit.oop.veber.application.SnakeGame;

public class Main {
    public static void main(String[] args) {
        Application snakeGame = new SnakeGame();
        snakeGame.launch();
    }
}
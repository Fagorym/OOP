package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.application.SnakeGame;
import ru.nsu.fit.oop.veber.application.SnakeGameGraphical;

public class Main {
    public static void main(String[] args) {
        switch ("graphical") {
            case "graphical" -> new SnakeGameGraphical().startGameProcess();
            case "console" -> new SnakeGame().startGameProcess();
        }
    }
}

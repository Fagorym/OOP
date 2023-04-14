package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.application.SnakeGameConsole;
import ru.nsu.fit.oop.veber.application.SnakeGameGraphical;

public class Main {
    public static void main(String[] args) {
        switch ("console") {
            case "graphical" -> new SnakeGameGraphical().startGameProcess();
            case "console" -> new SnakeGameConsole().startGameProcess();
        }
    }
}

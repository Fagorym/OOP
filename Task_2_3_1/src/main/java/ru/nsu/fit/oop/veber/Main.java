package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.application.SnakeGameConsole;
import ru.nsu.fit.oop.veber.application.SnakeGameGraphical;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, it is a Snake Game!");
        System.out.println("Press 1 to launch graphical version!");
        System.out.println("Press 2 to launch console version!");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> new SnakeGameGraphical().startGameProcess();
            case 2 -> new SnakeGameConsole().startGameProcess();
            default -> throw new IllegalArgumentException("Неверный ввод");
        }
    }
}

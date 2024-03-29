package ru.nsu.fit.oop.veber;

import com.google.gson.GsonBuilder;
import ru.nsu.fit.oop.veber.application.SnakeGameConsole;
import ru.nsu.fit.oop.veber.application.SnakeGameFXML;
import ru.nsu.fit.oop.veber.application.SnakeGameGraphical;
import ru.nsu.fit.oop.veber.utils.GameConfiguration;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

/**
 * Main class.
 * Hello screen, where you choose current implementation of the game.
 */
public class Main {
    public static void main(String[] args) {
        try (Reader reader = new FileReader("Task_2_3_1/src/main/resources/config.json")) {
            GameConfiguration gameConfiguration = new GsonBuilder().create().fromJson(reader, GameConfiguration.class);
            GameConfiguration.setGameConfiguration(gameConfiguration);
            System.out.println("Hello, it is a Snake Game!");
            System.out.println("Press 1 to launch graphical version!");
            System.out.println("Press 2 to launch console version!");
            System.out.println("Press 3 to launch full version");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> new SnakeGameGraphical().startGameProcess();
                case 2 -> new SnakeGameConsole().startGameProcess();
                case 3 -> new SnakeGameFXML().startGameProcess();
                default -> throw new IllegalArgumentException("Неверный ввод");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

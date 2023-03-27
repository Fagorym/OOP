package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        PizzeriaParser parser = new PizzeriaParser();
        Pizzeria pizzeria = parser.parsePizzeriaFromFile("Task_2_2_1/src/main/resources/config.json");
        ExecutorService executorPizzeria = Executors.newSingleThreadExecutor();
        boolean isWorking = true;
        while (isWorking) {
            String command = new Scanner(System.in).nextLine();
            switch (command) {
                case "start" -> {
                    System.out.println("Starting work");
                    executorPizzeria.submit(pizzeria);
                }
                case "stop" -> {
                    System.out.println("Stopping work");
                    pizzeria.stopWorking();
                }
                case "end" -> {
                    System.out.println("Ending work");
                    return;
                }
                default -> System.out.println("Unknown command");
            }
        }

    }
}

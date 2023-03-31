package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;
import ru.nsu.fit.oop.veber.pizzeria.PizzeriaImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PizzeriaParser parser = new PizzeriaParser();
        ConfigurationDto configurationDto = parser.getConfigurationDtoFromFile("/config.json");
        Pizzeria pizzeria = new PizzeriaImpl(configurationDto);
        boolean isWorking = true;
        while (isWorking) {
            String command = new Scanner(System.in).nextLine();
            switch (command) {
                case "start" -> {
                    System.out.println("Starting work");
                    new Thread(pizzeria).start();
                }
                case "stop" -> {
                    System.out.println("Stopping work");
                    pizzeria.stopWorking();
                }
                case "resume" -> {
                    System.out.println("Resuming work");
                    pizzeria.resumeWorking();
                }
                case "end" -> {
                    pizzeria.endWorking();
                    System.out.println("Ending work");
                    isWorking = false;
                }
                default -> System.out.println("Unknown command");
            }
        }

    }
}

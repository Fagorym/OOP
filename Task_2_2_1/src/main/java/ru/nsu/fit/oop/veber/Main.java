package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerImpl;
import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        PizzeriaParser parser = new PizzeriaParser();
        Pizzeria pizzeria = parser.parsePizzeriaFromFile("config.json");
        Random random = new Random();
        int customerCount = random.nextInt(3) + 3;
        Customer[] customers = new Customer[customerCount];
        for (int i = 0; i < customerCount; i++) {
            int pizzaCount = random.nextInt(5) + 1;
            customers[i] = new CustomerImpl();
            customers[i].orderPizza(pizzeria, pizzaCount);
        }
        pizzeria.run();
    }
}

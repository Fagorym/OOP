package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerImpl;
import ru.nsu.fit.oop.veber.pizzeria.OrderProvider;
import ru.nsu.fit.oop.veber.pizzeria.PizzeriaImpl;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        OrderProvider pizzeria = new PizzeriaImpl();
        Random random = new Random();
        int customerCount = random.nextInt(3) + 3;
        int exit = 0;
        Customer[] customers = new Customer[customerCount];
        for (int i = 0; i < customerCount; i++) {
            int pizzaCount = random.nextInt(5) + 1;
            customers[i] = new CustomerImpl();
            customers[i].orderPizza(pizzeria, pizzaCount);
        }
        Runnable orderProcessor = (Runnable) pizzeria;
        orderProcessor.run();
    }
}

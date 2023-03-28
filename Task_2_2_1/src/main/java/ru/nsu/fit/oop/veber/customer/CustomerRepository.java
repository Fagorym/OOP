package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents customer repository.
 * Main aim of this class - generate a bunch of customers that can create orders.
 */
public class CustomerRepository {
    private final int customerCount;
    private final Pizzeria pizzeria;


    public CustomerRepository(int minimalCustomerCount, Pizzeria pizzeria) {
        customerCount = new Random().nextInt(3) + minimalCustomerCount;
        this.pizzeria = pizzeria;

    }

    public List<Runnable> generateCustomers() {
        List<Runnable> customers = new ArrayList<>();
        for (int i = 0; i < customerCount; i++) {
            int pizzaCountInOrder = new Random().nextInt(3) + 1;
            Runnable customer = new CustomerImpl(pizzeria, pizzaCountInOrder);
            customers.add(customer);
        }
        return customers;
    }
}

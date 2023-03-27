package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerRepository {
    private final int customerCount;
    private final Pizzeria pizzeria;


    public CustomerRepository(int minimalCustomerCount, Pizzeria pizzeria) {
        customerCount = new Random().nextInt(3) + minimalCustomerCount;
        this.pizzeria = pizzeria;

    }

    public List<Customer> generateCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < customerCount; i++) {
            int pizzaCountInOrder = new Random().nextInt(3) + 1;
            Customer customer = new CustomerImpl(pizzeria, pizzaCountInOrder);
            customers.add(customer);
        }
        return customers;
    }
}

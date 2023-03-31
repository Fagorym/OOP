package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerImpl;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerService implements Service, CustomerGenerator {

    private final Pizzeria pizzeria;
    private final int MIN_CUSTOMER_COUNT = 3;
    private final List<Customer> customers;

    public CustomerService(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        customers = new ArrayList<>();
    }


    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(MIN_CUSTOMER_COUNT);
        generate();
        customers.forEach(executorService::execute);
    }

    @Override
    public List<Customer> generate() {
        int customerCount = new Random().nextInt(3) + MIN_CUSTOMER_COUNT;
        for (int i = 0; i < customerCount; i++) {
            int pizzaCountInOrder = new Random().nextInt(3) + 1;
            Customer customer = new CustomerImpl(pizzeria, pizzaCountInOrder);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public void stopService() {
        customers.forEach(Customer::stopOrdering);

    }

    @Override
    public void resumeService() {
        customers.forEach(Customer::resumeOrdering);
    }
}

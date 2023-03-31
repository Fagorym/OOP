package ru.nsu.fit.oop.veber.service;

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
    private ExecutorService executorService;
    private boolean isAlive = true;

    public CustomerService(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void closeService() {
        executorService.shutdownNow();
        isAlive = false;
    }


    @Override
    public void run() {
        executorService = Executors.newFixedThreadPool(MIN_CUSTOMER_COUNT);
        List<Runnable> customerList = generate();
        while (isAlive) {
            customerList.forEach(executorService::execute);
        }
    }

    @Override
    public List<Runnable> generate() {
        List<Runnable> customers = new ArrayList<>();
        int customerCount = new Random().nextInt(3) + MIN_CUSTOMER_COUNT;
        for (int i = 0; i < customerCount; i++) {
            int pizzaCountInOrder = new Random().nextInt(3) + 1;
            Runnable customer = new CustomerImpl(pizzeria, pizzaCountInOrder);
            customers.add(customer);
        }
        return customers;
    }
}

package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerRepository;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerService implements Service {

    private final Pizzeria pizzeria;

    private ExecutorService executorService;

    private boolean isAlive = true;

    public CustomerService(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void shutdown() {
        executorService.shutdownNow();
        isAlive = false;
    }


    @Override
    public Void call() {
        final int MIN_CUSTOMER_COUNT = 3;
        executorService = Executors.newFixedThreadPool(MIN_CUSTOMER_COUNT);
        List<Customer> customerList = new CustomerRepository(MIN_CUSTOMER_COUNT, pizzeria).generateCustomers();
        while (isAlive) {
            customerList.forEach(executorService::execute);
        }
        return null;
    }
}

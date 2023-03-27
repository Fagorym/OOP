package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerRepository;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerService implements Service {

    private final Pizzeria pizzeria;

    public CustomerService(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }


    @Override
    public Void call() {
        final int MIN_CUSTOMER_COUNT = 3;
        ExecutorService executorCustomers = Executors.newFixedThreadPool(MIN_CUSTOMER_COUNT);
        List<Customer> customerList = new CustomerRepository(MIN_CUSTOMER_COUNT, pizzeria).generateCustomers();
        while (true) {
            customerList.forEach(executorCustomers::execute);
        }
    }
}

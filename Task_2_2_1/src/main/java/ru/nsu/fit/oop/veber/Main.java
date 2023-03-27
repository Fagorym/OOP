package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.customer.Customer;
import ru.nsu.fit.oop.veber.customer.CustomerRepository;
import ru.nsu.fit.oop.veber.parsing.PizzeriaParser;
import ru.nsu.fit.oop.veber.pizzeria.Pizzeria;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        PizzeriaParser parser = new PizzeriaParser();
        Pizzeria pizzeria = parser.parsePizzeriaFromFile("Task_2_2_1/src/main/resources/config.json");

        final int MIN_CUSTOMER_COUNT = 3;
        List<Customer> customerList = new CustomerRepository(MIN_CUSTOMER_COUNT, pizzeria).generateCustomers();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        customerList.forEach(executorService::execute);
        executorService.execute(pizzeria);
    }
}

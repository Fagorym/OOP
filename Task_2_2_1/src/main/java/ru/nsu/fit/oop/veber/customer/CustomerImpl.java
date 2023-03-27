package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.pizzeria.OrderProvider;

import java.util.Random;

public class CustomerImpl implements Customer {
    private final OrderProvider provider;
    private final int pizzaCountInOrder;

    public CustomerImpl(OrderProvider provider, int pizzaCount) {
        this.provider = provider;
        this.pizzaCountInOrder = pizzaCount;

    }

    @Override
    public void run() {
        System.out.println("Hello from customer " + this);
        try {
            provider.makeOrder(pizzaCountInOrder);
            int MIN_SLEEP_TIME = 1000;
            int sleepTime = new Random().nextInt(5000) + MIN_SLEEP_TIME;
            Thread.sleep(sleepTime);

        } catch (InterruptedException e) {
            System.out.println("Customer " + this + " was interrupted");
        }
    }
}

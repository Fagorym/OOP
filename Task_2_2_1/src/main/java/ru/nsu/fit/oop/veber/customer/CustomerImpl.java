package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.pizzeria.OrderProvider;

public class CustomerImpl implements Customer {
    private final OrderProvider provider;
    private final int pizzaCountInOrder;

    public CustomerImpl(OrderProvider provider, int pizzaCount) {
        this.provider = provider;
        this.pizzaCountInOrder = pizzaCount;

    }

    @Override
    public void run() {
        while (true) {
            provider.makeOrder(pizzaCountInOrder);
            try {
                this.wait(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

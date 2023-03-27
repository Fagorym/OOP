package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.pizzeria.OrderProvider;

public class CustomerImpl implements Customer {
    @Override
    public void orderPizza(OrderProvider provider, int pizzaCount) {
        provider.makeOrder(pizzaCount);
    }
}

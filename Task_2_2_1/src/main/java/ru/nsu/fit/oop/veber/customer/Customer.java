package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.OrderProvider;

public interface Customer {
    void orderPizza(OrderProvider pizzeria, int pizzaCount);
}

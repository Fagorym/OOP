package ru.nsu.fit.oop.veber.customer;

import ru.nsu.fit.oop.veber.pizzeria.OrderProvider;

public interface Customer {
    void orderPizza(OrderProvider pizzeria, int pizzaCount);
}

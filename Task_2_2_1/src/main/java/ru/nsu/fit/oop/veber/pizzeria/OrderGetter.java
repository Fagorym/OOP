package ru.nsu.fit.oop.veber.pizzeria;

import ru.nsu.fit.oop.veber.order.PizzaOrder;

public interface OrderGetter {
    PizzaOrder getOrder() throws InterruptedException;
}

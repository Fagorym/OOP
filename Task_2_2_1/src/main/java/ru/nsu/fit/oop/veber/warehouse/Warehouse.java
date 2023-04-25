package ru.nsu.fit.oop.veber.warehouse;

import ru.nsu.fit.oop.veber.order.PizzaOrder;

public interface Warehouse {

    void addPizza(PizzaOrder pizza) throws InterruptedException;

    PizzaOrder getPizza() throws InterruptedException;

    boolean isFull();

    boolean isEmpty();
}

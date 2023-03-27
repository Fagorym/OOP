package ru.nsu.fit.oop.veber.warehouse;

import ru.nsu.fit.oop.veber.order.Pizza;

public interface Warehouse {

    void addPizza(Pizza pizza) throws InterruptedException;

    Pizza getPizza() throws InterruptedException;

    boolean isFull();
}

package ru.nsu.fit.oop.veber;

public interface Warehouse {

    void addPizza(Pizza pizza) throws InterruptedException;

    Pizza getPizza() throws InterruptedException;

    boolean isFull();
}

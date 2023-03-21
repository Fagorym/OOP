package ru.nsu.fit.oop.veber;

public interface Warehouse {
    int getPizzaCount();

    void addPizza(Pizza pizza);

    Pizza getPizza();

    boolean isFull();
}

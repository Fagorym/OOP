package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Queue;

public class WarehouseImpl implements Warehouse {
    private final Queue<Pizza> pizzas;
    private final int capacity;

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
        pizzas = new ArrayDeque<>(capacity);
    }

    @Override
    public int getPizzaCount() {
        return pizzas.size();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public Pizza getPizza() {
        return pizzas.poll();

    }

    @Override
    public boolean isFull() {
        return pizzas.size() == capacity;
    }
}

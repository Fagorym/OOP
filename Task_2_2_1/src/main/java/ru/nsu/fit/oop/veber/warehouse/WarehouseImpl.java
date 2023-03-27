package ru.nsu.fit.oop.veber.warehouse;

import ru.nsu.fit.oop.veber.order.Pizza;

import java.util.ArrayDeque;
import java.util.Queue;

public class WarehouseImpl implements Warehouse {
    private final Queue<Pizza> pizzas;
    private final int capacity;

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
        pizzas = new ArrayDeque<>(capacity);
    }

    public void addPizza(Pizza pizza) throws InterruptedException {
        synchronized (this) {
            while (this.isFull()) {
                this.wait();
            }
            pizzas.add(pizza);
        }

    }

    public Pizza getPizza() throws InterruptedException {
        synchronized (this) {
            while (isEmpty()) {
                this.wait();
            }
            return pizzas.poll();
        }

    }

    @Override
    public boolean isFull() {
        return pizzas.size() == capacity;
    }

    public boolean isEmpty() {
        return pizzas.size() == 0;
    }
}

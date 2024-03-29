package ru.nsu.fit.oop.veber.warehouse;

import ru.nsu.fit.oop.veber.order.PizzaOrder;

import java.util.ArrayDeque;
import java.util.Queue;

public class WarehouseImpl implements Warehouse {
    private final Queue<PizzaOrder> pizzas;
    private final int capacity;

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
        pizzas = new ArrayDeque<>(capacity);
    }

    @Override
    synchronized public void addPizza(PizzaOrder order) throws InterruptedException {
        while (this.isFull()) {
            this.wait();
        }
        pizzas.add(order);
        notifyAll();

    }

    @Override
    synchronized public PizzaOrder getPizza() throws InterruptedException {
        while (isEmpty()) {
            this.wait();
        }
        notifyAll();
        return pizzas.poll();

    }

    @Override
    public boolean isFull() {
        return pizzas.size() == capacity;
    }

    @Override
    public boolean isEmpty() {
        return pizzas.size() == 0;
    }


}

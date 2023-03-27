package ru.nsu.fit.oop.veber.courier;

import ru.nsu.fit.oop.veber.order.Pizza;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;

import java.util.ArrayDeque;
import java.util.Queue;

public class CourierImpl implements Courier, Runnable {
    private final Warehouse warehouse;
    private final Queue<Pizza> bag;

    private final int baggageCount;

    public CourierImpl(Warehouse warehouse, int baggageCount) {
        this.warehouse = warehouse;
        this.baggageCount = baggageCount;
        this.bag = new ArrayDeque<>();
    }

    @Override
    public void deliverPizza() throws InterruptedException {
        while (!isBagFull()) {
            Pizza pizza = warehouse.getPizza();
            bag.add(pizza);
            System.out.println("Courier " + this + " received pizza " + pizza.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                deliverPizza();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isBagFull() {
        return bag.size() == baggageCount;
    }
}

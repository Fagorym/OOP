package ru.nsu.fit.oop.veber.courier;

import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;

import java.util.ArrayDeque;
import java.util.Queue;

public class CourierImpl implements Courier, Runnable {
    private final Warehouse warehouse;
    private final Queue<PizzaOrder> bag;

    private final int baggageCount;

    public CourierImpl(Warehouse warehouse, int baggageCount) {
        this.warehouse = warehouse;
        this.baggageCount = baggageCount;
        this.bag = new ArrayDeque<>();
    }

    @Override
    public void deliverPizza() throws InterruptedException {
        while (!isBagFull()) {
            PizzaOrder pizzaOrder = warehouse.getPizza();
            bag.add(pizzaOrder);
            System.out.println("Courier " + this + " received pizza " + pizzaOrder.toString());
        }
        for (PizzaOrder pizzaOrder : bag) {
            pizzaOrder.getConsumer().accept(null);
            bag.remove(pizzaOrder);
        }
    }

    @Override
    public void run() {
        try {
            deliverPizza();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isBagFull() {
        return bag.size() == baggageCount;
    }
}

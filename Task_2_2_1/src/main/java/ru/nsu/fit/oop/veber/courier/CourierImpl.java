package ru.nsu.fit.oop.veber.courier;

import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;

import java.util.ArrayDeque;
import java.util.Queue;

public class CourierImpl implements Courier, Runnable {
    private final Warehouse warehouse;
    private final Queue<PizzaOrder> bag;

    private final int baggageCount;

    private final int deliveryTimeMs;


    public CourierImpl(Warehouse warehouse, int baggageCount, int deliveryTimeMs) {
        this.warehouse = warehouse;
        this.baggageCount = baggageCount;
        this.bag = new ArrayDeque<>();
        this.deliveryTimeMs = deliveryTimeMs;
    }

    @Override
    public void deliverPizza() throws InterruptedException {
        while (!isBagFull()) {
            PizzaOrder pizzaOrder = warehouse.getPizza();
            bag.add(pizzaOrder);
            System.out.println("Courier " + this + " received pizza " + pizzaOrder.toString());
        }
        for (PizzaOrder pizzaOrder : bag) {
            Thread.sleep(deliveryTimeMs);
            pizzaOrder.getConsumer().run();
            bag.remove(pizzaOrder);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                deliverPizza();
            } catch (InterruptedException e) {
                System.out.println("Courier thread was interrupted");
                return;
            }
        }
    }

    private boolean isBagFull() {
        return bag.size() == baggageCount;
    }
}

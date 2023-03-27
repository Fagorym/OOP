package ru.nsu.fit.oop.veber.courier;

import ru.nsu.fit.oop.veber.order.Pizza;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;

public class CourierImpl implements Courier, Runnable {
    private final Warehouse warehouse;
    private final int baggageCount;

    public CourierImpl(Warehouse warehouse, int baggageCount) {
        this.warehouse = warehouse;
        this.baggageCount = baggageCount;
    }

    @Override
    public void deliverPizza() throws InterruptedException {
        Pizza pizza = warehouse.getPizza();
        System.out.println("Courier " + this + " received pizza " + pizza.toString());
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
}

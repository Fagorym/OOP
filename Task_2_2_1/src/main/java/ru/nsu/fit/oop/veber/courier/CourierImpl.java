package ru.nsu.fit.oop.veber.courier;

import ru.nsu.fit.oop.veber.order.Pizza;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;

public class CourierImpl implements Courier, Runnable {
    private final Warehouse warehouse;

    public CourierImpl(Warehouse warehouse) {
        this.warehouse = warehouse;
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

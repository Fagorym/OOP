package ru.nsu.fit.oop.veber;

import java.util.*;

public class PizzeriaImpl implements OrderProvider, OrderGetter, Runnable {
    private final List<Backer> backers;
    private final List<Courier> couriers;
    private final Warehouse warehouse;
    private final Queue<PizzaOrder> orders;

    private boolean isOpened = true;

    private int orderNumber = 0;


    public PizzeriaImpl() {
        backers = new ArrayList<>();
        couriers = new ArrayList<>();
        warehouse = new WarehouseImpl(100);
        orders = new ArrayDeque<>();
        generateBackers();
        generateCouriers();
    }

    private void generateCouriers() {
        Random random = new Random();
        int maxWorkers = random.nextInt(4) + 2;
        for (int i = 0; i < maxWorkers; i++) {
            Courier courier = new CourierImpl();
            couriers.add(courier);
        }
    }

    private void generateBackers() {
        Random random = new Random();
        int maxBackers = random.nextInt(4) + 2;
        for (int i = 0; i < maxBackers; i++) {
            Backer backer = new BackerImpl(warehouse, this);
            backers.add(backer);
        }
    }

    @Override
    synchronized public void makeOrder(int count) {
        PizzaOrder order = new PizzaOrder(orderNumber++, count);
        orders.add(order);
        System.out.println("Pizzeria received order with number " + order.id());
    }

    @Override
    public void run() {
        while (isOpened) {
            backers.forEach(Backer::run);
            couriers.forEach(Courier::deliverPizza);
        }
    }

    @Override
    public PizzaOrder getOrder() {
        return orders.poll();
    }
}

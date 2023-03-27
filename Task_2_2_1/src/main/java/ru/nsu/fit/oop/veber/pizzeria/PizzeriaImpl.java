package ru.nsu.fit.oop.veber.pizzeria;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PizzeriaImpl implements OrderProvider, OrderGetter, Runnable {
    private final List<Backer> backers;
    private final List<Courier> couriers;
    private final Warehouse warehouse;
    private final Queue<PizzaOrder> orders;

    private boolean isOpened;

    private int orderNumber = 0;


    public PizzeriaImpl() {
        backers = new ArrayList<>();
        couriers = new ArrayList<>();
        warehouse = new WarehouseImpl(2);
        orders = new ArrayDeque<>();
        generateBackers();
        generateCouriers();
        isOpened = true;
    }

    private void generateCouriers() {
        Random random = new Random();
        int maxWorkers = random.nextInt(4) + 2;
        for (int i = 0; i < maxWorkers; i++) {
            Courier courier = new CourierImpl(warehouse);
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
            List<Runnable> workers = new ArrayList<>(backers);
            workers.addAll(couriers);
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            workers.forEach(executorService::execute);
        }
    }

    @Override
    public PizzaOrder getOrder() throws InterruptedException {
        synchronized (this) {
            if (orders.isEmpty()) {
                this.wait();
            }
            return orders.poll();
        }
    }
}

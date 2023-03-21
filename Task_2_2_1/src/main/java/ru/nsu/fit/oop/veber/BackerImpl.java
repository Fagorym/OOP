package ru.nsu.fit.oop.veber;

import java.util.Random;

public class BackerImpl implements Backer {
    private final int WORK_SPEED;
    private final OrderGetter orderGetter;
    private final Warehouse warehouse;

    public BackerImpl(Warehouse warehouse, OrderGetter orderGetter) {
        this.warehouse = warehouse;
        this.orderGetter = orderGetter;
        WORK_SPEED = new Random().nextInt(2000) + 1000;
    }

    @Override
    public Pizza makePizza(int orderId) throws InterruptedException {
        Thread.sleep(WORK_SPEED);
        Pizza pizza = new Pizza();
        System.out.println("Pizza man creates pizza for order " + orderId);
        return pizza;
    }

    @Override
    public void run() {
        try {
            PizzaOrder order = orderGetter.getOrder();
            Pizza pizza = makePizza(order.id());
            synchronized (warehouse) {
                while (warehouse.isFull()) {
                    Thread.currentThread().wait();
                }
                warehouse.addPizza(pizza);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

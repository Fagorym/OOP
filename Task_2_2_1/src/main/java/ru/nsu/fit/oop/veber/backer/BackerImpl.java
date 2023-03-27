package ru.nsu.fit.oop.veber.backer;

import ru.nsu.fit.oop.veber.order.Pizza;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.pizzeria.OrderGetter;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;

public class BackerImpl implements Backer {
    private final int workingTime;
    private final OrderGetter orderGetter;
    private final Warehouse warehouse;

    public BackerImpl(Warehouse warehouse, OrderGetter orderGetter, int workingTime) {
        this.warehouse = warehouse;
        this.orderGetter = orderGetter;
        this.workingTime = workingTime;
    }

    @Override
    public Pizza makePizza(int orderId) throws InterruptedException {
        Thread.sleep(workingTime);
        Pizza pizza = new Pizza();
        System.out.println("Pizza man " + this + " creates pizza for order " + orderId);
        return pizza;
    }

    @Override
    public void run() {
        while (true) {
            try {
                PizzaOrder order = orderGetter.getOrder();
                Pizza pizza = makePizza(order.id());
                warehouse.addPizza(pizza);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

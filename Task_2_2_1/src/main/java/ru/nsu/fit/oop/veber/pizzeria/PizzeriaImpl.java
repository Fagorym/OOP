package ru.nsu.fit.oop.veber.pizzeria;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PizzeriaImpl implements Pizzeria {
    private final List<Backer> backers;
    private final List<Courier> couriers;
    private final Warehouse warehouse;
    private final Queue<PizzaOrder> orders;


    private int orderNumber = 0;


    public PizzeriaImpl(ConfigurationDto configurationDto) {

        warehouse = new WarehouseImpl(configurationDto.getWarehouse().getCapacity());
        couriers = new ArrayList<>();
        for (CourierDto courierDto : configurationDto.getCouriers()) {
            Courier courier = new CourierImpl(warehouse, courierDto.getBaggageCount());
            couriers.add(courier);
        }
        backers = new ArrayList<>();
        for (BackerDto backerDto : configurationDto.getBackers()) {
            Backer backer = new BackerImpl(warehouse, this, backerDto.getWorkingTime());
            backers.add(backer);
        }
        orders = new ArrayDeque<>();
    }


    @Override
    synchronized public void makeOrder(int count) {
        PizzaOrder order = new PizzaOrder(orderNumber++, count);
        orders.add(order);
        System.out.println("Pizzeria received order with number " + order.id());
    }

    @Override
    public void run() {
        while (true) {
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

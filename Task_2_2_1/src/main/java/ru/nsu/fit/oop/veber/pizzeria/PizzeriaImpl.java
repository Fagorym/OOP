package ru.nsu.fit.oop.veber.pizzeria;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.service.CustomerService;
import ru.nsu.fit.oop.veber.service.Service;
import ru.nsu.fit.oop.veber.service.WorkersService;
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
    private final Queue<PizzaOrder> orders;
    private final Warehouse warehouse;
    private int orderNumber = 0;
    private CustomerService customerService;
    private WorkersService workersService;


    public PizzeriaImpl(ConfigurationDto configurationDto) {
        warehouse = new WarehouseImpl(configurationDto.warehouse().capacity());
        couriers = new ArrayList<>();
        for (CourierDto courierDto : configurationDto.couriers()) {
            Courier courier = new CourierImpl(warehouse, courierDto.baggageCount());
            couriers.add(courier);
        }
        backers = new ArrayList<>();
        for (BackerDto backerDto : configurationDto.backers()) {
            Backer backer = new BackerImpl(warehouse, this, backerDto.workingTime());
            backers.add(backer);
        }
        orders = new ArrayDeque<>();
    }


    @Override
    synchronized public void makeOrder(int count) {
        PizzaOrder order = new PizzaOrder(orderNumber++, count);
        order.setConsumer((x) ->
                System.out.println("Order was successfully delivered"));
        orders.add(order);
        System.out.println("Pizzeria received order with number " + order.getId());
        notifyAll();
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Service> services = new ArrayList<>();
        workersService = new WorkersService(backers, couriers, this);
        customerService = new CustomerService(this);
        services.add(customerService);
        services.add(workersService);
        try {
            executorService.invokeAll(services);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void stopWorking() {
        workersService.closeService();
        customerService.closeService();
    }

    @Override
    public PizzaOrder getOrder() throws InterruptedException {
        synchronized (this) {
            while (orders.isEmpty()) {
                wait();
            }
            return orders.poll();
        }
    }

    @Override
    public boolean isNoOrders() {
        return orders.isEmpty();
    }

    @Override
    public Warehouse getWarehouse() {
        return warehouse;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public List<Backer> getBackers() {
        return backers;
    }
}

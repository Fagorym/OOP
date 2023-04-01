package ru.nsu.fit.oop.veber.pizzeria;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.backer.BackerDto;
import ru.nsu.fit.oop.veber.backer.BackerImpl;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.courier.CourierDto;
import ru.nsu.fit.oop.veber.courier.CourierImpl;
import ru.nsu.fit.oop.veber.order.PizzaOrder;
import ru.nsu.fit.oop.veber.parsing.ConfigurationDto;
import ru.nsu.fit.oop.veber.service.BackerService;
import ru.nsu.fit.oop.veber.service.CourierService;
import ru.nsu.fit.oop.veber.service.CustomerService;
import ru.nsu.fit.oop.veber.warehouse.Warehouse;
import ru.nsu.fit.oop.veber.warehouse.WarehouseImpl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PizzeriaImpl implements Pizzeria {
    private final List<Backer> backers;
    private final List<Courier> couriers;
    private final Queue<PizzaOrder> orders;
    private final Warehouse warehouse;
    private int orderNumber = 0;
    private CustomerService customerService;
    private BackerService backerService;
    private CourierService courierService;


    public PizzeriaImpl(ConfigurationDto configurationDto) {
        warehouse = new WarehouseImpl(configurationDto.warehouse().capacity());
        couriers = new ArrayList<>();
        for (CourierDto courierDto : configurationDto.couriers()) {
            Courier courier = new CourierImpl(warehouse, courierDto.baggageCount(), courierDto.deliveryTimeMs());
            couriers.add(courier);
        }
        backers = new ArrayList<>();
        for (BackerDto backerDto : configurationDto.backers()) {
            Backer backer = new BackerImpl(warehouse, this, backerDto.workingTimeMs());
            backers.add(backer);
        }
        orders = new ArrayDeque<>();
    }


    @Override
    synchronized public void makeOrder(int count) {
        PizzaOrder order = new PizzaOrder(orderNumber++, count);
        order.setConsumer(() ->
                System.out.println("Order " + order.getId() + " was successfully delivered"));
        orders.add(order);
        System.out.println("Pizzeria received order with number " + order.getId());
        notifyAll();
    }

    @Override
    public void run() {
        backerService = new BackerService(backers);
        courierService = new CourierService(couriers);
        customerService = new CustomerService(this);
        backerService.run();
        courierService.run();
        customerService.run();
    }


    public void stopWorking() {
        customerService.stopService();
    }

    public void resumeWorking() {
        customerService.resumeService();
    }

    public void endWorking() {
        customerService.stopService();
        backerService.stopService();
        courierService.stopService();
        notifyAll();
    }

    @Override
    synchronized public PizzaOrder getOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        return orders.poll();
    }

    @Override
    public boolean isNoOrders() {
        return orders.isEmpty();
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

/*    public List<Courier> getCouriers() {
        return couriers;
    }

    public List<Backer> getBackers() {
        return backers;
    }

 */
}

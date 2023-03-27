package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.courier.Courier;
import ru.nsu.fit.oop.veber.pizzeria.OrderGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkersService implements Service {
    private final List<Backer> backers;
    private final List<Courier> couriers;

    private final OrderGetter orderGetter;

    private boolean isOpened;

    public WorkersService(List<Backer> backers, List<Courier> couriers, OrderGetter orderGetter) {
        this.backers = backers;
        this.couriers = couriers;
        this.orderGetter = orderGetter;
        isOpened = true;
    }

    public void closeService() {
        isOpened = false;
    }

    @Override
    public Void call() {
        ExecutorService executorService = Executors.newFixedThreadPool(backers.size() + couriers.size());
        List<Runnable> workers = new ArrayList<>(backers);
        workers.addAll(couriers);
        while (isOpened || !orderGetter.isNoOrders()) {
            workers.forEach(executorService::execute);
        }
        return null;
    }
}

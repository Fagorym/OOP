package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.pizzeria.OrderGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackerService implements Service {
    private final List<Backer> backers;
    private final OrderGetter orderGetter;

    private boolean isOpened;

    public BackerService(List<Backer> backers, OrderGetter orderGetter) {
        this.backers = backers;
        this.orderGetter = orderGetter;
        isOpened = true;
    }

    public void closeService() {
        isOpened = false;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(backers.size());
        List<Runnable> workers = new ArrayList<>(backers);
        while (isOpened || !orderGetter.isNoOrders()) {
            workers.forEach(executorService::execute);
        }
    }
}

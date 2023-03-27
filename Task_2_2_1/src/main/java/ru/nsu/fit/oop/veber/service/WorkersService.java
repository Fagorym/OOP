package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.backer.Backer;
import ru.nsu.fit.oop.veber.courier.Courier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkersService implements Service {
    private final List<Backer> backers;
    private final List<Courier> couriers;

    public WorkersService(List<Backer> backers, List<Courier> couriers) {
        this.backers = backers;
        this.couriers = couriers;
    }

    @Override
    public Void call() {
        ExecutorService executorService = Executors.newFixedThreadPool(backers.size() + couriers.size());
        List<Runnable> workers = new ArrayList<>(backers);
        workers.addAll(couriers);
        while (true) {
            workers.forEach(executorService::execute);
        }
    }
}

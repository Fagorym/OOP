package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.courier.Courier;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourierService implements Service {

    private final List<Courier> couriers;

    public CourierService(List<Courier> couriers) {
        this.couriers = couriers;
    }

    @Override
    public void closeService() {

    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(couriers.size());
        couriers.forEach(executorService::execute);

    }
}

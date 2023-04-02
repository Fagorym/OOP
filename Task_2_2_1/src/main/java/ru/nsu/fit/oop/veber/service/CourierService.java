package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.courier.Courier;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourierService implements Service {

    private final List<Courier> couriers;

    private final ExecutorService executorService;

    public CourierService(List<Courier> couriers) {
        this.couriers = couriers;
        executorService = Executors.newFixedThreadPool(couriers.size());
    }

    @Override
    public void stopService() {
        executorService.shutdownNow();
    }

    @Override
    public void run() {
        couriers.forEach(executorService::execute);
    }
}

package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.backer.Backer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackerService implements Service {
    private final List<Backer> backers;
    private final ExecutorService executorService;


    public BackerService(List<Backer> backers) {
        this.backers = backers;
        executorService = Executors.newFixedThreadPool(backers.size());
    }

    public void stopService() {
        executorService.shutdownNow();
    }

    @Override
    public void run() {
        backers.forEach(executorService::execute);
    }
}

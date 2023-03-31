package ru.nsu.fit.oop.veber.service;

import ru.nsu.fit.oop.veber.backer.Backer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackerService implements Service {
    private final List<Backer> backers;


    public BackerService(List<Backer> backers) {
        this.backers = backers;
    }

    public void closeService() {
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(backers.size());
        backers.forEach(executorService::execute);
    }
}

package ru.nsu.fit.oop.veber.service;

import java.util.concurrent.Callable;

public interface Service extends Callable<Void> {
    void closeService();
}

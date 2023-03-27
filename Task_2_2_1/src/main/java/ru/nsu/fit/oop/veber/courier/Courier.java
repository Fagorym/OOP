package ru.nsu.fit.oop.veber.courier;

public interface Courier extends Runnable {
    void deliverPizza() throws InterruptedException;
}

package ru.nsu.fit.oop.veber;

public interface Courier extends Runnable {
    void deliverPizza() throws InterruptedException;
}

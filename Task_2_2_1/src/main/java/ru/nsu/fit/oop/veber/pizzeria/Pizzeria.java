package ru.nsu.fit.oop.veber.pizzeria;

public interface Pizzeria extends Runnable, OrderGetter, OrderProvider {
    void stopWorking();
}

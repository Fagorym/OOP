package ru.nsu.fit.oop.veber;

public interface Backer extends Runnable {
    Pizza makePizza() throws InterruptedException;

    void getOrder(PizzaOrder order);
}

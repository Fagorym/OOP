package ru.nsu.fit.oop.veber;

public interface Backer extends Runnable {
    Pizza makePizza(int orderId) throws InterruptedException;

}

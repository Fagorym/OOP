package ru.nsu.fit.oop.veber.backer;

import ru.nsu.fit.oop.veber.order.Pizza;

public interface Backer extends Runnable {
    Pizza makePizza(int orderId) throws InterruptedException;

}

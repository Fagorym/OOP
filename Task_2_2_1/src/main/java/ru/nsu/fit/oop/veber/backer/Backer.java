package ru.nsu.fit.oop.veber.backer;

import ru.nsu.fit.oop.veber.order.Pizza;
import ru.nsu.fit.oop.veber.order.PizzaOrder;

public interface Backer extends Runnable {
    Pizza makePizza(PizzaOrder order) throws InterruptedException;

    void stopWorking();

    void resumeWorking();

}

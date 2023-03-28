package ru.nsu.fit.oop.veber.pizzeria;

import ru.nsu.fit.oop.veber.warehouse.Warehouse;

public interface Pizzeria extends Runnable, OrderGetter, OrderProvider {
    void stopWorking();

    Warehouse getWarehouse();
}

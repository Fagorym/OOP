package ru.nsu.fit.oop.veber.order;

import java.util.function.Consumer;

public class PizzaOrder {
    private final int id;
    private final int count;

    private Pizza pizza;
    private Consumer<Void> consumer;

    public PizzaOrder(int id, int count) {
        this.id = id;
        this.count = count;
    }
    

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Consumer<Void> getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer<Void> consumer) {
        this.consumer = consumer;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }
}


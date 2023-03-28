package ru.nsu.fit.oop.veber.order;

import java.util.function.Consumer;

/**
 * Class that represents pizza order.
 */
public class PizzaOrder {
    private final int id;
    private final int count;

    private Pizza pizza;
    private Consumer<Void> consumer;

    /**
     * Default constructor of pizza order.
     *
     * @param id    of order
     * @param count of pizza that client required
     */
    public PizzaOrder(int id, int count) {
        this.id = id;
        this.count = count;
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

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getId() {
        return id;
    }
}


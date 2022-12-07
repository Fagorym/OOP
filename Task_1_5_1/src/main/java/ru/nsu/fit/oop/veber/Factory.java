package ru.nsu.fit.oop.veber;

import java.util.HashMap;

public class Factory {

    private final HashMap<String, Operator> factory;

    public Factory() {
        factory = new HashMap<>();
    }

    public void addOperator(String key, Operator operator) {
        factory.put(key, operator);
    }

    public Operator getOperator(String key) {
        return factory.get(key);
    }
}

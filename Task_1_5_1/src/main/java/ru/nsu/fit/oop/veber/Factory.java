package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.Set;

public class Factory {

    private final HashMap<String, Operator> factory;

    public Factory() {
        factory = new HashMap<>();
        initOperatorMap();
    }

    public void addOperator(String key, Operator operator) {
        factory.put(key, operator);
    }

    public Operator getOperator(String key) {
        return factory.get(key);
    }

    public Set<String> getOperators() {
        return factory.keySet();
    }

    private void initOperatorMap() {
        factory.put("+", new BinaryOperator.Plus());
        factory.put("-", new BinaryOperator.Minus());
        factory.put("*", new BinaryOperator.Multiple());
        factory.put("/", new BinaryOperator.Divide());
        factory.put("sin", new UnaryOperator.Sin());
        factory.put("cos", new UnaryOperator.Cos());
        factory.put("log", new BinaryOperator.Log());
        factory.put("pow", new BinaryOperator.Pow());
        factory.put("sqrt", new UnaryOperator.Sqrt());


    }
}

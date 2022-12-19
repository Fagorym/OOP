package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.Set;

/**
 * Class that represent operator factory.
 */
public class Factory {

    private final HashMap<String, Operator> factory;

    /**
     * Main constructor of the factory.
     * Created hashmap and init basic operators.
     */
    public Factory() {
        factory = new HashMap<>();
        initOperatorMap();
    }

    /**
     * Adds custom operator to factory.
     *
     * @param key      - will be string representation of the operator
     * @param operator - operator interface, that implemented main calculate operation for this operator
     */
    public void addOperator(String key, Operator operator) {
        factory.put(key, operator);
    }

    /**
     * Getter for the operator.
     *
     * @param key - key of the explored operator
     * @return operator by key
     */
    public Operator getOperator(String key) {
        return factory.get(key);
    }

    /**
     * Getter for set of all operators, those fabric contains.
     *
     * @return set of all operators
     */
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

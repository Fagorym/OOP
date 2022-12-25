package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.binaryoperators.*;
import ru.nsu.fit.oop.veber.unaryoperators.Cos;
import ru.nsu.fit.oop.veber.unaryoperators.Sin;
import ru.nsu.fit.oop.veber.unaryoperators.Sqrt;

import java.util.Deque;
import java.util.HashMap;
import java.util.Set;

/**
 * Class that represent operator factory.
 */
public class Factory {

    private final HashMap<String, Operator> factory;
    private final Deque<Double> expression;

    /**
     * Main constructor of the factory.
     * Created hashmap and init basic operators.
     */
    public Factory(Deque<Double> expression) {
        factory = new HashMap<>();
        this.expression = expression;
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
        factory.put("+", new Plus());
        factory.put("-", new Minus());
        factory.put("*", new Multiple());
        factory.put("/", new Divide());
        factory.put("sin", new Sin());
        factory.put("cos", new Cos());
        factory.put("log", new Log());
        factory.put("pow", new Pow());
        factory.put("sqrt", new Sqrt());
    }
}

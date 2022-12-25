package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.binaryoperators.*;
import ru.nsu.fit.oop.veber.unaryoperators.Cos;
import ru.nsu.fit.oop.veber.unaryoperators.Sin;
import ru.nsu.fit.oop.veber.unaryoperators.Sqrt;

import java.util.*;

/**
 * Class that represent operator factory.
 */
public class Factory {

    private final List<Operator> factory;

    /**
     * Main constructor of the factory.
     * Created hashmap and init basic operators.
     */
    public Factory(Deque<Double> expression) {
        factory = new ArrayList<>();
        initOperatorMap();
    }

    /**
     * Adds custom operator to factory.
     *
     * @param operator - operator interface, that implemented main calculate operation for this operator
     */
    public void addOperator(Operator operator) {
        factory.add(operator);
    }

    /**
     * Getter for the operator.
     *
     * @param key - key of the explored operator
     * @return operator by key
     */
    public Operator getOperator(String key) {
        var optional = factory.stream()
                .filter(operator -> key.equals(operator.getKey()))
                .findFirst();
        return optional.orElseGet(() -> new Number(key));
    }

    private void initOperatorMap() {
        factory.add(new Plus());
        factory.add(new Minus());
        factory.add(new Multiple());
        factory.add(new Divide());
        factory.add(new Sin());
        factory.add(new Cos());
        factory.add(new Log());
        factory.add(new Pow());
        factory.add(new Sqrt());
    }
}

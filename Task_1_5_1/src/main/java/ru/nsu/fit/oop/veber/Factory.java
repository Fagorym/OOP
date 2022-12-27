package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.binaryoperators.*;
import ru.nsu.fit.oop.veber.numbers.ComplexNumber;
import ru.nsu.fit.oop.veber.numbers.RealNumber;
import ru.nsu.fit.oop.veber.unaryoperators.Cos;
import ru.nsu.fit.oop.veber.unaryoperators.Sin;
import ru.nsu.fit.oop.veber.unaryoperators.Sqrt;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent operator factory.
 */
public class Factory {

    private final List<Operator> factory;

    /**
     * Main constructor of the factory.
     * Create list and add basic operators.
     */
    public Factory() {
        factory = new ArrayList<>();
        initOperatorList();
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
        return factory.stream()
                .filter(operator -> operator.matches(key))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .clone();
    }

    private void initOperatorList() {
        factory.add(new Plus());
        factory.add(new Minus());
        factory.add(new Multiple());
        factory.add(new Divide());
        factory.add(new Sin());
        factory.add(new Cos());
        factory.add(new Log());
        factory.add(new Pow());
        factory.add(new Sqrt());
        factory.add(new RealNumber());
        factory.add(new ComplexNumber());
    }
}

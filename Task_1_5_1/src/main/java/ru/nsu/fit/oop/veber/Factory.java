package ru.nsu.fit.oop.veber;


import ru.nsu.fit.oop.veber.complex.ComplexNumber;
import ru.nsu.fit.oop.veber.complex.binary.*;
import ru.nsu.fit.oop.veber.complex.unary.ComplexCos;
import ru.nsu.fit.oop.veber.complex.unary.ComplexSin;
import ru.nsu.fit.oop.veber.complex.unary.ComplexSqrt;
import ru.nsu.fit.oop.veber.real.Operator;
import ru.nsu.fit.oop.veber.real.RealNumber;
import ru.nsu.fit.oop.veber.real.binary.*;
import ru.nsu.fit.oop.veber.real.unary.Cos;
import ru.nsu.fit.oop.veber.real.unary.Sin;
import ru.nsu.fit.oop.veber.real.unary.Sqrt;

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
        factory.add(new ComplexPlus());
        factory.add(new ComplexMinus());
        factory.add(new ComplexDivide());
        factory.add(new ComplexPow());
        factory.add(new ComplexMultiple());
        factory.add(new ComplexCos());
        factory.add(new ComplexSin());
        factory.add(new ComplexSqrt());
    }
}

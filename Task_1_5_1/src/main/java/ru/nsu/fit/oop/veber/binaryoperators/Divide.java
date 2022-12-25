package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent divide operation.
 */
public class Divide extends Operator {

    private final static String KEY = "/";
    private final static Integer ARITY = 2;


    @Override
    protected Integer getArity() {
        return ARITY;
    }

    @Override
    protected String getKey() {
        return KEY;
    }


    @Override
    protected Double calculate(List<Double> operands) {
        return operands.get(0) / operands.get(1);
    }
}
    
package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent sqrt operator.
 */
public class Sqrt extends Operator {

    private final static Integer ARITY = 1;


    @Override
    protected Integer getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return Math.sqrt(operands.get(0));
    }
}
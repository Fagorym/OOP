package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent cos operator.
 */
public class Cos extends Operator {

    private final Integer ARITY = 1;


    @Override
    protected Integer getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return Math.cos(operands.get(0));
    }
}
    
package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent pow operation.
 */
public class Pow extends Operator {

    @Override
    protected Integer getArity() {
        return 2;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return Math.pow(operands.get(0), operands.get(1));
    }
}


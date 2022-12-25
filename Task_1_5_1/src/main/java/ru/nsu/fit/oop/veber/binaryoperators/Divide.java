package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent divide operation.
 */
public class Divide extends Operator {


    @Override
    protected Integer getArity() {
        return 2;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return operands.get(0) / operands.get(1);
    }
}
    
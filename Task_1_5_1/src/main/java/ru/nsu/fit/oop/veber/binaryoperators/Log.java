package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent log operation.
 */
public class Log extends Operator {


    @Override
    protected Integer getArity() {
        return 2;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return (Math.log(operands.get(0)) / Math.log(operands.get(1)));
    }
}

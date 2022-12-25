package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.List;

/**
 * Class that represent log operation.
 */
public class Log extends Operator {
    private final static String KEY = "log";
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
        return (Math.log(operands.get(0)) / Math.log(operands.get(1)));
    }
}

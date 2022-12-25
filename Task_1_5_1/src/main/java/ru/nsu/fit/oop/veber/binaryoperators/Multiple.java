package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.List;

/**
 * Class that represents multiple operation.
 */
public class Multiple extends Operator {
    private final static String KEY = "*";
    private final static Integer ARITY = 2;


    /**
     * Function that returns arity of operator.
     *
     * @return arity of operator
     */
    @Override
    protected Integer getArity() {
        return ARITY;
    }

    /**
     * Function that returns key of operator.
     *
     * @return key of operator
     */
    @Override
    protected String getKey() {
        return KEY;
    }

    /**
     * Function that calculates result of operator.
     *
     * @param operands - list of operands that provided for operations
     * @return result of operator
     */
    @Override
    protected Double calculate(List<Double> operands) {
        return operands.get(0) * operands.get(1);
    }
}
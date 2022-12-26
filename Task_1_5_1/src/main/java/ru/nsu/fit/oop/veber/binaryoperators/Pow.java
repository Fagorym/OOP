package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.List;

/**
 * Class that represent pow operation.
 */
public class Pow extends AbstractBinary {

    private final static String KEY = "^";

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
        return Math.pow(operands.get(0), operands.get(1));
    }
}


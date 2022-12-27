package ru.nsu.fit.oop.veber.unaryoperators;

import java.util.List;

/**
 * Class that represent cos operator.
 */
public class Cos extends AbstractUnary {

    private final static String KEY = "cos";

    @Override
    protected Boolean matches(String key) {
        return KEY.equals(key);
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
        return Math.cos(operands.get(0));
    }
}
    
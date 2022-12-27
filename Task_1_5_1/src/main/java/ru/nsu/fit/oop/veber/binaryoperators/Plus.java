package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.numbers.Number;

import java.util.List;

/**
 * Class that represents plus operation
 */
public class Plus extends AbstractBinary {
    private final static String KEY = "+";

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
    public Double calculate(List<Number> operands) {
        return operands.get(0).calculate(operands) + operands.get(1).calculate(operands);
    }
}
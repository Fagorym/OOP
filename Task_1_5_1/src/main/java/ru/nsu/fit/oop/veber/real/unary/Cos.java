package ru.nsu.fit.oop.veber.real.unary;

import ru.nsu.fit.oop.veber.numbers.Number;

import java.util.List;

/**
 * Class that represent cos operator.
 */
public class Cos extends AbstractUnary {

    private final static String KEY = "cos";

    @Override
    public Boolean matches(String key) {
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
        return Math.cos(operands
                .get(0)
                .calculate(operands));
    }
}
    
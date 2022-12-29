package ru.nsu.fit.oop.veber.real.unary;

import ru.nsu.fit.oop.veber.Number;
import ru.nsu.fit.oop.veber.real.RealNumber;

import java.util.List;

/**
 * Class that represent sin operator.
 */
public class Sin extends AbstractUnary {

    private final static String KEY = "sin";

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
        return Math.sin(((RealNumber) operands
                .get(0))
                .calculate(operands));
    }
}
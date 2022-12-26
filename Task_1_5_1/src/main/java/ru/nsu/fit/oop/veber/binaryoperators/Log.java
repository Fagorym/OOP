package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.List;

/**
 * Class that represent log operation.
 */
public class Log extends AbstractBinary {
    private final static String KEY = "log";


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
        return (Math.log(operands.get(0)) / Math.log(operands.get(1)));
    }
}

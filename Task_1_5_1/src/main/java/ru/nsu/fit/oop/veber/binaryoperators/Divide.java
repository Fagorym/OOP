package ru.nsu.fit.oop.veber.binaryoperators;


import java.util.List;

/**
 * Class that represent divide operation.
 */
public class Divide extends AbstractBinary {

    private final static String KEY = "/";


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
        return operands.get(0) / operands.get(1);
    }
}
    
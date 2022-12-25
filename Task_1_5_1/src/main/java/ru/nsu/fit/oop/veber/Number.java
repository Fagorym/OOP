package ru.nsu.fit.oop.veber;

import java.util.List;


/**
 * Class that represent number.
 */
public class Number extends Operator {

    private final String key;

    /**
     * Default constructor for number.
     *
     * @param key will be key of the number
     */
    public Number(String key) {
        this.key = key;

    }

    /**
     * Function that returns arity of operator.
     *
     * @return arity of operator
     */
    @Override
    protected Integer getArity() {
        return 0;
    }

    /**
     * Function that returns key of operator.
     *
     * @return key of operator
     */
    @Override
    protected String getKey() {
        return key;
    }


    /**
     * Function that calculates result of operator.
     *
     * @param operands - list of operands that provided for operations
     * @return result of operator
     */
    @Override
    protected Double calculate(List<Double> operands) {
        return Double.valueOf(key);
    }

}

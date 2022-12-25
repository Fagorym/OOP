package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.List;

/**
 * Class that represent sqrt operator.
 */
public class Sqrt extends Operator {

    private final static Integer ARITY = 1;
    private final static String key = "sqrt";


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
        return Math.sqrt(operands.get(0));
    }
}
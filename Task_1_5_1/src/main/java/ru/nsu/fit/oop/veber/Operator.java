package ru.nsu.fit.oop.veber;


import java.util.List;

/**
 * Abstract class of operator.
 */
abstract public class Operator {

    /**
     * Function that returns arity of operator.
     *
     * @return arity of operator
     */
    protected abstract Integer getArity();


    /**
     * Function that returns key of operator.
     *
     * @return key of operator
     */
    protected abstract String getKey();

    /**
     * Function that calculates result of operator.
     *
     * @param operands - list of operands that provided for operations
     * @return result of operator
     */
    protected abstract Double calculate(List<Double> operands);
}

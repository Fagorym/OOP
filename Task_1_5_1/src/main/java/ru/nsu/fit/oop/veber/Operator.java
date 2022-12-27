package ru.nsu.fit.oop.veber;


import java.util.List;

/**
 * Abstract class of operator.
 */
abstract public class Operator implements Cloneable {

    /**
     * Function that returns arity of operator.
     *
     * @return arity of operator
     */
    protected abstract Integer getArity();

    /**
     * Function that compare operator key with provided string.
     *
     * @return true - if operator key matches this string
     * otherwise - false
     */
    protected abstract Boolean matches(String key);


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

    @Override
    public Operator clone() {
        try {
            return (Operator) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

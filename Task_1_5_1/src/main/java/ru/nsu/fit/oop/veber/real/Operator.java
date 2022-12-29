package ru.nsu.fit.oop.veber.real;


import ru.nsu.fit.oop.veber.Number;

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
    public abstract Integer getArity();

    /**
     * Function that compare operator key with provided string.
     *
     * @return true - if operator key matches this string
     * otherwise - false
     */
    public abstract Boolean matches(String key);


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
    public abstract Object calculate(List<Number> operands);

    /**
     * Function that clone existed operator and return it.
     *
     * @return clone of this operator
     */
    @Override
    public Operator clone() {
        try {
            return (Operator) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

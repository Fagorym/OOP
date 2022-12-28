package ru.nsu.fit.oop.veber.complexOperations;


import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.veber.numbers.Number;

import java.util.List;

/**
 * Abstract class of operator.
 */
abstract public class ComplexOperator implements Cloneable {

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
    public abstract Complex calculate(List<Number> operands);

    @Override
    public ComplexOperator clone() {
        try {
            return (ComplexOperator) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

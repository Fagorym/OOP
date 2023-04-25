package ru.nsu.fit.oop.veber.real;

import ru.nsu.fit.oop.veber.Number;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Class that represent RealNumber.
 */
public class RealNumber extends Number {

    private String key;


    /**
     * Function that return arity of number.
     *
     * @return return arity of number
     */
    @Override
    public Integer getArity() {
        return 0;
    }

    /**
     * Function that compare key with regex pattern
     *
     * @param key - input string key
     * @return true - if key matches pattern
     * otherwise - false
     */
    @Override
    public Boolean matches(String key) {
        if (Pattern.matches("(\\d+([.]\\d+)??)", key)) {
            this.key = key;
            return true;
        }
        return false;
    }

    /**
     * Function that return key of operator.
     *
     * @return key of operator
     */
    @Override
    protected String getKey() {
        return key;
    }

    /**
     * Function that return Double value of key.
     *
     * @param operands - list of operands that provided for operations
     * @return double value of key
     */
    @Override
    public Double calculate(List<Number> operands) {
        return Double.valueOf(key);
    }

    /**
     * Function that clone real number with key.
     *
     * @return clone of RealNumber
     */

    @Override
    public RealNumber clone() {
        RealNumber clone = (RealNumber) super.clone();
        clone.key = key;
        return clone;
    }
}

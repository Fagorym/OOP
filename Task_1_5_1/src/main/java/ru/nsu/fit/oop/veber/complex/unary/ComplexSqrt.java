package ru.nsu.fit.oop.veber.complex.unary;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.veber.Number;
import ru.nsu.fit.oop.veber.complex.ComplexNumber;

import java.util.List;

/**
 * Class that represent complex square root operation.
 */
public class ComplexSqrt extends AbstractComplexUnary {
    private final static String KEY = "sqrt`";

    /**
     * Function that compare input key with KEY of operator.
     *
     * @param key - input key
     * @return true - if keys equals
     * otherwise - false
     */
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

    @Override
    public Complex calculate(List<Number> operands) {
        return ((ComplexNumber) operands.get(0)).calculate(operands).sqrt();
    }
}

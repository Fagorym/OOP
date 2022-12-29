package ru.nsu.fit.oop.veber.complex.binary;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.veber.Number;
import ru.nsu.fit.oop.veber.complex.ComplexNumber;

import java.util.List;

public class ComplexLog extends AbstractComplexBinary {

    private static final String KEY = "log`";

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
        Complex fst = ((ComplexNumber) operands.get(0)).calculate(operands);
        Complex snd = ((ComplexNumber) operands.get(1)).calculate(operands);
        return fst.log().divide(snd.log());
    }
}

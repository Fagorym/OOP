package ru.nsu.fit.oop.veber.complex.binary;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.veber.Number;
import ru.nsu.fit.oop.veber.complex.ComplexNumber;
import ru.nsu.fit.oop.veber.complex.ComplexOperator;

import java.util.List;

public class ComplexMinus extends AbstractComplexBinary {
    private final static String KEY = "-`";
    @Override
    public Boolean matches(String key) {
        return KEY.equals(key);
    }

    @Override
    protected String getKey() {
        return KEY;
    }

    @Override
    public Complex calculate(List<Number> operands) {
        Complex fst = ((ComplexNumber) operands.get(0)).calculate(operands);
        Complex snd = ((ComplexNumber) operands.get(1)).calculate(operands);
        return fst.subtract(snd);
    }
}

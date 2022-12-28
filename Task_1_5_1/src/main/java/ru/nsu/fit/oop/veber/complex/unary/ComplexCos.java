package ru.nsu.fit.oop.veber.complex.unary;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.veber.Number;
import ru.nsu.fit.oop.veber.complex.ComplexNumber;

import java.util.List;

public class ComplexCos extends AbstractComplexUnary {
    private final static String KEY = "cos`";

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
        return ((ComplexNumber) operands.get(0)).calculate(operands).cos();
    }
}

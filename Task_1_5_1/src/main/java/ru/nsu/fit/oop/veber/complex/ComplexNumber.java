package ru.nsu.fit.oop.veber.complex;

import org.apache.commons.numbers.complex.Complex;

import java.util.List;
import java.util.regex.Pattern;

public class ComplexNumber extends ComplexOperator {

    private String key;

    @Override
    public Complex calculate(List<Number> operands) {
        return Complex.ofCartesian(1, 0);
    }

    @Override
    public Integer getArity() {
        return 0;
    }

    @Override
    public Boolean matches(String key) {
        if (Pattern.matches("\\d*[.]\\d*[+]\\d*[.]\\d*[i]", key)) {
            this.key = key;

            return true;
        }
        return false;
    }

    @Override
    protected String getKey() {
        return key;
    }

    @Override
    public ComplexNumber clone() {
        ComplexNumber clone = (ComplexNumber) super.clone();
        clone.key = key;
        return clone;
    }
}

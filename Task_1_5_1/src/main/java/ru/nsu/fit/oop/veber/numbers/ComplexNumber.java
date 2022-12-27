package ru.nsu.fit.oop.veber.numbers;

import java.util.List;
import java.util.regex.Pattern;

public class ComplexNumber extends Number {

    private String key;

    @Override
    public Double calculate(List<Number> operands) {
        return Double.valueOf(key);
    }

    @Override
    protected Boolean matches(String key) {
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
    public Number clone() {
        ComplexNumber clone = (ComplexNumber) super.clone();
        clone.key = key;
        return clone;
    }
}

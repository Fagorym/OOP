package ru.nsu.fit.oop.veber.numbers;

import java.util.List;
import java.util.regex.Pattern;

public class RealNumber extends Number {

    private String key;


    @Override
    protected Boolean matches(String key) {
        if (Pattern.matches("d+", key)) {
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
    protected Double calculate(List<Double> operands) {
        return Double.valueOf(key);
    }

    @Override
    public Number clone() {
        RealNumber clone = (RealNumber) super.clone();
        clone.key = key;
        return clone;
    }
}

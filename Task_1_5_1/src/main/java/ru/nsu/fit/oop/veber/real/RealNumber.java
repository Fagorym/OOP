package ru.nsu.fit.oop.veber.real;

import ru.nsu.fit.oop.veber.Number;

import java.util.List;
import java.util.regex.Pattern;

public class RealNumber extends Number {

    private String key;


    @Override
    public Integer getArity() {
        return 0;
    }

    @Override
    public Boolean matches(String key) {
        if (Pattern.matches("(\\d+([.]\\d+)??)", key)) {
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
    public Double calculate(List<Number> operands) {
        return Double.valueOf(key);
    }

    @Override
    public RealNumber clone() {
        RealNumber clone = (RealNumber) super.clone();
        clone.key = key;
        return clone;
    }
}

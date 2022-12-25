package ru.nsu.fit.oop.veber;

import java.util.Deque;
import java.util.List;

public class Number extends Operator {

    private final Double key;

    public Number(String key) {
        this.key = Double.valueOf(key);

    }

    @Override
    protected Integer getArity() {
        return 0;
    }

    @Override
    protected String getKey() {
        return "";
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return key;
    }

}

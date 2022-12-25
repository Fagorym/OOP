package ru.nsu.fit.oop.veber;

import java.util.Deque;
import java.util.List;

public class Number extends Operator {

    private final String key;

    public Number(String key) {
        this.key = key;

    }

    @Override
    protected Integer getArity() {
        return 0;
    }

    @Override
    protected String getKey() {
        return key;
    }


    @Override
    protected Double calculate(List<Double> operands) {
        return Double.valueOf(key);
    }

}

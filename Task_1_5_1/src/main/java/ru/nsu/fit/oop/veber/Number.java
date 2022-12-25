package ru.nsu.fit.oop.veber;

import java.util.Deque;

public class Number extends Operator{
    @Override
    protected Integer getArity() {
        return 0;
    }

    @Override
    protected Double calculate(Deque<Double> operands) {
        return null;
    }
}

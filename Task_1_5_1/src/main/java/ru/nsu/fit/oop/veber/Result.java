package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.real.Operator;

import java.util.List;

public class Result extends Operator {

    private final String value;

    public Result(Object number) {
        this.value = number.toString();
    }


    @Override
    public Integer getArity() {
        return 0;
    }

    @Override
    public Boolean matches(String key) {
        return null;
    }

    @Override
    protected String getKey() {
        return value;
    }

    @Override
    public Object calculate(List<Number> operands) {
        return null;
    }
}

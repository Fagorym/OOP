package ru.nsu.fit.oop.veber.real.binary;

import ru.nsu.fit.oop.veber.real.Operator;

abstract public class AbstractBinary extends Operator {
    private static final Integer ARITY = 2;

    @Override
    public Integer getArity() {
        return ARITY;
    }
}

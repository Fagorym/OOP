package ru.nsu.fit.oop.veber.doubleoperators.binaryoperators;

import ru.nsu.fit.oop.veber.doubleoperators.Operator;

abstract public class AbstractBinary extends Operator {
    private static final Integer ARITY = 2;

    @Override
    public Integer getArity() {
        return ARITY;
    }
}

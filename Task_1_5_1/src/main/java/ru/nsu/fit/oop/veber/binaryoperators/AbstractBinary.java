package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

abstract public class AbstractBinary extends Operator {
    private static final Integer ARITY = 2;

    @Override
    protected Integer getArity() {
        return ARITY;
    }
}

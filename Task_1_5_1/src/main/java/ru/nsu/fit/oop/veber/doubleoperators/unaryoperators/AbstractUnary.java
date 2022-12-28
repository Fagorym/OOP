package ru.nsu.fit.oop.veber.doubleoperators.unaryoperators;

import ru.nsu.fit.oop.veber.doubleoperators.Operator;

abstract public class AbstractUnary extends Operator {

    private static final Integer ARITY = 1;

    @Override
    public Integer getArity() {
        return ARITY;
    }
}

package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

abstract public class AbstractUnary extends Operator {

    private static final Integer ARITY = 1;

    @Override
    protected Integer getArity() {
        return ARITY;
    }
}

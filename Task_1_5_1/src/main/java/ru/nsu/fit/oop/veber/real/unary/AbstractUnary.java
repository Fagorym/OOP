package ru.nsu.fit.oop.veber.real.unary;

import ru.nsu.fit.oop.veber.real.Operator;

abstract public class AbstractUnary extends Operator {

    private static final Integer ARITY = 1;

    /**
     * Function that return arity of operator.
     *
     * @return arity of operator
     */
    @Override
    public Integer getArity() {
        return ARITY;
    }
}

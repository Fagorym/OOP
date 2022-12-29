package ru.nsu.fit.oop.veber.real.binary;

import ru.nsu.fit.oop.veber.real.Operator;

/**
 * Abstract binary operator.
 */
abstract public class AbstractBinary extends Operator {


    private static final Integer ARITY = 2;

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

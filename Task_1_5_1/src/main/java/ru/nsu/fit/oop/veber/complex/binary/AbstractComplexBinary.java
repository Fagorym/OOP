package ru.nsu.fit.oop.veber.complex.binary;

import ru.nsu.fit.oop.veber.complex.ComplexOperator;

/**
 * Class that represent Abstract Complex Binary operator.
 */
abstract public class AbstractComplexBinary extends ComplexOperator {

    /**
     * Function that return arity of binary operator.
     *
     * @return arity of binary operator
     */
    @Override
    public Integer getArity() {
        return 2;
    }
}

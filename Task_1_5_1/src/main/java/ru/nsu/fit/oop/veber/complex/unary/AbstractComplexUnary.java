package ru.nsu.fit.oop.veber.complex.unary;

import ru.nsu.fit.oop.veber.complex.ComplexOperator;

abstract public class AbstractComplexUnary extends ComplexOperator {
    @Override
    public Integer getArity() {
        return 1;
    }
}

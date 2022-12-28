package ru.nsu.fit.oop.veber.complex.binary;

import ru.nsu.fit.oop.veber.complex.ComplexOperator;

abstract public class AbstractComplexBinary extends ComplexOperator {

    @Override
    public Integer getArity() {
        return 2;
    }
}

package ru.nsu.fit.oop.veber;


import java.util.List;

/**
 * Marker interface for operators.
 */
abstract public class Operator {

    protected abstract Integer getArity();


    protected abstract String getKey();
    protected abstract Double calculate(List<Double> operands);
}

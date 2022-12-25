package ru.nsu.fit.oop.veber;


import java.util.Deque;
import java.util.List;

/**
 * Marker interface for operators.
 */
abstract public class Operator {

    Integer ARITY;
    private Deque<Double> operands;

    protected abstract Integer getArity();


    protected abstract Double calculate(List<Double> operands);
}

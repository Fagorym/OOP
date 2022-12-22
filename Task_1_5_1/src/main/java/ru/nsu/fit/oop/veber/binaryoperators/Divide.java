package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represent divide operation.
 */
public class Divide extends AbstractBinary implements Operator {

    private final Deque<Float> expression;

    public Divide(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates division of two arguments.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return division of two arguments
     */

    public Float calculate(Float fst, Float snd) {
        return fst / snd;
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}
    
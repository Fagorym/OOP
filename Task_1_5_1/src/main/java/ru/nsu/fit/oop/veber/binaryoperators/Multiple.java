package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represents multiple operation.
 */
public class Multiple extends AbstractBinary implements Operator {

    private final Deque<Float> expression;

    public Multiple(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates multiple of two arguments.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return muplitle of two arguments
     */
    public float calculate(float fst, float snd) {
        return fst * snd;
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}
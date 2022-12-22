package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represents plus operation
 */
public class Plus extends AbstractBinary implements Operator {

    private final Deque<Float> expression;

    public Plus(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates sum of two arguments.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return sum of two arguments
     */
    public float calculate(float fst, float snd) {
        return fst + snd;
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }

}
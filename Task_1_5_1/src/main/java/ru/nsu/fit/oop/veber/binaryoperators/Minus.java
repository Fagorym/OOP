package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represents minus operation
 */
public class Minus extends AbstractBinary implements Operator {

    private final Deque<Float> expression;

    public Minus(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates subtraction of two arguments.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return subtraction of two arguments
     */
    public float calculate(float fst, float snd) {
        return fst - snd;
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}

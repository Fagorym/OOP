package ru.nsu.fit.oop.veber.binaryoperators;


import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represent pow operation.
 */
public class Pow extends AbstractBinary implements Operator {
    private final Deque<Float> expression;

    public Pow(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates first arguments in pow of second argument.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return first arguments in pow of second argument
     */
    public float calculate(float fst, float snd) {
        return (float) Math.pow(fst, snd);
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }

}


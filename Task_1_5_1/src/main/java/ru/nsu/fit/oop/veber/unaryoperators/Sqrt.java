package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represent sqrt operator.
 */
public class Sqrt extends AbstractUnary implements Operator {

    private final Deque<Float> expression;

    public Sqrt(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates the sqrt of argument.
     *
     * @param fst - argument of expression
     * @return sqrt of the argument
     */
    public float calculate(float fst) {
        return (float) Math.sqrt(fst);
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}
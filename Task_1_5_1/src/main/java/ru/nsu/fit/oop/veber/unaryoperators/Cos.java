package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represent cos operator.
 */
public class Cos extends AbstractUnary implements Operator {

    private final Deque<Float> expression;

    public Cos(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates the cosinus of argument.
     *
     * @param fst - argument of expression
     * @return - cos of argument
     */
    public float calculate(float fst) {
        return (float) Math.cos(fst);
    }


    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}
    
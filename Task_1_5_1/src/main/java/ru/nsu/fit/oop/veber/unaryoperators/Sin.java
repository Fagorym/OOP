package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represent sin operator.
 */
public class Sin extends AbstractUnary implements Operator {

    private final Deque<Float> expression;

    public Sin(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates the sinus of argument.
     *
     * @param fst - argument of expression
     * @return - sin of argument
     */
    public float calculate(float fst) {
        return (float) Math.sin(fst);
    }


    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}
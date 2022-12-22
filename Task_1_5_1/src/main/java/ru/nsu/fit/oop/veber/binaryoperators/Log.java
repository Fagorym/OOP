package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;

/**
 * Class that represent log operation.
 */
public class Log extends AbstractBinary implements Operator {
    private final Deque<Float> expression;

    public Log(Deque<Float> expression) {
        this.expression = expression;
    }

    /**
     * Calculates log of first argument by second argument.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return log of first argument by second argument
     */
    public Float calculate(Float fst, Float snd) {
        return (float) (Math.log(fst) / Math.log(snd));
    }

    @Override
    public Float evaluate() {
        return super.parseExpression(this::calculate, expression);
    }
}

package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class that represent calculator.
 */
public class Calculator {
    private final Deque<Float> floatDeque;
    private final Factory factory;
    private String expression;

    /**
     * Main constructor of calculator, with empty expression.
     */
    public Calculator() {
        this.floatDeque = new ArrayDeque<>();
        this.factory = new Factory(floatDeque);
    }

    /**
     * Additional constructor of calculator, with non-empty expression.
     *
     * @param expression - will be calculated expression
     */
    public Calculator(String expression) {
        this();
        this.expression = expression;

    }

    /**
     * Setter for expression.
     *
     * @param expression - will be calculated expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Counts the expression, that was settled by constructor or setter.
     *
     * @return result of the calculated expression
     */
    public Float calculate() {
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (factory.getOperators().contains(token)) {
                Operator operator = parseExpr(token);
                Float result;
                result = operator.evaluate();
                floatDeque.addFirst(result);
            } else if (Character.isDigit(token.charAt(0))) {
                floatDeque.addFirst(Float.valueOf(token));
            } else {
                throw new IllegalArgumentException("Check your expression line");
            }
        }
        return floatDeque.pollFirst();

    }

    private Operator parseExpr(String operator) {
        return factory.getOperator(operator);
    }

}
package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private final Deque<Float> floatDeque;
    private final Factory factory;
    private String expression;

    public Calculator() {
        this.floatDeque = new ArrayDeque<>();
        this.factory = new Factory();
    }

    public Calculator(String expression) {
        this();
        this.expression = expression;

    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Float calculate() {
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (factory.getOperators().contains(token)) {
                Operator operator = parseExpr(token);
                float result;
                if (operator instanceof BinaryOperator operator1) {
                    float first = floatDeque.pollFirst();
                    float second = floatDeque.pollFirst();
                    result = operator1.calculate(first, second);
                } else if (operator instanceof UnaryOperator operator1) {
                    float first = floatDeque.pollFirst();
                    result = operator1.calculate(first);

                } else {
                    throw new IllegalArgumentException("Something went wrong, check your expression line");
                }
                floatDeque.addFirst(result);
            } else if (Character.isDigit(token.charAt(0))) {
                floatDeque.addFirst(Float.valueOf(token));
            } else {
                throw new IllegalArgumentException("Something went wrong, check your expression line");
            }
        }
        return floatDeque.pollFirst();

    }

    private Operator parseExpr(String operator) {
        return factory.getOperator(operator);
    }

}
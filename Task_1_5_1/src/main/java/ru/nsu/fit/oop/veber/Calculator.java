package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private final String operators;
    private final Deque<Float> floatDeque;
    private String expression;

    public Calculator() {
        this.operators = "+-/*";
        this.floatDeque = new ArrayDeque<>();
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
            if (operators.contains(token)) {
                Float firstOperand = floatDeque.pollFirst();
                Float secondOperand = floatDeque.pollFirst();
                Float result = parseExpr(token, firstOperand, secondOperand);
                floatDeque.addFirst(result);
            } else if (Character.isDigit(token.charAt(0))) {
                floatDeque.addFirst(Float.valueOf(token));
            } else {
                throw new IllegalArgumentException("Something went wrong, check your expression line");
            }
        }
        return floatDeque.pollFirst();

    }

    private Float parseExpr(String operator, Float firstOperand, Float secondOperand) {
        return switch (operator) {
            case "+" -> firstOperand + secondOperand;

            case "-" -> firstOperand - secondOperand;

            case "*" -> firstOperand * secondOperand;

            case "/" -> firstOperand / secondOperand;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };

    }

}
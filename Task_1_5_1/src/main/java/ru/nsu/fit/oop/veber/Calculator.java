package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {
    private final String operators;
    private final Deque<Float> floatDeque;
    private String expression;
    private Integer index;

    public Calculator() {
        this.operators = "+-/*";
        this.floatDeque = new ArrayDeque<>();
        this.index = 0;
    }

    public Calculator(String expression) {
        this();
        this.expression = expression;
        this.index = expression.length();

    }

    public void setExpression(String expression) {
        this.index = expression.length() - 1;
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
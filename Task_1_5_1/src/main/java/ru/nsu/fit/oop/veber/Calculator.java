package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Calculator {
    private final String operators;
    private String expression;
    private Deque<Float> floatDeque;
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

    public Float calculate() {
        for (int i = index; i >= 0; i--) {
            String token = String.valueOf(expression.charAt(i));
            if (operators.contains(token)) {
                Float firstOperand = floatDeque.getFirst();
                Float secondOperand = floatDeque.getFirst();
                Float result = parseExpr(expression.charAt(i), firstOperand, secondOperand);
                floatDeque.addFirst(result);
            } else {
                floatDeque.addFirst(Float.valueOf(token));
            }
        }
        return floatDeque.getFirst();

    }

    private Float parseExpr(char operator, Float firstOperand, Float secondOperand) {
        return switch (operator) {
            case '+' -> firstOperand + secondOperand;

            case '-' -> firstOperand - secondOperand;

            case '*' -> firstOperand * secondOperand;

            case '/' -> firstOperand / secondOperand;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };

    }

}
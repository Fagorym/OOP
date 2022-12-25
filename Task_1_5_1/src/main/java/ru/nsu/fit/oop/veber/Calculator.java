package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Class that represent calculator.
 */
public class Calculator {
    private final Deque<Double> floatDeque;
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
    public Double calculate() {
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            {
                Operator operator = parseExpr(tokens[i]);
                List<Double> operands = new ArrayList<>();
                for (int j = 0; j < operator.getArity(); j++) {
                    operands.add(Double.valueOf(tokens[i--]));
                }
                Double result = operator.calculate(operands);
                floatDeque.addFirst(result);
            }
        }
        return floatDeque.pollFirst();

    }

    private Operator parseExpr(String operator) {
        return factory.getOperator(operator);
    }

}
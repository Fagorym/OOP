package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.real.Operator;

import java.util.*;

/**
 * Class that represent calculator.
 */
public class Calculator {
    private final Deque<Number> floatDeque;
    private final Factory factory;
    private String expression;

    /**
     * Main constructor of calculator, with empty expression.
     */
    public Calculator() {
        this.floatDeque = new ArrayDeque<>();
        this.factory = new Factory();
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
    public String calculate() {
        String[] tokens = expression.split(" ");
        for (int i = tokens.length - 1; i >= 0; i--) {
            {
                Operator operator = factory.getOperator(tokens[i]);
                List<Number> operands = new ArrayList<>();
                for (int j = 0; j < operator.getArity(); j++) {
                    operands.add(floatDeque.pollFirst());
                }
                Result result = new Result(operator.calculate(operands));
                Number number = (Number) factory.getOperator(result.getKey());
                floatDeque.addFirst(number);
            }
        }
        return floatDeque.pollFirst().calculate(Collections.emptyList()).toString();
    }
}
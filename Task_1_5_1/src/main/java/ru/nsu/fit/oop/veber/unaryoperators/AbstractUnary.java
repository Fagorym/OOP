package ru.nsu.fit.oop.veber.unaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.function.UnaryOperator;

abstract public class AbstractUnary implements Operator {
    public Float parseExpression(UnaryOperator<Float> operator, Deque<Float> deque) {
        return operator.apply(deque.pollFirst());
    }
}

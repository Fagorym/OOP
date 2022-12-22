package ru.nsu.fit.oop.veber.binaryoperators;

import ru.nsu.fit.oop.veber.Operator;

import java.util.Deque;
import java.util.function.BinaryOperator;

abstract public class AbstractBinary implements Operator {
    public Float parseExpression(BinaryOperator<Float> operator, Deque<Float> deque) {
        return operator.apply(deque.pollFirst(), deque.pollFirst());
    }
}

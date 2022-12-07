package ru.nsu.fit.oop.veber.CalculatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.veber.Calculator;

public class CalculatorTests {
    private Calculator calculator;

    @BeforeEach
    public void initCalculator() {
        calculator = new Calculator();
    }

    private void checkExpression(String expression, float expected) {
        calculator.setExpression(expression);
        Float result = calculator.calculate();
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void plusExpressionTest() {
        checkExpression("+ 1 2", 3);
    }

    @Test
    public void minusExpressionTest() {
        checkExpression("- 5 2", 3);
    }

    @Test
    public void multipleExpressionTest() {
        checkExpression("* 100 2", 200);
    }

    @Test
    public void divideExpressionTest() {
        checkExpression("/ 200 100", 2);
    }

    @Test
    public void powExpressionTest() {
        checkExpression("pow 2 4", 16);
    }

    @Test
    public void sinExpressionTest() {
        checkExpression("sin 0", 0);
    }

    @Test
    public void cosExpressionTest() {
        checkExpression("cos 0", 1);
    }

    @Test
    public void sqrtExpressionTest() {
        checkExpression("sqrt 100", 10);
    }
}

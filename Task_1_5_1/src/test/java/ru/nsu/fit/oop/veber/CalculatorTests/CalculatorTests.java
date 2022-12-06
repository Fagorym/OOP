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

    @Test
    public void firstExpressionTest() {
        calculator.setExpression("+ 1 2");
        Float result = calculator.calculate();
        Assertions.assertEquals(3, result);
    }

    @Test
    public void secondExpressionTest() {
        calculator = new Calculator("+ + + 1 2 3 4");
        Float result = calculator.calculate();
        Assertions.assertEquals(10, result);
    }

    @Test
    public void minusExpressionTest() {
        calculator.setExpression("- 5 2");
        Float result = calculator.calculate();
        Assertions.assertEquals(3, result);
    }

    @Test
    public void strongExpressionTest() {
        calculator.setExpression("+ - + - + 100 1 1 10 15 105");
        Float result = calculator.calculate();
        Assertions.assertEquals(200, result);
    }
}

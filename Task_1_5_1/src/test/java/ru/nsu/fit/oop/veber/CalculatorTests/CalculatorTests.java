package ru.nsu.fit.oop.veber.CalculatorTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.nsu.fit.oop.veber.Calculator;

public class CalculatorTests {
    private Calculator calculator;

    @BeforeEach
    public void initCalculator() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({
            "+ 1 2, 3",
            "- 5 2, 3",
            "* 100 2, 200",
            "/ 200 100, 2",
            "^ 2 4, 16",
            "sin 0, 0",
            "cos 0, 1",
            "sqrt 100, 10",
            "log 8 2, 3"
    })
    public void checkExpression(String expression, float expected) {
        calculator.setExpression(expression);
        Double result = calculator.calculate();
        Assertions.assertEquals(expected, result);

    }
}

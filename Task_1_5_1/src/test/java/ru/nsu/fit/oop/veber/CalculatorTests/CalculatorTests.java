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
            "+ 1 2, 3.0",
            "- 5 2, 3.0",
            "* 100 2, 200.0",
            "/ 200 100, 2.0",
            "^ 2 4, 16.0",
            "sin 0, 0.0",
            "cos 0, 1.0",
            "sqrt 100, 10.0",
            "log 8 2, 3.0"
    })
    public void checkExpression(String expression, String expected) {
        calculator.setExpression(expression);
        String result = calculator.calculate();
        Assertions.assertEquals(expected, result);

    }


    @ParameterizedTest
    @CsvSource({
            "+` 3+2i 4+2i, (7.0,4.0)",
            "-` 4+2i 2+2i, (2.0,0.0)",
            "*` 19+2i 135+100i, (2365.0,2170.0)",
            "/` 100+50i 2+2i, (37.5,-12.5)",
            "^` 2+2i 2+2i, (-1.452504627055703,-0.8098895463353006)",
            "sin` 4+4i, (-20.66693875274718,-17.837880289798733)",
            "cos` 1+2i, (2.0327230070196656,-3.0518977991518)",
            "sqrt` 3+2i, (1.8173540210239707,0.5502505227003375)"
    })
    public void checkComplexExpression(String expression, String expectedReal, String expectedImage) {
        calculator.setExpression(expression);
        String result = calculator.calculate();
        Assertions.assertEquals(expectedReal + ',' + expectedImage, result);
    }
}

package ru.nsu.fit.oop.veber;

public interface BinaryOperator extends Operator {

    int argCount = 2;

    public class Plus implements BinaryOperator {

        @Override
        public Float calculate() {
            return null;
        }
    }

    public class Minus implements BinaryOperator {

        @Override
        public Float calculate() {
            return null;
        }
    }

    public class Multiple implements BinaryOperator {

        @Override
        public Float calculate() {
            return null;
        }
    }

    public class Divide implements BinaryOperator {
        @Override
        public Float calculate() {
            return null;
        }
    }
}

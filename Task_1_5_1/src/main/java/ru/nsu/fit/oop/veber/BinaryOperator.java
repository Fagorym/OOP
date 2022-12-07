package ru.nsu.fit.oop.veber;

public interface BinaryOperator extends Operator {

    int argCount = 2;


    Float calculate(Float fst, Float snd);

    public class Plus implements BinaryOperator {

        @Override
        public Float calculate(Float fst, Float snd) {
            return fst + snd;
        }
    }

    public class Minus implements BinaryOperator {

        @Override
        public Float calculate(Float fst, Float snd) {
            return fst - snd;
        }
    }

    public class Multiple implements BinaryOperator {

        @Override
        public Float calculate(Float fst, Float snd) {
            return fst * snd;
        }
    }

    public class Divide implements BinaryOperator {
        @Override
        public Float calculate(Float fst, Float snd) {
            return fst / snd;
        }
    }
}

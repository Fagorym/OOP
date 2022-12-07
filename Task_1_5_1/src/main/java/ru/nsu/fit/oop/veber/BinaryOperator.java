package ru.nsu.fit.oop.veber;

public interface BinaryOperator extends Operator {

    int argCount = 2;


    float calculate(float fst, float snd);

    class Plus implements BinaryOperator {

        @Override
        public float calculate(float fst, float snd) {
            return fst + snd;
        }
    }

    class Minus implements BinaryOperator {

        @Override
        public float calculate(float fst, float snd) {
            return fst - snd;
        }
    }

    class Multiple implements BinaryOperator {

        @Override
        public float calculate(float fst, float snd) {
            return fst * snd;
        }
    }

    class Divide implements BinaryOperator {
        @Override
        public float calculate(float fst, float snd) {
            return fst / snd;
        }
    }

    class Pow implements BinaryOperator {
        @Override
        public float calculate(float fst, float snd) {
            return (float) Math.pow(fst, snd);
        }
    }

    class Log implements BinaryOperator {
        @Override
        public float calculate(float fst, float snd) {
            return (float) (Math.log(fst) / Math.log(snd));
        }
    }
}

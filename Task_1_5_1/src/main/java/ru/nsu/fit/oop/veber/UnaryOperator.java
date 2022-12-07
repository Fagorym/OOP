package ru.nsu.fit.oop.veber;

import java.lang.Math;

public interface UnaryOperator extends Operator {

    float calculate(float fst);

    class Sin implements UnaryOperator {
        @Override
        public float calculate(float fst) {
            return (float) Math.sin(fst);
        }
    }

    class Cos implements UnaryOperator {
        @Override
        public float calculate(float fst) {
            return (float) Math.cos(fst);
        }
    }

    class Sqrt implements UnaryOperator {
        @Override
        public float calculate(float fst) {
            return (float) Math.sqrt(fst);
        }
    }
}

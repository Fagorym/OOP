package ru.nsu.fit.oop.veber;

import java.lang.Math;

/**
 * Interface for operators, that operates with one argument.
 */
public interface UnaryOperator extends Operator {

    /**
     * Main function of operator, that calculates the expression.
     *
     * @param fst - argument of expression
     * @return - result of expression
     */
    float calculate(float fst);

    /**
     * Class that represent sin operator.
     */
    class Sin implements UnaryOperator {
        /**
         * Calculates the sinus of argument.
         *
         * @param fst - argument of expression
         * @return - sin of argument
         */
        @Override
        public float calculate(float fst) {
            return (float) Math.sin(fst);
        }
    }

    /**
     * Class that represent cos operator.
     */
    class Cos implements UnaryOperator {
        /**
         * Calculates the cosinus of argument.
         *
         * @param fst - argument of expression
         * @return - cos of argument
         */
        @Override
        public float calculate(float fst) {
            return (float) Math.cos(fst);
        }
    }

    /**
     * Class that represent sqrt operator.
     */
    class Sqrt implements UnaryOperator {
        /**
         * Calculates the sqrt of argument.
         *
         * @param fst - argument of expression
         * @return sqrt of the argument
         */
        @Override
        public float calculate(float fst) {
            return (float) Math.sqrt(fst);
        }
    }
}

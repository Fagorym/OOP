package ru.nsu.fit.oop.veber;

/**
 * Interface for operators, that operates with two arguments.
 */
public interface BinaryOperator extends Operator {

    /**
     * Main function of operator, that calculates the expression.
     *
     * @param fst - first argument of expression
     * @param snd - second argument of expression
     * @return - result of expression
     */
    float calculate(float fst, float snd);

    /**
     * Class that represents plus operation
     */
    class Plus implements BinaryOperator {

        /**
         * Calculates sum of two arguments.
         *
         * @param fst - first argument of expression
         * @param snd - second argument of expression
         * @return sum of two arguments
         */
        @Override
        public float calculate(float fst, float snd) {
            return fst + snd;
        }
    }

    /**
     * Class that represents minus operation
     */
    class Minus implements BinaryOperator {

        /**
         * Calculates subtraction of two arguments.
         *
         * @param fst - first argument of expression
         * @param snd - second argument of expression
         * @return subtraction of two arguments
         */
        @Override
        public float calculate(float fst, float snd) {
            return fst - snd;
        }
    }

    /**
     * Class that represents multiple operation.
     */
    class Multiple implements BinaryOperator {

        /**
         * Calculates multiple of two arguments.
         *
         * @param fst - first argument of expression
         * @param snd - second argument of expression
         * @return muplitle of two arguments
         */
        @Override
        public float calculate(float fst, float snd) {
            return fst * snd;
        }
    }

    /**
     * Class that represent divide operation.
     */
    class Divide implements BinaryOperator {
        /**
         * Calculates division of two arguments.
         *
         * @param fst - first argument of expression
         * @param snd - second argument of expression
         * @return division of two arguments
         */
        @Override
        public float calculate(float fst, float snd) {
            return fst / snd;
        }
    }

    /**
     * Class that represent pow operation.
     */
    class Pow implements BinaryOperator {
        /**
         * Calculates first arguments in pow of second argument.
         *
         * @param fst - first argument of expression
         * @param snd - second argument of expression
         * @return first arguments in pow of second argument
         */
        @Override
        public float calculate(float fst, float snd) {
            return (float) Math.pow(fst, snd);
        }
    }

    /**
     * Class that represent log operation.
     */
    class Log implements BinaryOperator {
        /**
         * Calculates log of first argument by second argument.
         *
         * @param fst - first argument of expression
         * @param snd - second argument of expression
         * @return log of first argument by second argument
         */
        @Override
        public float calculate(float fst, float snd) {
            return (float) (Math.log(fst) / Math.log(snd));
        }
    }
}

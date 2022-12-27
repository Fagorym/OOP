package ru.nsu.fit.oop.veber.numbers;

import ru.nsu.fit.oop.veber.Operator;


/**
 * Class that represent number.
 */
abstract public class Number extends Operator implements Cloneable {


    /**
     * Function that returns arity of operator.
     *
     * @return arity of operator
     */
    @Override
    protected Integer getArity() {
        return 0;
    }


    @Override
    public Number clone() {
        return (Number) super.clone();
    }
}
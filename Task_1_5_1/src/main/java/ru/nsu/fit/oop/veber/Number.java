package ru.nsu.fit.oop.veber;

import ru.nsu.fit.oop.veber.real.Operator;


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
    public Integer getArity() {
        return 0;
    }


    @Override
    public Number clone() {
        return (Number) super.clone();
    }
}

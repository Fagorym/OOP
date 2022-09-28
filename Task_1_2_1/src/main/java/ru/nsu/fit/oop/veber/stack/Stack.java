package ru.nsu.fit.oop.veber.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * This class is used to store your elements of all types
 * Main idea of this class - first in - first out
 * It means that element that you pushed the last, will be pop the first
 */
public class Stack {
    private final static int MAX_SIZE = 1000;
    private final int capacity;
    private int topIdx;
    private Object[] arr;

    Stack() {
        this.arr = new Object[MAX_SIZE];
        this.capacity = MAX_SIZE;
        this.topIdx = -1;
    }

    Stack(int size) {
        this.arr = new Object[size];
        this.capacity = size;
        this.topIdx = -1;
    }

    /**
     * @param elem - elem that you need to push in your stack
     *             it must match type of other Stack elements
     * @return boolean - false, when stack is full
     * true - when elem pushed successfully
     */
    public boolean push(Object elem) {
        if (topIdx >= capacity - 1) {
            return false;
        } else {
            arr[++topIdx] = elem;
            return true;
        }
    }

    /**
     * @param elems - elems that you need to push in your stack
     *              it must match type of other Stack elements
     * @return boolean - false, when stack is full
     * true - when elems pushed successfully
     */
    public boolean pushStack(Stack elems) {
        if (topIdx >= capacity - (elems.topIdx + 1)) {
            return false;
        } else {
            for (Object elem : elems.arr) {
                if (elem != null) push(elem);
            }
            return true;
        }
    }

    /**
     * @return Object - element that matches Stack type
     * @throws EmptyStackException - when you try to pop empty stack
     */
    public Object pop() throws EmptyStackException {
        if (topIdx == -1) {
            throw new EmptyStackException();
        } else {
            Object temp = arr[topIdx];
            arr[topIdx--] = null;
            return temp;
        }

    }

    /**
     * Function that pop elements by count
     *
     * @param count - count of needed elements
     * @return Variable of Stack class
     * @throws EmptyStackException - when you try to pop more elements than you have in Stack
     */
    public Stack popStack(int count) throws EmptyStackException {
        if (topIdx - count < -1) {
            throw new EmptyStackException();
        } else {
            Stack result = new Stack();
            Object[] temp = Arrays.copyOfRange(this.arr, topIdx - count + 1, this.topIdx + 1);
            for (int i = 0; i < count; i++) {
                this.pop();
            }
            result.arr = temp;
            result.topIdx = temp.length - 1;
            return result;
        }
    }

    /**
     * @return count of elements at the current stack
     */
    public int count() {
        return topIdx + 1;

    }
}

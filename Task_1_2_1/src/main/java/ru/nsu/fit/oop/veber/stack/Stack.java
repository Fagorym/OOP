package ru.nsu.fit.oop.veber.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.lang.SuppressWarnings;

/**
 * This class is used to store your elements of all types
 * Main idea of this class - first in - first out
 * It means that element that you pushed the last, will be pop the first
 */
public class Stack<T> {
    private final static int STANDART_SIZE = 1000;
    private int capacity;
    private int topIdx;
    private T[] arr;

    @SuppressWarnings("unchecked")
    Stack() {
        this.arr = (T[]) new Object[STANDART_SIZE];
        this.capacity = STANDART_SIZE;
        this.topIdx = -1;
    }

    @SuppressWarnings("unchecked")
    Stack(int size) {
        this.arr = (T[]) new Object[size];
        this.capacity = size;
        this.topIdx = -1;
    }

    private void resize() {
        this.capacity *= 2;
    }

    /**
     * Function that adds element to stack
     *
     * @param elem - elem that you need to push in your stack
     *             it must match type of other Stack elements
     */
    public void push(T elem) {
        if (topIdx >= capacity - 1) {
            resize();
        }
        arr[++topIdx] = elem;

    }

    /**
     * Function that adding Stack of elements to Stack
     *
     * @param elems - elems that you need to push in your stack
     *              it must match type of other Stack elements
     * @return boolean - false, when stack is full
     * true - when elems pushed successfully
     */
    public boolean pushStack(Stack<T> elems) {
        if (topIdx >= capacity - (elems.topIdx + 1)) {
            resize();
        }
        for (T elem : elems.arr) {
            if (elem != null) push(elem);
        }
        return true;

    }

    /**
     * Function delete last element from stack and returns it
     *
     * @return Last element of the stack
     * @throws EmptyStackException - when you try to pop empty stack
     */
    public T pop() throws EmptyStackException {
        if (topIdx == -1) {
            throw new EmptyStackException();
        } else {
            T temp = arr[topIdx];
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
    public Stack<T> popStack(int count) throws EmptyStackException {
        if (topIdx - count < -1) {
            throw new EmptyStackException();
        } else {
            Stack<T> result = new Stack<>();
            T[] temp = Arrays.copyOfRange(this.arr, topIdx - count + 1, this.topIdx + 1);
            for (int i = 0; i < count; i++) {
                this.pop();
            }
            result.arr = temp;
            result.topIdx = temp.length - 1;
            return result;
        }
    }

    /**
     * Function returns count elements in stack
     *
     * @return count of elements at the current stack
     */
    public int count() {
        return topIdx + 1;

    }
}

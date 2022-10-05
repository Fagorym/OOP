package ru.nsu.fit.oop.veber.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    int count = 100;
    private Stack<Integer> stack;

    private void pushElements(Stack<Integer> stack, int count) {
        for (int i = 0; i < count; i++) {
            stack.push(i);
        }
    }

    private void popEveryElement(Stack<Integer> stack, int count) {
        for (int i = count - 1; i >= 0; i--) {
            var curElem = stack.pop();
            assertEquals(curElem, i);
        }
    }

    @BeforeEach
    public void initStack() {
        stack = new Stack<>();
    }

    @Test
    void testPopEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    void testElementsCount() {
        pushElements(stack, count);
        assertEquals(stack.count(), count);
    }

    @Test
    void testPushStack() {
        Stack<Integer> temp = new Stack<>();
        pushElements(temp, count);
        stack.pushStack(temp);
        popEveryElement(stack, count);
    }

    @Test
    void testPushStackResize() {
        Stack<Integer> littleStack = new Stack<>(1);
        Stack<Integer> temp = new Stack<>();
        temp.push(1);
        temp.push(2);
        littleStack.pushStack(temp);
    }

    @Test
    void testPushResize() {
        Stack<Integer> littleStack = new Stack<>(1);
        littleStack.push(1);
        littleStack.push(2);
    }

    @Test
    void testCreateEmptyStack() {
        assertThrowsExactly(InvalidParameterException.class, () -> new Stack<Integer>(0));
    }

    @Test
    void testPopStackEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.popStack(4));
    }

    @Test
    void testCheckPopStack() {
        Stack<Integer> temp = new Stack<>();
        pushElements(temp, count);
        stack.pushStack(temp);
        Stack<Integer> checkStack = stack.popStack(count);
        for (int i = count - 1; i >= 0; i--) {
            assertEquals(i, checkStack.pop());
        }
    }


}

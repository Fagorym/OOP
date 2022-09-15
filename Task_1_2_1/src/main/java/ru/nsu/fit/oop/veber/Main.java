package ru.nsu.fit.oop.veber;

public class Main {
    public static void main(String[] args) {

        var stack = new Stack();
        stack.push(8);
        stack.push(10);
        var stack2 = new Stack();
        stack2.push(11);
        stack2.push(13);
        stack.pushStack(stack2);
        var stack3 = stack.popStack(4);
        stack3.print();


    }
}
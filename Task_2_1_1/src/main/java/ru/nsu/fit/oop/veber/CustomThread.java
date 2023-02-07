package ru.nsu.fit.oop.veber;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomThread extends Thread {
    private final Deque<Integer> deque;
    private final Util util = new Util();
    private Boolean flag;

    public CustomThread(Integer[] arr, Boolean flag) {
        this.deque = new ArrayDeque<>();
        for (Integer integer : arr) {
            deque.addLast(integer);
        }
        this.flag = flag;
    }

    @Override
    public void run() {
        Integer x = deque.getFirst();
        if (util.isNotPrime(x)) {
            flag = true;
            this.interrupt();
        } else {
            this.start();
        }

    }
}

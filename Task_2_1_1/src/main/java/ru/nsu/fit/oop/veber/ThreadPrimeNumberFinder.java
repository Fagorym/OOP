package ru.nsu.fit.oop.veber;

import java.util.Arrays;

public class ThreadPrimeNumberFinder implements PrimeNumberFinder {

    private final Integer[] arr;
    private final Integer threadCount;

    public ThreadPrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
        this.threadCount = Thread.activeCount();
    }

    @Override
    public Boolean haveNotPrime() {
        boolean result = false;
        checkArrayCorrectness(arr);
        CustomThread[] threads = new CustomThread[threadCount];
        int tempLength = arr.length / threadCount;
        int tempRest = arr.length % threadCount;
        int tempPosition = 0;
        for (int i = 0; i < threadCount; i++) {
            Integer[] tempArray;
            if (tempRest > 0) {
                tempRest--;
                tempArray = Arrays.copyOfRange(arr, tempPosition, tempPosition + tempLength + 1);
                tempPosition += tempLength + 1;
            } else {
                tempArray = Arrays.copyOfRange(arr, tempPosition, tempPosition + tempLength);
                tempPosition += tempLength;
            }

            threads[i] = new CustomThread(tempArray, false, this);
            threads[i].start();

        }
        try {

            for (int i = 0; i < threadCount; i++) {
                threads[i].join();
                result |= threads[i].getValue();

            }
        } catch (InterruptedException ignored) {
        }
        return result;
    }
}

package ru.nsu.fit.oop.veber;

public class ThreadPrimeNumberFinder implements PrimeNumberFinder {

    private final Integer[] arr;
    private final Integer threadCount;

    public ThreadPrimeNumberFinder(Integer[] arr) {
        this.arr = arr;
        this.threadCount = Runtime.getRuntime().availableProcessors();
    }

    public ThreadPrimeNumberFinder(Integer[] arr, int threadCount) {
        this.arr = arr;
        if (threadCount > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException("You cannot use more threads than you have.");
        }
        this.threadCount = threadCount;
    }

    @Override
    public Boolean haveNotPrime() {
        boolean result = false;
        checkArrayCorrectness(arr);
        CustomThread[] threads = new CustomThread[threadCount];
        int tempLength = arr.length / threadCount;
        int tempRest = arr.length % threadCount;
        int startIndex = 0;
        int endIndex = tempLength + tempRest;
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new CustomThread(arr, startIndex, endIndex, false, this);
            threads[i].start();
            startIndex = endIndex;
            endIndex += tempLength;

        }
        try {
            for (int i = 0; i < threadCount; i++) {
                if (result) {
                    threads[i].interrupt();
                } else {
                    threads[i].join();
                    result = threads[i].getValue();
                }
            }
        } catch (InterruptedException ignored) {
        }
        return result;
    }
}

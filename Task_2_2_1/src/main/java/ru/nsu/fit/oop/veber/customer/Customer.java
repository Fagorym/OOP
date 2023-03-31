package ru.nsu.fit.oop.veber.customer;

public interface Customer extends Runnable {
    void stopOrdering();

    void resumeOrdering();
}

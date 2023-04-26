package ru.nsu.fit.oop.veber.utils;

public class Primitive<T> {
    private T representation;

    public Primitive(T representation) {
        this.representation = representation;

    }

    public T getRepresentation() {
        return representation;
    }

    public void setRepresentation(T representation) {
        this.representation = representation;
    }
}

package ru.nsu.fit.oop.veber;


import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {
    T elem;
    Set<Edge<T>> startEdges;
    Set<Edge<T>> endEdges;

    public Vertex(T elem) {
        this.elem = elem;
        this.startEdges = new HashSet<>();
        this.endEdges = new HashSet<>();
    }

    @Override
    public String toString() {
        return elem.toString();
    }

}

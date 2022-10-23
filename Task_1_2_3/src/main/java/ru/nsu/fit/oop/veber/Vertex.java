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

    public void addStartEdge(Edge<T> edge) {
        this.startEdges.add(edge);
    }

    public void addEndEdge(Edge<T> edge) {
        this.endEdges.add(edge);
    }

}

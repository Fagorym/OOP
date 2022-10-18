package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    T elem;
    List<Edge<T>> startEdges;
    List<Edge<T>> endEdges;
    public Vertex (T elem){
        this.elem = elem;
        this.startEdges = new ArrayList<>();
        this.endEdges = new ArrayList<>();
    }

    public void addStartEdge(Edge<T> edge){
        this.startEdges.add(edge);
    }

    public void addEndEdge(Edge<T> edge){
        this.endEdges.add(edge);
    }

}

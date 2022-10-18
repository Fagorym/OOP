package ru.nsu.fit.oop.veber;

public class Edge<T> {
    int weight;
    Vertex<T> start;
    Vertex<T> end;

    public Edge(int weight, Vertex<T> start, Vertex<T> end){
        this.weight = weight;
        this.start = start;
        this.end = end;
    }
}

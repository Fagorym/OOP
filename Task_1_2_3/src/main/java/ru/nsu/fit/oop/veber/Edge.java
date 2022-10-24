package ru.nsu.fit.oop.veber;

public class Edge<T> {
    int weight;

    String name;
    Vertex<T> start;
    Vertex<T> end;

    public Edge(String name, int weight, Vertex<T> start, Vertex<T> end){
        this.weight = weight;
        this.start = start;
        start.startEdges.add(this);
        this.end = end;
        end.endEdges.add(this);
        this.name = name;
    }
}

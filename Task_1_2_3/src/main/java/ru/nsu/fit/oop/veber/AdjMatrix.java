package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjMatrix<T> implements Graph<T> {
    ArrayList<Vertex<T>> vertexes;
    HashMap<Vertex<T>, HashMap<Vertex<T>, ArrayList<Edge<T>>>> matrix;
    @Override
    public boolean addVertex(Vertex<T> vertex) {
        this.matrix.put(vertex, new HashMap<>());
        vertexes.add(vertex);
        return true;
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
       var smth =  matrix.get(vertex);

    }

    @Override
    public void addEdge(Edge<T> edge) {
        var column = this.matrix.get(edge.start);
        var cell = column.get(edge.end);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {

    }

    @Override
    public List<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        return null;
    }

    @Override
    public T getVertexElement(Vertex<T> vertex) {
        return vertex.elem;
    }

    @Override
    public void setVertexElement(Vertex<T> vertex, T newElem) {
        vertex.elem = newElem;
    }

    @Override
    public int getVertexDegree(Vertex<T> vertex) {
        return 0;
    }

    @Override
    public int getVertexNumber(Vertex<T> vertex) {
        return 0;
    }

    @Override
    public int getEdgesNumber(Vertex<T> vertex) {
        return 0;
    }
}

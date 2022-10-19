package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.List;

public class AdjMatrix<T> implements Graph<T> {
    HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> matrix;

    @Override
    public void addVertex(Vertex<T> vertex) {
        
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {

    }

    @Override
    public void addEdge(Edge<T> edge) {

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
        return null;
    }

    @Override
    public void setVertexElement(Vertex<T> vertex, T newElem) {

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

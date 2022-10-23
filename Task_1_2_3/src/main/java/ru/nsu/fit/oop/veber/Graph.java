package ru.nsu.fit.oop.veber;

import java.util.List;
import java.util.Set;

public interface Graph<T> {
    public boolean addVertex(Vertex<T> vertex);
    public void deleteVertex(Vertex<T> vertex);
    public void addEdge(Edge<T> edge);
    public void deleteEdge(Edge<T> edge);
    public Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex);
    public T getVertexElement(Vertex<T> vertex);
    public void setVertexElement(Vertex<T> vertex, T newElem);

    public int getVertexDegree(Vertex<T> vertex);
    public int getVertexNumber(Vertex<T> vertex);
    public int getEdgesNumber(Vertex<T> vertex);
}

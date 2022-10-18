package ru.nsu.fit.oop.veber;

import java.util.List;

public interface Graph<T> {
    public void addVertex(Vertex<T> vertex);
    public void deleteVertex(Vertex<T> vertex);
    public void addEdge(Edge<T> edge);
    public void deleteEdge(Edge<T> edge);
    public List<Vertex<T>> getAdjVertexes(Vertex<T> vertex);
    public T getVertexElement(Vertex<T> vertex);
    public void setVertexElement(Vertex<T> vertex, T newElem);

}

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

    /**
     * Method returns degree of current vertex.
     *
     * @param vertex - from what vertex we count degree
     * @return degree of the vertex (count of edges those are connected to it)
     */
    public int getVertexDegree(Vertex<T> vertex);

    /**
     * Method returns number of vertexes in graph.
     *
     * @return number of vertexes
     */
    public int getVertexNumber();

    /**
     * Method returns number of edges from current vertex.
     *
     * @return count of edges
     */
    public int getEdgesNumber();
}

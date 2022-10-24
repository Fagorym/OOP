package ru.nsu.fit.oop.veber;

import java.util.Set;

/**
 * This interface contains all basic methods to work with directed simple graphs.
 *
 * @param <T> elem that can be in vertex (vertexes can be associated with any type)
 */
public interface Graph<T> {
    /**
     * Adds vertex to graph.
     *
     * @param vertex - vertex that we add
     * @return true - if vertex was added, false - if vertex was not added.
     */
    boolean addVertex(Vertex<T> vertex);

    /**
     * Deletes vertex from graph.
     *
     * @param vertex - vertex that we delete
     */
    void deleteVertex(Vertex<T> vertex);

    /**
     * Adds edge to graph.
     *
     * @param edge - edge that we add
     */
    void addEdge(Edge<T> edge);

    /**
     * Deletes edge from graph.
     *
     * @param edge - edge that we delete
     */
    void deleteEdge(Edge<T> edge);

    /**
     * Get the set of adjacency vertexes.
     *
     * @param vertex - from what vertex we get adjacency vertexes
     * @return set of the vertexes
     */
    Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex);

    /**
     * Get the element of the current vertex.
     *
     * @param vertex from what vertex we get element
     * @return element of the vertex
     */
    T getVertexElement(Vertex<T> vertex);

    /**
     * Set the element of the current vertex.
     *
     * @param vertex  - to what vertex we set element
     * @param newElem - what element will be actual element
     */
    void setVertexElement(Vertex<T> vertex, T newElem);

    /**
     * Method returns degree of current vertex.
     *
     * @param vertex - from what vertex we count degree
     * @return degree of the vertex (count of edges those are connected to it)
     */
    int getVertexDegree(Vertex<T> vertex);

    /**
     * Method returns number of vertexes in graph.
     *
     * @return number of vertexes
     */
    int getVertexNumber();

    /**
     * Method returns number of edges from current vertex.
     *
     * @return count of edges
     */
    int getEdgesNumber();
}

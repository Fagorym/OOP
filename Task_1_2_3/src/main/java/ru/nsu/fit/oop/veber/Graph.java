package ru.nsu.fit.oop.veber;

import java.util.Set;

/**
 * This interface contains all basic methods to work with directed simple graphs.
 *
 * @param <T> elem that can be in vertex (vertexes can be associated with any type)
 */
public interface Graph<V, E> {
    /**
     * Adds vertex to graph.
     *
     * @param vertex - vertex that we add
     * @return true - if vertex was added, false - if vertex was not added.
     */
    boolean addVertex(Vertex<V> vertex);

    /**
     * Deletes vertex from graph.
     *
     * @param vertex - vertex that we delete
     */
    void deleteVertex(Vertex<V> vertex);

    /**
     * Adds edge to graph.
     *
     * @param edge - edge that we add
     */
    void addEdge(Edge<V, E> edge);

    /**
     * Deletes edge from graph.
     *
     * @param edge - edge that we delete
     */
    void deleteEdge(Edge<V, E> edge);

    /**
     * Get the set of adjacency vertexes.
     *
     * @param vertex - from what vertex we get adjacency vertexes
     * @return set of the vertexes
     */
    Set<Vertex<V>> getAdjVertexes(Vertex<V> vertex);

    /**
     * Get the set of vertexes of the graph.
     *
     * @return set of vertexes
     */
    Set<Vertex<V>> getVertexes();

    /**
     * Get the set of edges of graph.
     *
     * @return set edges of the graph
     */
    Set<Edge<V, E>> getEdges();

    /**
     * Get the element of the current vertex.
     *
     * @param vertex from what vertex we get element
     * @return element of the vertex
     */

    V getVertexElement(Vertex<V> vertex);

    /**
     * Set the element of the current vertex.
     *
     * @param vertex  - to what vertex we set element
     * @param newElem - what element will be actual element
     */
    void setVertexElement(Vertex<V> vertex, V newElem);

    /**
     * Method returns degree of current vertex.
     *
     * @param vertex - from what vertex we count degree
     * @return degree of the vertex (count of edges those are connected to it)
     */
    int getVertexDegree(Vertex<V> vertex);

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

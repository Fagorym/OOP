package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.Set;

/**
 * This interface contains all basic methods to work with directed simple graphs.
 *
 * @param <V> elem that can be in vertex (vertexes can be associated with any type)
 * @param <E> elem that can be in edge (edges can be associated with any type)
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

    /**
     * Method that takes one source vertex and counts the shortest paths to all other vertexes.
     * If there is no path - it will be maxInteger value.
     *
     * @param sourceVertex - from which vertex we count the shortest paths
     * @return hashMap from Vertex to the shortest path to this vertex
     */
    public HashMap<Vertex<V>, Integer> dijkstra(Vertex<V> sourceVertex);

    /**
     * Method that checks existence of the vertex in the graph.
     *
     * @param elem - elem of the vertex
     * @return true - exist, false - no
     */
    boolean containsVertex(V elem);

    /**
     * Method that checks existence of the edge in the graph.
     *
     * @param elem - elem of the edge
     * @return true - exist, false - no
     */
    boolean containsEdge(E elem);

    /**
     * Method that returns vertex with provided element.
     * @param elem - with this element we're exploring vertex
     * @return vertex with exploring elem
     */
    public Vertex<V> getVertex(V elem);
}

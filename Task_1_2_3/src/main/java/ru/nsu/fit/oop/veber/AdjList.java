package ru.nsu.fit.oop.veber;

import java.util.*;

/**
 * That class implements graph interface and all of its methods.
 * Main idea - that for every element we store set of adjacency vertexes in hashmap.
 * This class must not be used for multi graphs (you must not put multiple edges and loops).
 *
 * @param <T> elem that can be in vertex (vertexes can be associated with any type)
 */
public class AdjList<T> implements Graph<T> {

    private final HashMap<Vertex<T>, Set<Vertex<T>>> rows;
    private final Set<Edge<T>> edges;
    private final Set<Vertex<T>> vertexes;

    /**
     * Default constructor, that creates empty sets and empty hashmap.
     */
    public AdjList() {
        this.rows = new HashMap<>();
        this.edges = new HashSet<>();
        this.vertexes = new HashSet<>();
    }


    /**
     * Method creates new set and adds all incident vertexes to this set.
     * After it, method creates new row to associate vertex with this set.
     * This method does not add vertex, that is already in graph.
     * If vertexes connected to vertex, that are not in graph, we also add those vertexes.
     *
     * @param vertex - vertex that we add to graph
     * @return true - vertex was added, false - vertex was not added
     */
    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (!vertexes.contains(vertex)) {
            Set<Vertex<T>> adjVertexes = new HashSet<>();
            vertexes.add(vertex);
            for (Edge<T> edge : vertex.startEdges) {
                adjVertexes.add(edge.end);
                edges.add(edge);
                if (!vertexes.contains(edge.end)) {
                    addVertex(edge.end);
                }
            }
            for (Edge<T> edge : vertex.endEdges) {
                adjVertexes.add(edge.start);
                edges.add(edge);
                if (!vertexes.contains(edge.start)) {
                    addVertex(edge.start);
                }
            }
            rows.put(vertex, adjVertexes);
            return true;
        }
        return false;
    }

    /**
     * This method deletes current vertex from graph and all edges, that was connected to this vertex.
     *
     * @param vertex - vertex that we delete from graph
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        rows.remove(vertex);
        vertexes.remove(vertex);
        for (Edge<T> edge : vertex.startEdges) {
            edges.remove(edge);
            rows.get(edge.end).remove(edge.start);
        }
        for (Edge<T> edge : vertex.endEdges) {
            edges.remove(edge);
            rows.get(edge.start).remove(edge.end);
        }

    }


    /**
     * This method adds new edge to graph.
     * Change adjacency list, added start vertex to list of end vertex and the opposite.
     *
     * @param edge - edge that we add to graph
     */
    @Override
    public void addEdge(Edge<T> edge) {
        this.edges.add(edge);
        rows.get(edge.start).add(edge.end);
        this.rows.get(edge.end).add(edge.start);

    }

    /**
     * This method deleted the edge from graph.
     * We change adjacency list and current state of the vertex.
     *
     * @param edge - edge that we deleted from graph
     */

    @Override
    public void deleteEdge(Edge<T> edge) {
        this.edges.remove(edge);
        this.rows.get(edge.start).remove(edge.end);
        this.rows.get(edge.end).remove(edge.start);
        edge.start.startEdges.remove(edge);
        edge.end.endEdges.remove(edge);


    }

    /**
     * Method get the row of the current vertex and returns the set of adjacency vertexes associated with it.
     *
     * @param vertex - from what vertex we need to get adjacency list.
     * @return set of adjacency vertexes
     */

    @Override
    public Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        return this.rows.get(vertex);
    }

    /**
     * Get element of the current vertex (name of the vertex).
     *
     * @param vertex - from what vertex we get element
     * @return element of the current vertex
     */
    @Override
    public T getVertexElement(Vertex<T> vertex) {
        return vertex.elem;
    }

    /**
     * Set the element of the current vertex.
     *
     * @param vertex  - to what vertex we set element
     * @param newElem - what element will be actual element
     */
    @Override
    public void setVertexElement(Vertex<T> vertex, T newElem) {
        vertex.elem = newElem;
    }

    /**
     * Method returns degree of current vertex.
     *
     * @param vertex - from what vertex we count degree
     * @return degree of the vertex (count of edges those are connected to it)
     */
    @Override
    public int getVertexDegree(Vertex<T> vertex) {
        return this.rows.get(vertex).size();
    }

    /**
     * Method returns number of vertexes in graph.
     *
     * @return number of vertexes.
     */
    @Override
    public int getVertexNumber() {
        return vertexes.size();
    }

    /**
     * Method returns number of edges in graph.
     *
     * @return count of edges
     */
    @Override
    public int getEdgesNumber() {
        return edges.size();
    }
}

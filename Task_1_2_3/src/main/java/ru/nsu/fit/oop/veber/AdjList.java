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

    private HashMap<Vertex<T>, Set<Vertex<T>>> rows;
    private Set<Edge<T>> edges;
    private Set<Vertex<T>> vertexes;

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
                this.edges.add(edge);
                if (!vertexes.contains(edge.end)) {
                    addVertex(edge.end);
                }
            }
            for (Edge<T> edge : vertex.endEdges) {
                adjVertexes.add(edge.start);
                this.edges.add(edge);
                if (!vertexes.contains(edge.start)) {
                    addVertex(edge.start);
                }
            }
            this.rows.put(vertex, adjVertexes);
            return true;
        }
        return false;
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        this.rows.remove(vertex);
        this.vertexes.remove(vertex);
        for (Edge<T> edge : vertex.startEdges) {
            this.edges.remove(edge);
            var row = this.rows.get(edge.end);
            row.remove(edge.start);
            this.rows.put(edge.end, row);
        }
        for (Edge<T> edge : vertex.endEdges) {
            this.edges.remove(edge);
            var row = this.rows.get(edge.start);
            row.remove(edge.end);
            this.rows.put(edge.start, row);
        }

    }

    @Override
    public void addEdge(Edge<T> edge) {
        this.edges.add(edge);
        edge.start.startEdges.add(edge);
        edge.end.endEdges.add(edge);
        var startRow = this.rows.get(edge.start);
        startRow.add(edge.end);
        this.rows.put(edge.start, startRow);
        var endRow = this.rows.get(edge.end);
        endRow.add(edge.start);
        this.rows.put(edge.end, endRow);

    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        this.edges.remove(edge);
        var startRow = this.rows.get(edge.start);
        startRow.remove(edge.end);
        this.rows.put(edge.start, startRow);
        var endRow = this.rows.get(edge.end);
        endRow.remove(edge.start);
        this.rows.put(edge.end, endRow);


    }

    @Override
    public Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        return this.rows.get(vertex);
    }

    @Override
    public T getVertexElement(Vertex<T> vertex) {
        return vertex.elem;
    }

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

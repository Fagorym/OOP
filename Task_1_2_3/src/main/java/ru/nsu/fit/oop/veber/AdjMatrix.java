package ru.nsu.fit.oop.veber;

import java.util.*;

/**
 * 
 * @param <T>
 */
public class AdjMatrix<T> implements Graph<T> {

    Set<Vertex<T>> vertexes;
    Set<Edge<T>> edges;
    HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> matrix;


    public AdjMatrix() {
        this.vertexes = new HashSet<>();
        this.edges = new HashSet<>();
        this.matrix = new HashMap<>();
    }

    /**
     * Method iterate all vertexes and putting cell with value = 0.
     * After that we iterate edges of added vertex and put cell with value = 1 to adjacency vertexes.
     * This method does not add vertex, that is already in graph.
     * If vertexes connected to vertex, that are not in graph, we also add those vertexes.
     *
     * @param vertex - vertex that we add to graph
     * @return true - vertex was added, false - vertex was not added
     */
    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (!vertexes.contains(vertex)) {
            matrix.put(vertex, new HashMap<>());
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).put(vertex, 0);
                matrix.get(vertex).put(customVertex, 0);
            }
            this.vertexes.add(vertex);
            for (Edge<T> edge : vertex.startEdges) {
                this.matrix.get(vertex).put(edge.end, 1);
                this.matrix.get(edge.end).put(vertex, 1);
                this.edges.add(edge);
                if (!vertexes.contains(edge.end)) {
                    addVertex(edge.end);
                }
            }
            for (Edge<T> edge : vertex.endEdges) {
                this.matrix.get(vertex).put(edge.start, 1);
                this.matrix.get(edge.start).put(vertex, 1);
                this.edges.add(edge);
                if (!vertexes.contains(edge.start)) {
                    addVertex(edge.start);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Deletes vertex and all edges, those are connected to this vertex.
     * If vertex does not exist in graph, method does not do anything.
     *
     * @param vertex - vertex that we delete from graph
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (vertexes.contains(vertex)) {
            vertexes.remove(vertex);
            matrix.remove(vertex);
            edges.removeIf(edge -> edge.start == vertex || edge.end == vertex);
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).remove(vertex);
            }
        }

    }

    /**
     * Method that adds some edge to graph.
     *
     * @param edge - edge that we add in graph
     * @throws IllegalArgumentException - if you are trying to add edge with vertexes, that is not already in graph
     */
    @Override
    public void addEdge(Edge<T> edge) {
        if (!vertexes.contains(edge.start) || !vertexes.contains(edge.end)) {
            throw new IllegalArgumentException("You are trying to add edge with vertexes, that is not in the graph");
        }
        if (!edges.contains(edge)) {
            edges.add(edge);
            matrix.get(edge.start).put(edge.end, 1);
            matrix.get(edge.end).put(edge.start, 1);
        }

    }

    /**
     * Method deletes edge from graph.
     *
     * @param edge - edge that we delete
     * @throws IllegalArgumentException - if you are trying to delete edge with vertexes, that is not already in graph
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        if (!vertexes.contains(edge.start) || !vertexes.contains(edge.end)) {
            throw new IllegalArgumentException("You are trying to delete edge with vertexes, that is not in the graph");
        }
        edges.remove(edge);
        matrix.get(edge.start).put(edge.end, 0);
        matrix.get(edge.end).put(edge.start, 0);

    }

    /**
     * Method that iterate all vertexes and checks, if they are adjacency to current.
     * All adjacency vertexes adds to resultSet.
     *
     * @param vertex - from what vertex we get adjacency vertexes
     * @return set of adjacency vertexes
     */
    @Override
    public Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        Set<Vertex<T>> adjVertexes = new HashSet<>();
        var matrix = this.matrix.get(vertex);
        for (Vertex<T> customVert : this.vertexes) {
            if (matrix.get(customVert) > 0) {
                adjVertexes.add(customVert);
            }
        }
        return adjVertexes;
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
     * Methods that change vertex element.
     *
     * @param vertex  - to what vertex we change element
     * @param newElem - this element will be new element of the vertex
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
        return getAdjVertexes(vertex).size();
    }


    /**
     * Method returns number of vertexes in graph.
     *
     * @return number of vertexes.
     */
    @Override
    public int getVertexNumber() {
        return edges.size();
    }

    /**
     * Method returns number of edges in graph.
     *
     * @return count of edges
     */
    @Override
    public int getEdgesNumber() {
        return vertexes.size();
    }
}

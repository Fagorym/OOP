package ru.nsu.fit.oop.veber;

import java.util.*;

public class AdjList<T> implements Graph<T> {

    private HashMap<Vertex<T>, Set<Vertex<T>>> rows;
    private Set<Edge<T>> edges;
    private Set<Vertex<T>> vertexes;

    public AdjList() {
        this.rows = new HashMap<>();
        this.edges = new HashSet<>();
        this.vertexes = new HashSet<>();
    }


    @Override
    public boolean addVertex(Vertex<T> vertex) {
        Set<Vertex<T>> adjVertexes = new HashSet<>();
        if (!vertexes.contains(vertex)) {
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
    public List<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        return this.rows.get(vertex).stream().toList();
    }

    @Override
    public T getVertexElement(Vertex<T> vertex) {
        return vertex.elem;
    }

    @Override
    public void setVertexElement(Vertex<T> vertex, T newElem) {
        vertex.elem = newElem;
    }

    @Override
    public int getVertexDegree(Vertex<T> vertex) {
        return this.rows.get(vertex).size();
    }

    @Override
    public int getVertexNumber(Vertex<T> vertex) {
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber(Vertex<T> vertex) {
        return edges.size();
    }
}

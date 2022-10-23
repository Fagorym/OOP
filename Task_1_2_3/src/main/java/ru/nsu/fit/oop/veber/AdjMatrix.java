package ru.nsu.fit.oop.veber;

import java.util.*;

public class AdjMatrix<T> implements Graph<T> {

    Set<Vertex<T>> vertexes;
    Set<Edge<T>> edges;
    HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> matrix;

    public AdjMatrix() {
        this.vertexes = new HashSet<>();
        this.edges = new HashSet<>();
        this.matrix = new HashMap<>();
    }

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (!vertexes.contains(vertex)) {
            this.vertexes.add(vertex);
            for (Edge<T> edge : vertex.startEdges) {
                this.matrix.get(vertex).put(edge.end, 1);
                this.matrix.get(edge.end).put(vertex, 1);
                this.edges.add(edge);
            }
            for (Edge<T> edge : vertex.endEdges) {
                this.matrix.get(vertex).put(edge.start, 1);
                this.matrix.get(edge.start).put(vertex, 1);
                this.edges.add(edge);
            }
            return true;
        }
        return false;
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (vertexes.contains(vertex)) {
            this.vertexes.remove(vertex);
            for (Edge<T> edge : vertex.startEdges) {
                this.edges.remove(edge);
                this.matrix.remove(vertex);
                this.matrix.get(edge.end).remove(edge.start);

            }
            for (Edge<T> edge : vertex.endEdges) {
                this.edges.remove(edge);
                this.matrix.remove(vertex);
                this.matrix.get(edge.start).remove(vertex);
            }
        }

    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
            matrix.get(edge.start).put(edge.end, 1);
            matrix.get(edge.end).put(edge.start, 1);
        }

    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        matrix.get(edge.start).put(edge.end, 0);
        matrix.get(edge.end).put(edge.start, 0);

    }

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
        return getAdjVertexes(vertex).size();
    }

    @Override
    public int getVertexNumber(Vertex<T> vertex) {
        return edges.size();
    }

    @Override
    public int getEdgesNumber(Vertex<T> vertex) {
        return vertexes.size();
    }
}

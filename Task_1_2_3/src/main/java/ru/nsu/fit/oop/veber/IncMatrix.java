package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncMatrix<T> implements Graph<T> {
    Set<Vertex<T>> vertexes;
    Set<Edge<T>> edges;
    HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> matrix;

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (vertexes.contains(vertex)) {
            for (Edge<T> edge : vertex.startEdges) {
                matrix.get(vertex).put(edge, 1);
                matrix.get(edge.end).put(edge, 1);
                edges.add(edge);
            }
            for (Edge<T> edge : vertex.endEdges) {
                matrix.get(vertex).put(edge, 1);
                matrix.get(edge.start).put(edge, 1);
                edges.add(edge);
            }
            return true;
        }
        return false;
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        vertexes.remove(vertex);
        matrix.remove(vertex);
        for (Edge<T> edge: vertex.startEdges){
            edges.remove(edge);
            for (Vertex<T> customVertex : vertexes){
                matrix.get(customVertex).remove(edge);
            }
        }
        for (Edge<T> edge: vertex.endEdges){
            edges.remove(edge);
            for (Vertex<T> customVertex : vertexes){
                matrix.get(customVertex).remove(edge);
            }
        }

    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!edges.contains(edge)){
            edges.add(edge);
            matrix.get(edge.start).put(edge,1);
            matrix.get(edge.end).put(edge,1);
        }

    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        for (Vertex<T> vertex: vertexes){
            matrix.get(vertex).remove(edge);
            vertex.startEdges.remove(edge);
            vertex.endEdges.remove(edge);
        }


    }

    @Override
    public List<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        Set<Vertex<T>> vertexSet = new HashSet<>();
        for (Edge<T> edge: vertex.endEdges){
            vertexSet.add(edge.start);
        }
        for (Edge<T> edge: vertex.startEdges){
            vertexSet.add(edge.end);
        }
        return vertexSet.stream().toList();
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
        return vertexes.size();
    }

    @Override
    public int getEdgesNumber(Vertex<T> vertex) {
        return edges.size();
    }
}

package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * That class implements graph interface and all of its methods.
 * Main idea - that for every vertex we store hashmap with key = *current edge*.
 * Value of the second hashmap - weight of the current edge (if exist) and 0, if there is no edge.
 * This class must not be used for multi graphs (you must not put multiple edges and loops).
 *
 * @param <T> elem that can be in vertex (vertexes can be associated with any type)
 */
public class IncMatrix<T> implements Graph<T> {
    private final Set<Vertex<T>> vertexes;
    private final Set<Edge<T>> edges;
    private final HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> matrix;

    public IncMatrix() {
        vertexes = new HashSet<>();
        edges = new HashSet<>();
        matrix = new HashMap<>();
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

    /**
     * Method deletes vertex from all vertexes of the graph, remove row from matrix,
     * removes all edges connected to this graph.
     * After that, we iterate all vertexes and remove their cells of this edge.
     *
     * @param vertex - vertex that we delete
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        vertexes.remove(vertex);
        matrix.remove(vertex);
        for (Edge<T> edge : vertex.startEdges) {
            edges.remove(edge);
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).remove(edge);
            }
        }
        for (Edge<T> edge : vertex.endEdges) {
            edges.remove(edge);
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).remove(edge);
            }
        }

    }

    /**
     * If edge already in graph - we do not add it.
     * If edge not in graph, we add to weight to current vertexes.
     * For other vertexes we add weight = 0 (because they are not incident to it).
     *
     * @param edge - edge that we add
     */
    @Override
    public void addEdge(Edge<T> edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
            matrix.get(edge.start).put(edge, edge.weight);
            matrix.get(edge.end).put(edge, edge.weight);
        }

    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        for (Vertex<T> vertex : vertexes) {
            matrix.get(vertex).remove(edge);
            vertex.startEdges.remove(edge);
            vertex.endEdges.remove(edge);
        }


    }

    /**
     * We iterate all edges of the current vertex and add unique adjacency vertex to the set.
     *
     * @param vertex - from what vertex we get adjacency vertexes
     * @return set of the adjacency vertexes
     */
    @Override
    public Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        Set<Vertex<T>> vertexSet = new HashSet<>();
        for (Edge<T> edge : vertex.endEdges) {
            vertexSet.add(edge.start);
        }
        for (Edge<T> edge : vertex.startEdges) {
            vertexSet.add(edge.end);
        }
        return vertexSet;
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

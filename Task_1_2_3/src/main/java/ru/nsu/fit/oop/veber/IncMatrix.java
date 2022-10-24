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

    /**
     * Default constructor of the incident matrix.
     */
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
            initVertex(vertex);
            for (Edge<T> edge : vertex.getStartEdges()) {
                initEdge(edge);
                addVertex(edge.getEnd());
            }
            for (Edge<T> edge : vertex.getEndEdges()) {
                initEdge(edge);
                addVertex(edge.getStart());
            }
            return true;
        }
        return false;
    }

    private void initEdge(Edge<T> edge) {
        edges.add(edge);
        for (Vertex<T> vertex : vertexes) {
            if (edge.getStart() == vertex || edge.getEnd() == vertex) {
                matrix.get(vertex).put(edge, 1);
            } else {
                matrix.get(vertex).put(edge, 0);
            }
        }
    }

    private void initVertex(Vertex<T> vertex) {
        this.vertexes.add(vertex);
        matrix.put(vertex, new HashMap<>());
        for (Edge<T> edge : edges) {
            matrix.get(vertex).put(edge, 0);
        }

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
        for (Edge<T> edge : vertex.getStartEdges()) {
            edges.remove(edge);
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).remove(edge);
            }
        }
        for (Edge<T> edge : vertex.getEndEdges()) {
            edges.remove(edge);
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).remove(edge);
            }
        }

    }

    /**
     * If edge connected to vertex, that is not already in graph, we also add this vertex.
     * If edge already in graph - we do not add it.
     * If edge not in graph, we add to weight to current vertexes.
     * For other vertexes we add weight = 0 (because they are not incident to it).
     *
     * @param edge - edge that we add
     */
    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        addVertex(edge.getStart());
        addVertex(edge.getEnd());
    }

    /**
     * Deleted current edge from the incident matrix.
     *
     * @param edge - edge that we delete
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        for (Vertex<T> vertex : vertexes) {
            matrix.get(vertex).remove(edge);
            vertex.removeEdge(edge);
        }


    }

    /**
     * Getter for the edges of the graph.
     *
     * @return set of the edges of the graph
     */
    @Override
    public Set<Edge<T>> getEdges() {
        return edges;
    }

    /**
     * Getter for the vertexes of the graph.
     *
     * @return set of the vertexes of the graph
     */
    @Override
    public Set<Vertex<T>> getVertexes() {
        return vertexes;
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
        for (Edge<T> edge : vertex.getEndEdges()) {
            vertexSet.add(edge.getStart());
        }
        for (Edge<T> edge : vertex.getStartEdges()) {
            vertexSet.add(edge.getEnd());
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
        return vertex.getElem();
    }

    /**
     * Methods that change vertex element.
     *
     * @param vertex  - to what vertex we change element
     * @param newElem - this element will be new element of the vertex
     */
    @Override
    public void setVertexElement(Vertex<T> vertex, T newElem) {
        vertex.setElem(newElem);
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

    /**
     * Creates human-reading string of the incident matrix.
     *
     * @return string representation of the matrix
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(" |");
        for (Vertex<T> vertex : vertexes) {
            resultString.append(vertex.getElem());
            resultString.append("|");
        }
        resultString.append("\n");
        for (Edge<T> edge : edges) {
            resultString.append(edge.getName());
            resultString.append("|");
            for (Vertex<T> vertex : vertexes) {
                resultString.append(matrix.get(vertex).get(edge));
                resultString.append("|");
            }
            resultString.append("\n");
        }

        return resultString.toString();
    }
}

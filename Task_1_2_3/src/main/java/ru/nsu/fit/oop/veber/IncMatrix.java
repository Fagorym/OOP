package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * That class implements graph interface and all of its methods.
 * Main idea - that for every vertex we store hashmap with key = *current edge*.
 * Value of the second hashmap - weight of the current edge (if exist) and 0, if there is no edge.
 * This class must not be used for multi graphs (you must not put multiple edges and loops).
 *
 * @param <V> elem that can be in vertex (vertexes can be associated with any type)
 * @param <E> elem that can be in edge (edges can be associated with any type)
 */
public class IncMatrix<V, E> implements Graph<V, E> {
    private final Set<Vertex<V>> vertexes;
    private final Set<Edge<V, E>> edges;
    private final HashMap<Vertex<V>, HashMap<Edge<V, E>, Integer>> matrix;

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
     *
     * @param vertex - vertex that we add to graph
     * @return true - vertex was added, false - vertex was not added
     */
    @Override
    public boolean addVertex(Vertex<V> vertex) {
        if (!vertexes.contains(vertex)) {
            initVertex(vertex);
            return true;
        }
        return false;
    }

    private void initEdge(Edge<V, E> edge) {
        edges.add(edge);
        for (Vertex<V> vertex : vertexes) {
            if (edge.getStart() == vertex || edge.getEnd() == vertex) {
                matrix.get(vertex).put(edge, 1);
            } else {
                matrix.get(vertex).put(edge, 0);
            }
        }
    }

    private void initVertex(Vertex<V> vertex) {
        this.vertexes.add(vertex);
        matrix.put(vertex, new HashMap<>());
        for (Edge<V, E> edge : edges) {
            matrix.get(vertex).put(edge, 0);
        }

    }

    /**
     * Method deletes vertex from list of all vertexes of the graph, remove row from matrix,
     * removes all edges connected to this graph.
     * After that, we iterate all vertexes and remove their cells of this edge.
     *
     * @param vertex - vertex that we delete
     */
    @Override
    public void deleteVertex(Vertex<V> vertex) {
        vertexes.remove(vertex);
        matrix.remove(vertex);
        ArrayList<Edge<V, E>> toDelete = new ArrayList<>();
        for (Edge<V, E> edge : edges) {
            if (edge.getStart() == vertex || edge.getEnd() == vertex) {
                toDelete.add(edge);
                for (Vertex<V> customVertex : vertexes) {
                    matrix.get(customVertex).remove(edge);
                }
            }
        }
        toDelete.forEach(edges::remove);

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
    public void addEdge(Edge<V, E> edge) {
        if (!vertexes.contains(edge.getEnd()) || !vertexes.contains(edge.getStart())) {
            throw new IllegalArgumentException("You try to add edge with vertexes, that does not in the graph. Firstly, you need to add those vertexes.");
        }
        initEdge(edge);
    }

    /**
     * Deleted current edge from the incident matrix.
     *
     * @param edge - edge that we delete
     */
    @Override
    public void deleteEdge(Edge<V, E> edge) {
        edges.remove(edge);
        for (Vertex<V> vertex : vertexes) {
            matrix.get(vertex).remove(edge);
        }


    }

    /**
     * Getter for the edges of the graph.
     *
     * @return set of the edges of the graph
     */
    @Override
    public Set<Edge<V, E>> getEdges() {
        return edges;
    }

    /**
     * Getter for the vertexes of the graph.
     *
     * @return set of the vertexes of the graph
     */
    @Override
    public Set<Vertex<V>> getVertexes() {
        return vertexes;
    }

    /**
     * We iterate all edges of the current vertex and add unique adjacency vertex to the set.
     *
     * @param vertex - from what vertex we get adjacency vertexes
     * @return set of the adjacency vertexes
     */
    @Override
    public Set<Vertex<V>> getAdjVertexes(Vertex<V> vertex) {
        Set<Vertex<V>> vertexSet = new HashSet<>();
        for (Edge<V, E> edge : edges) {
            if (edge.getStart() == vertex) {
                vertexSet.add(edge.getEnd());
            } else if (edge.getEnd() == vertex) {
                vertexSet.add(edge.getStart());
            }
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
    public V getVertexElement(Vertex<V> vertex) {
        return vertex.getElem();
    }

    /**
     * Methods that change vertex element.
     *
     * @param vertex  - to what vertex we change element
     * @param newElem - this element will be new element of the vertex
     */
    @Override
    public void setVertexElement(Vertex<V> vertex, V newElem) {
        vertex.setElem(newElem);
    }

    /**
     * Method returns degree of current vertex.
     *
     * @param vertex - from what vertex we count degree
     * @return degree of the vertex (count of edges those are connected to it)
     */
    @Override
    public int getVertexDegree(Vertex<V> vertex) {
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
        for (Vertex<V> vertex : vertexes) {
            resultString.append(vertex.getElem());
            resultString.append("|");
        }
        resultString.append("\n");
        for (Edge<V, E> edge : edges) {
            resultString.append(edge.getName());
            resultString.append("|");
            for (Vertex<V> vertex : vertexes) {
                resultString.append(matrix.get(vertex).get(edge));
                resultString.append("|");
            }
            resultString.append("\n");
        }

        return resultString.toString();
    }
}

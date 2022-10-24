package ru.nsu.fit.oop.veber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * That class implements graph interface and all of its methods.
 * Main idea - that for every vertex we store hashmap with key = *current vertex*.
 * Value of the second hashmap - weight of the between two vertexes (if exist) and 0, if there is no edge.
 * This class must not be used for multi graphs (you must not put multiple edges and loops).
 *
 * @param <T> elem that can be in vertex (vertexes can be associated with any type)
 */
public class AdjMatrix<T> implements Graph<T> {

    private final Set<Vertex<T>> vertexes;
    private final Set<Edge<T>> edges;
    private final HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> matrix;


    /**
     * Default constructor, init all the fields with empty structures.
     */
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
            initVertex(vertex);
            for (Edge<T> edge : vertex.getStartEdges()) {
                if (!vertexes.contains(edge.getEnd())) {
                    addVertex(edge.getEnd());
                }
                this.matrix.get(vertex).put(edge.getEnd(), 1);
                this.matrix.get(edge.getEnd()).put(vertex, 1);
                this.edges.add(edge);
            }
            for (Edge<T> edge : vertex.getEndEdges()) {
                if (!vertexes.contains(edge.getStart())) {
                    addVertex(edge.getStart());
                }
                this.matrix.get(vertex).put(edge.getStart(), 1);
                this.matrix.get(edge.getStart()).put(vertex, 1);
                this.edges.add(edge);
            }
            return true;
        }
        return false;
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
     * Getter for the edges of the graph.
     *
     * @return set of the edges of the graph
     */
    @Override
    public Set<Edge<T>> getEdges() {
        return edges;
    }

    private void initVertex(Vertex<T> vertex) {
        this.vertexes.add(vertex);
        matrix.put(vertex, new HashMap<>());
        for (Vertex<T> customVertex : vertexes) {
            matrix.get(customVertex).put(vertex, 0);
            matrix.get(vertex).put(customVertex, 0);
        }

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
            edges.removeIf(edge -> edge.getStart() == vertex || edge.getEnd() == vertex);
            for (Vertex<T> customVertex : vertexes) {
                matrix.get(customVertex).remove(vertex);
            }
        }

    }

    /**
     * Method that adds some edge to graph.
     *
     * @param edge - edge that we add in graph
     */
    @Override
    public void addEdge(Edge<T> edge) {
        addVertex(edge.getStart());
        addVertex(edge.getEnd());
        if (!edges.contains(edge)) {
            edges.add(edge);
            matrix.get(edge.getStart()).put(edge.getEnd(), 1);
            matrix.get(edge.getEnd()).put(edge.getStart(), 1);
        }

    }

    /**
     * Method deletes edge from graph.
     *
     * @param edge - edge that we delete
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        matrix.get(edge.getStart()).put(edge.getEnd(), 0);
        matrix.get(edge.getEnd()).put(edge.getStart(), 0);

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
     * Creates human-reading string of the adjacency matrix.
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
        for (Vertex<T> iVertex : vertexes) {
            resultString.append(iVertex.getElem());
            resultString.append("|");
            for (Vertex<T> jVertex : vertexes) {
                resultString.append(matrix.get(jVertex).get(iVertex));
                resultString.append("|");
            }
            resultString.append("\n");
        }

        return resultString.toString();
    }
}

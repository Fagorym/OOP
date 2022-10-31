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
 * @param <V> elem that can be in vertex (vertexes can be associated with any type)
 * @param <E> elem that can be in edge (edges can be associated with any type)
 */
public class AdjMatrix<V, E> extends AbstractGraph<V, E> implements Graph<V, E> {


    private final HashMap<Vertex<V>, HashMap<Vertex<V>, Integer>> matrix;


    /**
     * Default constructor, init all the fields with empty structures.
     */
    public AdjMatrix() {
        super(new HashSet<>(), new HashSet<>());
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
    public boolean addVertex(Vertex<V> vertex) {
        if (!vertexes.contains(vertex)) {
            initVertex(vertex);
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
    public Set<Vertex<V>> getVertexes() {
        return vertexes;
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

    private void initVertex(Vertex<V> vertex) {
        this.vertexes.add(vertex);
        matrix.put(vertex, new HashMap<>());
        for (Vertex<V> customVertex : vertexes) {
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
    public void deleteVertex(Vertex<V> vertex) {
        if (vertexes.contains(vertex)) {
            vertexes.remove(vertex);
            matrix.remove(vertex);
            edges.removeIf(edge -> edge.getStart() == vertex || edge.getEnd() == vertex);
            for (Vertex<V> customVertex : vertexes) {
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
    public void addEdge(Edge<V, E> edge) {
        if (!vertexes.contains(edge.getStart()) || !vertexes.contains(edge.getEnd())) {
            throw new IllegalArgumentException("You try to add edge with vertexes, that does not in the graph. Firstly, you need to add those edges.");
        }
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
    public void deleteEdge(Edge<V, E> edge) {
        if (!vertexes.contains(edge.getEnd()) || !vertexes.contains(edge.getStart())) {
            throw new IllegalArgumentException("You try to add edge with vertexes, that does not in the graph. Firstly, you need to add those edges.");
        }
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
    public Set<Vertex<V>> getAdjVertexes(Vertex<V> vertex) {
        if (!vertexes.contains(vertex)) {
            throw new IllegalArgumentException("Graph does not contain this vertex");
        }
        Set<Vertex<V>> adjVertexes = new HashSet<>();
        var matrix = this.matrix.get(vertex);
        for (Vertex<V> customVert : this.vertexes) {
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
     * Creates human-reading string of the adjacency matrix.
     *
     * @return string representation of the matrix
     */
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(" |");
        for (Vertex<V> vertex : vertexes) {
            resultString.append(vertex.getElem());
            resultString.append("|");
        }
        resultString.append("\n");
        for (Vertex<V> iVertex : vertexes) {
            resultString.append(iVertex.getElem());
            resultString.append("|");
            for (Vertex<V> jVertex : vertexes) {
                resultString.append(matrix.get(jVertex).get(iVertex));
                resultString.append("|");
            }
            resultString.append("\n");
        }

        return resultString.toString();
    }

    /**
     * Method that takes one source vertex and counts the shortest paths to all other vertexes.
     * If there is no path - it will be maxInteger value.
     *
     * @param sourceVertex - from which vertex we count the shortest paths
     * @return hashMap from Vertex to the shortest path to this vertex
     */
    public HashMap<Vertex<V>, Integer> dijkstra(Vertex<V> sourceVertex) {
        return super.dijkstra(sourceVertex);

    }

    /**
     * Method that checks existence of the edge in the graph.
     *
     * @param elem - elem of the edge
     * @return true - exist, false - no
     */
    @Override
    public boolean containsEdge(E elem) {
        for (Edge<V, E> edge : edges) {
            if (elem.equals(edge.getElem())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that checks existence of the vertex in the graph.
     *
     * @param elem - elem of the vertex
     * @return true - exist, false - no
     */

    @Override
    public boolean containsVertex(V elem) {
        for (Vertex<V> vertex : vertexes) {
            if (elem.equals(vertex.getElem())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns vertex with provided element.
     *
     * @param elem - with this element we're exploring vertex
     * @return vertex with exploring elem
     */
    @Override
    public Vertex<V> getVertex(V elem) {
        for (Vertex<V> vertex : vertexes) {
            if (elem.equals(vertex.getElem())) {
                return vertex;
            }
        }
        return null;
    }
}

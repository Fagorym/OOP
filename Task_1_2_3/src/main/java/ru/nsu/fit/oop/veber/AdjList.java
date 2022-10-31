package ru.nsu.fit.oop.veber;


import java.util.*;

/**
 * That class implements graph interface and all of its methods.
 * Main idea - that for every element we store set of adjacency vertexes in hashmap.
 * This class must not be used for multi graphs (you must not put multiple edges and loops).
 *
 * @param <V> elem that can be in vertex (vertexes can be associated with any type)
 * @param <E> elem that can be in edge (edges can be associated with any type)
 */
public class AdjList<V, E> extends AbstractGraph<V, E> implements Graph<V, E> {

    private final HashMap<Vertex<V>, Set<Vertex<V>>> rows;

    /**
     * Default constructor, that creates empty sets and empty hashmap.
     */
    public AdjList() {
        super(new HashSet<>(), new HashSet<>());
        this.rows = new HashMap<>();
    }


    /**
     * Constructor that uses adjacency matrix to create graph.
     *
     * @param matrix - from what matrix we get vertexes.
     */
    public AdjList(AdjMatrix<V, E> matrix) {
        super(new HashSet<>(), new HashSet<>());
        this.rows = new HashMap<>();
        for (Vertex<V> vertex : matrix.getVertexes()) {
            addVertex(vertex);
        }

    }

    /**
     * Constructor that uses incident matrix to create graph.
     *
     * @param matrix - from what matrix we get vertexes
     */
    public AdjList(IncMatrix<V, E> matrix) {
        super(new HashSet<>(), new HashSet<>());
        this.rows = new HashMap<>();
        for (Vertex<V> vertex : matrix.getVertexes()) {
            addVertex(vertex);
        }
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
    public boolean addVertex(Vertex<V> vertex) {
        if (!vertexes.contains(vertex)) {
            Set<Vertex<V>> adjVertexes = new HashSet<>();
            vertexes.add(vertex);
            rows.put(vertex, adjVertexes);
            return true;
        }
        return false;
    }


    /**
     * This method deletes current vertex from graph and all edges, that was connected to this vertex.
     *
     * @param vertex - vertex that we delete from graph
     */
    @Override
    public void deleteVertex(Vertex<V> vertex) {
        rows.remove(vertex);
        vertexes.remove(vertex);
        edges.removeIf((edge) -> edge.getStart() == vertex || edge.getEnd() == vertex);

    }


    /**
     * This method adds new edge to graph.
     * Change adjacency list, added start vertex to list of end vertex and the opposite.
     *
     * @param edge - edge that we add to graph
     */
    @Override
    public void addEdge(Edge<V, E> edge) {
        if (!vertexes.contains(edge.getEnd()) || !vertexes.contains(edge.getStart())) {
            throw new IllegalArgumentException("You try to add edge with vertexes, that does not in the graph. Firstly, you need to add those edges.");
        }
        this.edges.add(edge);
        rows.get(edge.getStart()).add(edge.getEnd());
        this.rows.get(edge.getEnd()).add(edge.getStart());

    }

    /**
     * This method deleted the edge from graph.
     * We change adjacency list and current state of the vertex.
     *
     * @param edge - edge that we deleted from graph
     */

    @Override
    public void deleteEdge(Edge<V, E> edge) {
        this.edges.remove(edge);
        this.rows.get(edge.getStart()).remove(edge.getEnd());
        this.rows.get(edge.getEnd()).remove(edge.getStart());
    }

    /**
     * Method that gets set of edges of the graph.
     *
     * @return set of graph edges
     */
    @Override
    public Set<Edge<V, E>> getEdges() {
        return edges;
    }

    /**
     * Method that gets set of vertexes of the graph.
     *
     * @return set of graph vertexes
     */
    @Override
    public Set<Vertex<V>> getVertexes() {
        return vertexes;
    }

    /**
     * Method get the row of the current vertex and returns the set of adjacency vertexes associated with it.
     *
     * @param vertex - from what vertex we need to get adjacency list.
     * @return set of adjacency vertexes
     */

    @Override
    public Set<Vertex<V>> getAdjVertexes(Vertex<V> vertex) {
        return this.rows.get(vertex);
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
     * Set the element of the current vertex.
     *
     * @param vertex  - to what vertex we set element
     * @param newElem - what element will be actual element
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

    /**
     * Creates a human-reading string of current adjacency list and returns it.
     *
     * @return adjacency list as a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Vertex<V> vertex : vertexes) {
            builder.append(vertex.getElem());
            builder.append(" - ");
            builder.append(rows.get(vertex).toString());
            builder.append('\n');
        }
        return builder.toString();
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

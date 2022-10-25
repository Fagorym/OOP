package ru.nsu.fit.oop.veber;


import java.util.*;

/**
 * That class implements graph interface and all of its methods.
 * Main idea - that for every element we store set of adjacency vertexes in hashmap.
 * This class must not be used for multi graphs (you must not put multiple edges and loops).
 *
 * @param <T> elem that can be in vertex (vertexes can be associated with any type)
 */
public class AdjList<T> implements Graph<T> {

    private final HashMap<Vertex<T>, Set<Vertex<T>>> rows;
    private final Set<Edge<T>> edges;
    private final Set<Vertex<T>> vertexes;

    /**
     * Default constructor, that creates empty sets and empty hashmap.
     */
    public AdjList() {
        this.rows = new HashMap<>();
        this.edges = new HashSet<>();
        this.vertexes = new HashSet<>();
    }


    /**
     * Constructor that uses adjacency matrix to create graph.
     *
     * @param matrix - from what matrix we get vertexes.
     */
    public AdjList(AdjMatrix<T> matrix) {
        this.rows = new HashMap<>();
        this.edges = new HashSet<>();
        this.vertexes = new HashSet<>();
        for (Vertex<T> vertex : matrix.getVertexes()) {
            addVertex(vertex);
        }

    }

    /**
     * Constructor that uses incident matrix to create graph.
     *
     * @param matrix - from what matrix we get vertexes
     */
    public AdjList(IncMatrix<T> matrix) {
        this.rows = new HashMap<>();
        this.edges = new HashSet<>();
        this.vertexes = new HashSet<>();
        for (Vertex<T> vertex : matrix.getVertexes()) {
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
    public boolean addVertex(Vertex<T> vertex) {
        if (!vertexes.contains(vertex)) {
            Set<Vertex<T>> adjVertexes = new HashSet<>();
            vertexes.add(vertex);
            for (Edge<T> edge : vertex.getStartEdges()) {
                edges.add(edge);
                addAdjVertex(adjVertexes, edge.getEnd());

            }
            for (Edge<T> edge : vertex.getEndEdges()) {
                edges.add(edge);
                addAdjVertex(adjVertexes, edge.getStart());

            }
            rows.put(vertex, adjVertexes);
            return true;
        }
        return false;
    }

    private void addAdjVertex(Set<Vertex<T>> adjVertexes, Vertex<T> vertex) {
        adjVertexes.add(vertex);
        addVertex(vertex);
    }

    /**
     * This method deletes current vertex from graph and all edges, that was connected to this vertex.
     *
     * @param vertex - vertex that we delete from graph
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        rows.remove(vertex);
        vertexes.remove(vertex);
        for (Edge<T> edge : vertex.getStartEdges()) {
            edges.remove(edge);
            vertex.removeEdge(edge);
            edge.getEnd().removeEdge(edge);
        }
        for (Edge<T> edge : vertex.getStartEdges()) {
            edges.remove(edge);
            vertex.removeEdge(edge);
            edge.getEnd().removeEdge(edge);
        }

    }


    /**
     * This method adds new edge to graph.
     * Change adjacency list, added start vertex to list of end vertex and the opposite.
     *
     * @param edge - edge that we add to graph
     */
    @Override
    public void addEdge(Edge<T> edge) {
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
    public void deleteEdge(Edge<T> edge) {
        this.edges.remove(edge);
        this.rows.get(edge.getStart()).remove(edge.getEnd());
        this.rows.get(edge.getEnd()).remove(edge.getStart());
        edge.getStart().removeEdge(edge);
        edge.getEnd().removeEdge(edge);


    }

    /**
     * Method that gets set of edges of the graph.
     *
     * @return set of graph edges
     */
    @Override
    public Set<Edge<T>> getEdges() {
        return edges;
    }

    /**
     * Method that gets set of vertexes of the graph.
     *
     * @return set of graph vertexes
     */
    @Override
    public Set<Vertex<T>> getVertexes() {
        return vertexes;
    }

    /**
     * Method get the row of the current vertex and returns the set of adjacency vertexes associated with it.
     *
     * @param vertex - from what vertex we need to get adjacency list.
     * @return set of adjacency vertexes
     */

    @Override
    public Set<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        return this.rows.get(vertex);
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
     * Set the element of the current vertex.
     *
     * @param vertex  - to what vertex we set element
     * @param newElem - what element will be actual element
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
        for (Vertex<T> vertex : vertexes) {
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
     * @return hashMap with Vertex -> the shortest path to this vertex
     */
    public HashMap<Vertex<T>, Integer> dijkstra(Vertex<T> sourceVertex) {
        HashMap<Vertex<T>, Integer> resultMap = new HashMap<>();
        List<Vertex<T>> queue = new ArrayList<>();
        for (Vertex<T> graphVertex : vertexes) {
            resultMap.put(graphVertex, Integer.MAX_VALUE);
        }
        resultMap.put(sourceVertex, 0);
        Vertex<T> minVertex = null;
        queue.add(sourceVertex);
        while (!queue.isEmpty()) {
            Integer minValue = Integer.MAX_VALUE;
            for (Vertex<T> nextVertex : queue) {
                if (resultMap.get(nextVertex) < minValue) {
                    minVertex = nextVertex;
                    minValue = resultMap.get(nextVertex);
                }
            }
            queue.remove(minVertex);

            for (Edge<T> edgeFromMin : minVertex.getStartEdges()) {
                if (edgeFromMin.getWeight() + minValue < resultMap.get(edgeFromMin.getEnd())) {
                    resultMap.put(edgeFromMin.getEnd(), edgeFromMin.getWeight() + minValue);
                    queue.add(edgeFromMin.getEnd());
                }
            }
        }

        return resultMap;

    }
}

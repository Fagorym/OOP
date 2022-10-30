package ru.nsu.fit.oop.veber;

/**
 * Class that represent edge of the graph.
 *
 * @param <V> elem that can be in vertex (vertexes can be associated with any type)
 * @param <E> elem that can be in edge (edges can be associated with any type)
 */
public class Edge<V, E> {
    private final Vertex<V> start;
    private final Vertex<V> end;
    private int weight;
    private E name;

    /**
     * Default constructor of the edge.
     *
     * @param name   - will be current name of the edge
     * @param weight - will be current weight of the edge
     * @param start  - will be current start vertex of the edge
     * @param end    - will be current end vertex of the edge
     */
    public Edge(E name, int weight, Vertex<V> start, Vertex<V> end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
        this.name = name;
    }

    /**
     * Getter for the weight of the edge.
     *
     * @return weight of the edge
     */
    public int getWeight() {
        return weight;
    }


    /**
     * Getter for the name of the edge.
     *
     * @return the name of the edge
     */
    public E getName() {
        return name;
    }

    /**
     * Setter for the name of the edge.
     *
     * @param name - will be new name of the edge
     */
    public void setName(E name) {
        this.name = name;
    }

    /**
     * Getter for the start vertex of the edge.
     *
     * @return start vertex of the edge
     */
    public Vertex<V> getStart() {
        return start;
    }

    /**
     * Getter for the end vertex of the edge.
     *
     * @return end vertex of the edge
     */

    public Vertex<V> getEnd() {
        return end;
    }

}

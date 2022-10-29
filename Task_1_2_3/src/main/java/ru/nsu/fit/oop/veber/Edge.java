package ru.nsu.fit.oop.veber;

/**
 * Class that represent edge of the graph.
 *
 * @param <T> - elem of the vertex.
 */
public class Edge<T, T1> {
    private final Vertex<T> start;
    private final Vertex<T> end;
    private int weight;
    private T1 name;

    /**
     * Default constructor of the edge.
     *
     * @param name   - will be current name of the edge
     * @param weight - will be current weight of the edge
     * @param start  - will be current start vertex of the edge
     * @param end    - will be current end vertex of the edge
     */
    public Edge(T1 name, int weight, Vertex<T> start, Vertex<T> end) {
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
    public T1 getName() {
        return name;
    }

    /**
     * Setter for the name of the edge.
     *
     * @param name - will be new name of the edge
     */
    public void setName(T1 name) {
        this.name = name;
    }

    /**
     * Getter for the start vertex of the edge.
     *
     * @return start vertex of the edge
     */
    public Vertex<T> getStart() {
        return start;
    }

    /**
     * Getter for the end vertex of the edge.
     *
     * @return end vertex of the edge
     */

    public Vertex<T> getEnd() {
        return end;
    }

}

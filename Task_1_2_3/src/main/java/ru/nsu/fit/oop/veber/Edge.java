package ru.nsu.fit.oop.veber;

/**
 * Class that represent edge of the graph.
 *
 * @param <T> - elem of the vertex.
 */
public class Edge<T> {
    private int weight;

    private String name;
    private Vertex<T> start;
    private Vertex<T> end;

    /**
     * Default constructor of the edge.
     *
     * @param name   - will be name of the edge
     * @param weight - will be weight of the edge
     * @param start  - will be start vertex of the edge
     * @param end    - will be end vertex of the edge
     */
    public Edge(String name, int weight, Vertex<T> start, Vertex<T> end) {
        this.weight = weight;
        this.start = start;
        start.addStartEdge(this);
        this.end = end;
        end.addEndEdge(this);
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
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the edge.
     *
     * @param name - will be new name of the edge
     */
    public void setName(String name) {
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

package ru.nsu.fit.oop.veber;


import java.util.HashSet;
import java.util.Set;

/**
 * Class that represent vertex of the graph,
 * that can store one element and a set start and end edges.
 *
 * @param <T> - elem of the vertex
 */
public class Vertex<T> {
    private T elem;
    private Set<Edge<T>> startEdges;
    private Set<Edge<T>> endEdges;

    /**
     * Default constructor of the vertex,
     * that creates empty hashSets for edges and fill "elem" field by provided param.
     *
     * @param elem - will be element of the vertex
     */
    public Vertex(T elem) {
        this.elem = elem;
        this.startEdges = new HashSet<>();
        this.endEdges = new HashSet<>();
    }

    /**
     * Method that added provided edge to ended edges.
     *
     * @param edge - will be added to the list of started edges
     */
    public void addStartEdge(Edge<T> edge) {
        this.startEdges.add(edge);
    }

    /**
     * Method that added provided edge to ended edges set.
     *
     * @param edge - will be added to the list of ended edges
     */
    public void addEndEdge(Edge<T> edge) {
        this.endEdges.add(edge);
    }

    /**
     * Removes edge from both sets, started and ended edges.
     *
     * @param edge - that edge will be deleted from both sets
     */
    public void removeEdge(Edge<T> edge) {
        this.startEdges.remove(edge);
        this.endEdges.remove(edge);
    }

    /**
     * Getter for the set of start edges.
     *
     * @return set of the start edges
     */
    public Set<Edge<T>> getStartEdges() {
        return startEdges;
    }


    /**
     * Getter for the set of end edges.
     *
     * @return set of the end edges
     */
    public Set<Edge<T>> getEndEdges() {
        return endEdges;
    }

    /**
     * Getter for the element of the vertex.
     *
     * @return element of the vertex
     */
    public T getElem() {
        return elem;
    }

    /**
     * Setter for the element of the vertex.
     *
     * @param elem - will be new element of the vertex
     */
    public void setElem(T elem) {
        this.elem = elem;
    }

    /**
     * Return string representation of the elem of the vertex.
     *
     * @return elem of the vertex as string
     */
    @Override
    public String toString() {
        return elem.toString();
    }

}

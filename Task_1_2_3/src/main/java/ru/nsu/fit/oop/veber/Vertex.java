package ru.nsu.fit.oop.veber;


/**
 * Class that represent vertex of the graph,
 * that can store one element and a set start and end edges.
 *
 * @param <T> - elem of the vertex
 */
public class Vertex<T> {
    private T elem;

    /**
     * Default constructor of the vertex,
     * that creates empty hashSets for edges and fill "elem" field by provided param.
     *
     * @param elem - will be element of the vertex
     */
    public Vertex(T elem) {
        this.elem = elem;
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

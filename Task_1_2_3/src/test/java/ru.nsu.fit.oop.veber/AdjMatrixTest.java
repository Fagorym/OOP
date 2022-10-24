package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdjMatrixTest<T> {
    AdjMatrix<String> testMatrix;

    @BeforeEach
    public void initMatrix() {
        this.testMatrix = new AdjMatrix<>();
    }

    @Test
    public void addVertexes() {
        Vertex<String> firstVertex = new Vertex<>("A");
        Vertex<String> secondVertex = new Vertex<>("B");
        Vertex<String> thirdVertex = new Vertex<>("C");
        Edge<String> firstEdge = new Edge<>("1", 12, firstVertex, secondVertex);
        Edge<String> secondEdge = new Edge<>("2", 43, firstVertex, thirdVertex);
        testMatrix.addVertex(firstVertex);
        System.out.println(testMatrix.toString());
        testMatrix.deleteEdge(firstEdge);
        System.out.println(testMatrix.toString());
        testMatrix.deleteEdge(secondEdge);
        System.out.println(testMatrix.toString());

    }


}

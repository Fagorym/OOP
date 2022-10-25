package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class with tests for adjMatrix.
 */
public class AdjMatrixTest {
    AdjMatrix<String> testedMatrix;

    @BeforeEach
    public void initMatrix() {
        testedMatrix = new AdjMatrix<>();
    }

    @Test
    public void testToStringMethod() {
        Vertex<String> firstVertex = new Vertex<>("A");
        Vertex<String> secondVertex = new Vertex<>("B");
        Vertex<String> thirdVertex = new Vertex<>("C");
        Edge<String> firstEdge = new Edge<>("1", 12, firstVertex, secondVertex);
        Edge<String> secondEdge = new Edge<>("2", 43, firstVertex, thirdVertex);
        testedMatrix.addVertex(firstVertex);
        Assertions.assertEquals(2, testedMatrix.getEdgesNumber());
        Assertions.assertEquals("A", testedMatrix.getVertexElement(firstVertex));
        Assertions.assertEquals(3, testedMatrix.getVertexNumber());
        Assertions.assertEquals(2, testedMatrix.getVertexDegree(firstVertex));
        testedMatrix.deleteVertex(firstVertex);
        Assertions.assertEquals(2, testedMatrix.getVertexNumber());
        Assertions.assertEquals(0, testedMatrix.getEdgesNumber());
        testedMatrix.addEdge(firstEdge);
        Assertions.assertEquals(2, testedMatrix.getVertexDegree(firstVertex));
        System.out.println(testedMatrix.toString());
        testedMatrix.deleteEdge(firstEdge);
        Assertions.assertEquals(1, testedMatrix.getEdgesNumber());
        Assertions.assertEquals(3, testedMatrix.getVertexNumber());
    }
}

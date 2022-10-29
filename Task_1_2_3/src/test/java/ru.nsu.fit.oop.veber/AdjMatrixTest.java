package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class with tests for adjMatrix.
 */
public class AdjMatrixTest {
    AdjMatrix<String, String> testedMatrix;

    @BeforeEach
    public void initMatrix() {
        testedMatrix = new AdjMatrix<>();
    }

    @Test
    public void testToStringMethod() {
        Vertex<String> firstVertex = new Vertex<>("A");
        Vertex<String> secondVertex = new Vertex<>("B");
        Vertex<String> thirdVertex = new Vertex<>("C");
        Edge<String, String> firstEdge = new Edge<>("1", 12, firstVertex, secondVertex);
        Edge<String, String> secondEdge = new Edge<>("2", 43, firstVertex, thirdVertex);
        testedMatrix.addVertex(firstVertex);
        testedMatrix.addVertex(secondVertex);
        testedMatrix.addVertex(thirdVertex);
        testedMatrix.addEdge(firstEdge);
        testedMatrix.addEdge(secondEdge);
        Assertions.assertEquals(2, testedMatrix.getEdgesNumber());
        Assertions.assertEquals("A", testedMatrix.getVertexElement(firstVertex));
        Assertions.assertEquals(3, testedMatrix.getVertexNumber());
        Assertions.assertEquals(2, testedMatrix.getVertexDegree(firstVertex));
        testedMatrix.deleteVertex(firstVertex);
        Assertions.assertEquals(2, testedMatrix.getVertexNumber());
        Assertions.assertEquals(0, testedMatrix.getEdgesNumber());
        System.out.println(testedMatrix.toString());
        Assertions.assertEquals(0, testedMatrix.getEdgesNumber());
        Assertions.assertEquals(2, testedMatrix.getVertexNumber());
    }
}

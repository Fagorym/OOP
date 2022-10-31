package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class with tests for adjMatrix.
 */
public class AdjMatrixTest {
    AdjMatrix<String, Integer> testedMatrix;

    @BeforeEach
    public void initMatrix() throws FileNotFoundException {
        File file = new File("./src/test/resources/matrix.txt");
        Parser parser = new Parser(file);
        testedMatrix = parser.parseAdjacencyMatrix();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testToStringMethod() {
        var vertexes = testedMatrix.getVertexes().toArray();
        Vertex<String> firstVertex = (Vertex<String>) vertexes[0];
        Assertions.assertEquals(20, testedMatrix.getEdgesNumber());
        Assertions.assertEquals(7, testedMatrix.getVertexNumber());
        Assertions.assertTrue(testedMatrix.containsVertex(testedMatrix.getVertexElement(firstVertex)));
        testedMatrix.setVertexElement(firstVertex, "aboba");
        Assertions.assertEquals("aboba", testedMatrix.getVertexElement(firstVertex));
        System.out.println(testedMatrix.getVertexNumber());
        Assertions.assertFalse(testedMatrix.addVertex(firstVertex));
        var edges = testedMatrix.getEdges().toArray();
        Edge<String, Integer> edge = (Edge<String, Integer>) edges[1];
        Assertions.assertTrue(testedMatrix.containsEdge(edge.getElem()));
        Assertions.assertEquals(7, testedMatrix.dijkstra(firstVertex).entrySet().size());
        testedMatrix.deleteEdge(edge);
        Assertions.assertEquals(19, testedMatrix.getEdgesNumber());
        var adjVert = testedMatrix.getAdjVertexes(firstVertex);
        testedMatrix.deleteVertex(firstVertex);
        Assertions.assertEquals(6, testedMatrix.getVertexNumber());
        System.out.println(testedMatrix.toString());
    }
}

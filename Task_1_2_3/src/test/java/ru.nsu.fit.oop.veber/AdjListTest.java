package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;


/**
 * Class to test adjList functions.
 */
public class AdjListTest {
    AdjList<String, Integer> testedList;

    @BeforeEach
    public void initList() throws FileNotFoundException {
        File file = new File("./src/test/resources/list.txt");
        Parser parser = new Parser(file);
        this.testedList = parser.parseAdjacencyList();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddVertexes() {
        var vertexes = testedList.getVertexes().toArray();
        Vertex<String> firstVertex = (Vertex<String>) vertexes[1];
        Assertions.assertEquals(6, testedList.getEdgesNumber());
        Assertions.assertEquals(6, testedList.getVertexNumber());
        Assertions.assertTrue(testedList.containsVertex(testedList.getVertexElement(firstVertex)));
        testedList.setVertexElement(firstVertex, "aboba");
        Assertions.assertEquals("aboba", testedList.getVertexElement(firstVertex));
        System.out.println(testedList.getVertexNumber());
        Assertions.assertFalse(testedList.addVertex(firstVertex));
        var edges = testedList.getEdges().toArray();
        Edge<String, Integer> edge = (Edge<String, Integer>) edges[1];
        Assertions.assertTrue(testedList.containsEdge(edge.getElem()));
        Assertions.assertEquals(6, testedList.dijkstra(firstVertex).entrySet().size());
        testedList.deleteEdge(edge);
        Assertions.assertEquals(5, testedList.getEdgesNumber());
        var adjVert = testedList.getAdjVertexes(firstVertex);
        Assertions.assertEquals(2, adjVert.size());
        testedList.deleteVertex(firstVertex);
        Assertions.assertEquals(5, testedList.getVertexNumber());
        Assertions.assertThrows(IllegalArgumentException.class, () -> testedList.addEdge(edge));
        Assertions.assertFalse(testedList.containsEdge(edge.getElem()));
        System.out.println(testedList.toString());

    }


}

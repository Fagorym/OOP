package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;


/**
 * Class to test adjList functions.
 */
public class AdjListTest {
    AdjList<Integer> testedList;

    @BeforeEach
    public void initList() {
        this.testedList = new AdjList<>();
    }

    @Test
    public void testAddVertexes() {
        Vertex<Integer> newVertex = new Vertex<>(2);
        this.testedList.addVertex(newVertex);
        Assertions.assertEquals(2, this.testedList.getVertexElement(newVertex));
        Vertex<Integer> newVertex2 = new Vertex<>(3);
        this.testedList.addVertex(newVertex2);
        Assertions.assertEquals(3, this.testedList.getVertexElement(newVertex2));
        Edge<Integer> newEdge = new Edge<>("1", 2, newVertex, newVertex2);
        this.testedList.addEdge(newEdge);
        var set = new HashSet<Vertex<Integer>>();
        set.add(newVertex2);
        Assertions.assertEquals(set, this.testedList.getAdjVertexes(newVertex));
        this.testedList.setVertexElement(newVertex, 4);
        Assertions.assertEquals(1, this.testedList.getVertexDegree(newVertex));
        Assertions.assertEquals(2, this.testedList.getVertexNumber());
        Assertions.assertEquals(1, this.testedList.getEdgesNumber());
        Assertions.assertEquals(this.testedList.getVertexElement(newVertex), 4);
        this.testedList.deleteEdge(newEdge);
        Assertions.assertEquals(this.testedList.getAdjVertexes(newVertex), new HashSet<>());
        Assertions.assertEquals(this.testedList.getAdjVertexes(newVertex2), new HashSet<>());
        Assertions.assertEquals(0, this.testedList.getEdgesNumber());
        this.testedList.deleteVertex(newVertex);
        Assertions.assertEquals(1, this.testedList.getVertexNumber());

    }

    @Test
    public void testAddVertexWithEdges() {
        Vertex<Integer> firstVertex = new Vertex<>(2);
        Vertex<Integer> secondVertex = new Vertex<>(3);
        new Edge<>("1", 12, firstVertex, secondVertex);
        this.testedList.addVertex(firstVertex);
        Assertions.assertEquals(2, testedList.getVertexNumber());
        Assertions.assertEquals(1, testedList.getEdgesNumber());
        testedList.deleteVertex(firstVertex);
        Assertions.assertEquals(0, testedList.getEdgesNumber());
        Assertions.assertEquals(1, testedList.getVertexNumber());
        Assertions.assertFalse(testedList.addVertex(secondVertex));
        testedList.deleteVertex(secondVertex);
        Assertions.assertEquals(0, testedList.getVertexNumber());
    }

    @Test
    public void testToStringMethod() {
        Vertex<Integer> firstVertex = new Vertex<>(2);
        Vertex<Integer> secondVertex = new Vertex<>(3);
        new Edge<>("1", 12, firstVertex, secondVertex);
        new Edge<>("2", 13, firstVertex, secondVertex);
        Vertex<Integer> thirdVertex = new Vertex<>(10);
        this.testedList.addVertex(firstVertex);
        testedList.addVertex(thirdVertex);
        System.out.println(testedList.toString());

    }

    @Test
    public void testDijkstraMethod() {
        Vertex<Integer> firstVertex = new Vertex<>(2);
        Vertex<Integer> secondVertex = new Vertex<>(3);
        Vertex<Integer> thirdVertex = new Vertex<>(4);
        new Edge<>("1", 12, firstVertex, secondVertex);
        new Edge<>("2", 40, firstVertex, thirdVertex);
        new Edge<>("3", 4, secondVertex, thirdVertex);
        this.testedList.addVertex(firstVertex);
        var res = this.testedList.dijkstra(firstVertex);
        System.out.println(res.entrySet());

    }


}

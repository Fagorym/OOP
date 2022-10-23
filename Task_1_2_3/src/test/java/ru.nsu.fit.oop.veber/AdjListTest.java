package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class AdjListTest<T> {
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
        Edge<Integer> newEdge = new Edge<>(2, newVertex, newVertex2);
        this.testedList.addEdge(newEdge);
        var set = new HashSet<Vertex<Integer>>();
        set.add(newVertex2);
        Assertions.assertEquals(set, this.testedList.getAdjVertexes(newVertex));
        this.testedList.setVertexElement(newVertex, 4);
        Assertions.assertEquals(this.testedList.getVertexElement(newVertex), 4);
        this.testedList.deleteEdge(newEdge);
        Assertions.assertEquals(this.testedList.getAdjVertexes(newVertex), new HashSet<>());
        Assertions.assertEquals(this.testedList.getAdjVertexes(newVertex2), new HashSet<>());
    }


}

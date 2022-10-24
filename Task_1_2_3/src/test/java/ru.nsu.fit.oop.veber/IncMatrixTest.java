package ru.nsu.fit.oop.veber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IncMatrixTest {
    IncMatrix<String> testedMatrix;

    @BeforeEach
    public void initMatrix(){
        testedMatrix = new IncMatrix<>();
    }

    @Test
    public void testToStringMethod(){
        Vertex<String> firstVertex = new Vertex<>("A");
        Vertex<String> secondVertex = new Vertex<>("B");
        Vertex<String> thirdVertex = new Vertex<>("C");
        Edge<String> firstEdge = new Edge<>("1", 12, firstVertex, secondVertex);
        Edge<String> secondEdge = new Edge<>("2", 43, firstVertex, thirdVertex);
        testedMatrix.addVertex(firstVertex);
        testedMatrix.addVertex(secondVertex);
        testedMatrix.addVertex(thirdVertex);
        testedMatrix.addEdge(firstEdge);
        testedMatrix.addEdge(secondEdge);
        System.out.println(testedMatrix.toString());
    }
}

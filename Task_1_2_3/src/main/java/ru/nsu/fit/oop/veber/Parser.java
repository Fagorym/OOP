package ru.nsu.fit.oop.veber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {
    File file;

    public Parser(File file) {
        this.file = file;
    }

    public <V, E> AdjMatrix<V, E> parseAdjacencyMatrix() throws FileNotFoundException {
        InputStreamReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        return null;

    }

    public AdjList<String, Integer> parseAdjacencyList() throws FileNotFoundException {
        AdjList<String, Integer> resultList = new AdjList<>();
        InputStreamReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        int name = 1;
        while (scanner.hasNext()) {
            String startElem = scanner.next();
            Vertex<String> startVertex = resultList.getVertex(startElem);
            if (startVertex == null) {
                startVertex = new Vertex<>(startElem);
            }
            String endElem = scanner.next();
            Vertex<String> endVertex = resultList.getVertex(endElem);
            if (endVertex == null) {
                endVertex = new Vertex<>(endElem);
            }
            int elem = Integer.parseInt(scanner.next());
            if (!resultList.containsVertex(startElem)) {
                resultList.addVertex(startVertex);
            }
            if (!resultList.containsVertex(endElem)) {
                resultList.addVertex(endVertex);
            }
            Edge<String, Integer> newEdge = new Edge<>(name, elem, startVertex, endVertex);
            resultList.addEdge(newEdge);
            name++;
        }
        return resultList;
    }

    public <V, E> IncMatrix<V, E> parseIncMatrix() {
        return null;
    }
}

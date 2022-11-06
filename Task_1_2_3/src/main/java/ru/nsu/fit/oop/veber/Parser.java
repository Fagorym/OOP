package ru.nsu.fit.oop.veber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    File file;

    public Parser(File file) {
        this.file = file;
    }

    public AdjMatrix<String, Integer> parseAdjacencyMatrix() throws FileNotFoundException {
        AdjMatrix<String, Integer> resultMatrix = new AdjMatrix<>();
        ArrayList<Vertex<String>> indexToVertex = new ArrayList<>();
        InputStreamReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        String vertLine = scanner.nextLine();
        Scanner vertScanner = new Scanner(vertLine);
        while (vertScanner.hasNext()) {
            String elem = vertScanner.next();
            Vertex<String> curVertex = new Vertex<>(elem);
            resultMatrix.addVertex(curVertex);
            indexToVertex.add(curVertex);
        }

        for (int i = 0; i < indexToVertex.size(); i++) {
            for (int j = 0; j < indexToVertex.size(); j++) {
                String token = scanner.next();
                if (token.equals("-")) {
                    continue;
                } else {
                    int weight = Integer.parseInt(token);
                    var start = indexToVertex.get(i);
                    var end = indexToVertex.get(j);
                    Edge<String, Integer> edge = new Edge<>(i, weight, start, end);
                    resultMatrix.addEdge(edge);
                }
            }
        }
        return resultMatrix;

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
}

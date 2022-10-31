package ru.nsu.fit.oop.veber;

import java.util.*;

public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    protected final Set<Vertex<V>> vertexes;
    protected final Set<Edge<V, E>> edges;


    protected AbstractGraph(Set<Vertex<V>> vertexes, Set<Edge<V, E>> edges) {
        this.edges = edges;
        this.vertexes = vertexes;
    }

    /**
     * Method that takes one source vertex and counts the shortest paths to all other vertexes.
     * If there is no path - it will be maxInteger value.
     *
     * @param sourceVertex - from which vertex we count the shortest paths
     * @return hashMap from Vertex to the shortest path to this vertex
     */

    public HashMap<Vertex<V>, Integer> dijkstra(Vertex<V> sourceVertex) {
        HashMap<Vertex<V>, Integer> resultMap = new HashMap<>();
        List<Vertex<V>> queue = new ArrayList<>();
        for (Vertex<V> graphVertex : vertexes) {
            resultMap.put(graphVertex, Integer.MAX_VALUE);
        }
        resultMap.put(sourceVertex, 0);
        Vertex<V> minVertex = null;
        queue.add(sourceVertex);
        while (!queue.isEmpty()) {
            Integer minValue = Integer.MAX_VALUE;
            for (Vertex<V> nextVertex : queue) {
                if (resultMap.get(nextVertex) < minValue) {
                    minVertex = nextVertex;
                    minValue = resultMap.get(nextVertex);
                }
            }
            queue.remove(minVertex);

            for (Edge<V, E> edgeFromMin : edges) {
                if (edgeFromMin.getStart() == minVertex && edgeFromMin.getWeight() + minValue < resultMap.get(edgeFromMin.getEnd())) {
                    resultMap.put(edgeFromMin.getEnd(), edgeFromMin.getWeight() + minValue);
                    queue.add(edgeFromMin.getEnd());
                }
            }
        }

        return resultMap;

    }
}

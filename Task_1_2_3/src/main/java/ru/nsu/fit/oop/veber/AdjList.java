package ru.nsu.fit.oop.veber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdjList<T> implements Graph<T> {

    List<column<T>> columns;
    int edgesNumber;

    public AdjList() {
        this.columns = new ArrayList<>();
    }

    public AdjList(Collection<Vertex<T>> vertexes) {
        this.columns = new ArrayList<>();
        for (var vertex : vertexes) {
            addVertex(vertex);
        }
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        var newColumn = new column<T>();
        newColumn.vertex = vertex;
        for (var edge : vertex.endEdges) {
            newColumn.adjVertexes.add(edge.start);
            this.edgesNumber++;
        }
        for (var edge : vertex.startEdges) {
            newColumn.adjVertexes.add(edge.end);
            this.edgesNumber++;
        }
        this.columns.add(newColumn);
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        for (var column : this.columns) {
            if (column.vertex == vertex) {
                this.columns.remove(column);
            } else {
                column.adjVertexes.remove(vertex);
            }
        }
    }

    @Override
    public void addEdge(Edge<T> edge) {
        for (var column : this.columns) {
            if (column.vertex == edge.start) {
                column.adjVertexes.add(edge.end);
                column.vertex.addStartEdge(edge);
            } else if (column.vertex == edge.end) {
                column.adjVertexes.add(edge.start);
                column.vertex.addEndEdge(edge);
            }

        }
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        for (var column : this.columns) {
            if (column.vertex == edge.start) {
                column.adjVertexes.remove(edge.end);
                column.vertex.startEdges.remove(edge);
            } else if (column.vertex == edge.end) {
                column.adjVertexes.remove(edge.start);
                column.vertex.endEdges.remove(edge);
            }
        }
    }

    @Override
    public List<Vertex<T>> getAdjVertexes(Vertex<T> vertex) {
        for (var column : this.columns) {
            if (column.vertex == vertex) {
                return column.adjVertexes;
            }
        }
        throw new IllegalArgumentException("No such vertex in graph");
    }

    @Override
    public T getVertexElement(Vertex<T> vertex) {
        for (var column : this.columns) {
            if (column.vertex == vertex) {
                return column.vertex.elem;
            }
        }
        throw new IllegalArgumentException("No such vertex in graph");
    }

    @Override
    public void setVertexElement(Vertex<T> vertex, T newElem) {
        for (var column : this.columns) {
            if (column.vertex == vertex) {
                column.vertex.elem = newElem;
                return;
            }
        }
        throw new IllegalArgumentException("No such vertex in graph");
    }

    @Override
    public int getVertexDegree(Vertex<T> vertex) {
        return vertex.startEdges.size() + vertex.endEdges.size();
    }

    @Override
    public int getVertexNumber(Vertex<T> vertex) {
        return this.columns.size();
    }

    @Override
    public int getEdgesNumber(Vertex<T> vertex) {
        return this.edgesNumber;
    }


    static private class column<T> {
        Vertex<T> vertex;
        List<Vertex<T>> adjVertexes;

        public column() {
            adjVertexes = new ArrayList<>();
        }
    }

}

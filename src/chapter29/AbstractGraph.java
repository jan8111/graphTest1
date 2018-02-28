package chapter29;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractGraph<V> {
    protected List<V> vertices = new ArrayList<>(); // Store vertices
    protected List<List<Edge>> neighbors = new ArrayList<>(); // Adjacency lists


    public int getSize() {
        return this.vertices.size();
    }


    public List<V> getVertices() {
        return vertices;
    }

    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index))
            result.add(e.v);
        return result;
    }

    public V getVertex(int index) {
        return vertices.get(index);
    }

}


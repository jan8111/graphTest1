package chapter29;

import java.util.*;

public class WeightedGraph<V> extends AbstractGraph<V> {

  
  public WeightedGraph(V[] vertices, int[][] edges) {
    createWeightedGraph(Arrays.asList(vertices), edges);
  }


  private void createWeightedGraph(List<V> vertices, int[][] edges) {
    this.vertices = vertices;     

    for (int i = 0; i < vertices.size(); i++) {
      neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
    }

    for (int i = 0; i < edges.length; i++) {
      neighbors.get(edges[i][0]).add(
        new Edge(edges[i][0], edges[i][1], edges[i][2]));
    }
  }


  public ArrayList<Integer> getShortestPath(int sourceVertexIndex, int destVertexIndex) {
    // cost[v] stores the cost of the path from v to the source
    double[] cost = new double[getSize()];
    for (int i = 0; i < cost.length; i++) {
      cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
    }
    cost[sourceVertexIndex] = 0; // Cost of source is 0

    // parent[v] stores the previous vertex of v in the path
    int[] parent = new int[getSize()];
    parent[sourceVertexIndex] = -1; // The parent of source is set to -1

    // T stores the vertices whose path found so far
    Set<Integer> T = new HashSet<>();

    // Expand T
    while (T.size() < getSize()) {
      // Find smallest cost v in V - T
      int u = -1; // Vertex to be determined
      double currentMinCost = Double.POSITIVE_INFINITY;
      for (int i = 0; i < getSize(); i++) {
        if (!T.contains(i) && cost[i] < currentMinCost) {
          currentMinCost = cost[i];
          u = i;
        }
      }

      if (u == -1) break; else T.add(u); // Add a new vertex to T

      // Adjust cost[v] for v that is adjacent to u and v in V - T
      for (Edge e : neighbors.get(u)) {
        if (!T.contains(e.v)
            && cost[e.v] > cost[u] + ((Edge)e).weight) {
          cost[e.v] = cost[u] + ((Edge)e).weight;
          parent[e.v] = u;
        }
      }
    } // End of while

    ArrayList<Integer> retPath = new ArrayList<>();
    do {
      retPath.add( (destVertexIndex));
      destVertexIndex = parent[destVertexIndex];
    }
    while (destVertexIndex != -1);
    return retPath;
  }


}


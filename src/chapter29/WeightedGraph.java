package chapter29;

import java.util.*;
import chapter28.*;

public class WeightedGraph<V> extends UnweightedGraph<V> {
  /** Construct an empty */
  public WeightedGraph() {
  }
  
  /** Construct a WeightedGraph from vertices and edged in arrays */
  public WeightedGraph(V[] vertices, int[][] edges) {
    createWeightedGraph(Arrays.asList(vertices), edges);
  }

  /** Construct a WeightedGraph from vertices and edges in list */
  public WeightedGraph(int[][] edges, int numberOfVertices) {
    List<V> vertices = new ArrayList<>();
    for (int i = 0; i < numberOfVertices; i++)
      vertices.add((V)(new Integer(i)));
    
    createWeightedGraph(vertices, edges);
  }

  /** Construct a WeightedGraph for vertices 0, 1, 2 and edge list */
  public WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
    createWeightedGraph(vertices, edges);
  }

  /** Construct a WeightedGraph from vertices 0, 1, and edge array */
  public WeightedGraph(List<WeightedEdge> edges,
      int numberOfVertices) {
    List<V> vertices = new ArrayList<>();
    for (int i = 0; i < numberOfVertices; i++)
      vertices.add((V)(new Integer(i)));
    
    createWeightedGraph(vertices, edges);
  }

  /** Create adjacency lists from edge arrays */
  private void createWeightedGraph(List<V> vertices, int[][] edges) {
    this.vertices = vertices;     

    for (int i = 0; i < vertices.size(); i++) {
      neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
    }

    for (int i = 0; i < edges.length; i++) {
      neighbors.get(edges[i][0]).add(
        new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
    }
  }

  /** Create adjacency lists from edge lists */
  private void createWeightedGraph(
      List<V> vertices, List<WeightedEdge> edges) {
    this.vertices = vertices;     

    for (int i = 0; i < vertices.size(); i++) {
      neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
    }

    for (WeightedEdge edge: edges) {      
      neighbors.get(edge.u).add(edge); // Add an edge into the list
    }
  }

  /** Return the weight on the edge (u, v) */
  public double getWeight(int u, int v) throws Exception {
    for (Edge edge : neighbors.get(u)) {
      if (edge.v == v) {
        return ((WeightedEdge)edge).weight;
      }
    }
    
    throw new Exception("Edge does not exit");
  }
  
  /** Display edges with weights */
  public void printWeightedEdges() {
    for (int i = 0; i < getSize(); i++) {
      System.out.print(getVertex(i) + " (" + i + "): ");
      for (Edge edge : neighbors.get(i)) {
        System.out.print("(" + edge.u +
          ", " + edge.v + ", " + ((WeightedEdge)edge).weight + ") ");
      }
      System.out.println();
    }
  }
 
  /** Add an edge (u, v, weight) to the graph. */  
  public boolean addEdge(int u, int v, double weight) {
    return addEdge(new WeightedEdge(u, v, weight));
  }




  /** Find single source shortest paths */
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
    List<Integer> T = new ArrayList<>();
    
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
            && cost[e.v] > cost[u] + ((WeightedEdge)e).weight) {
          cost[e.v] = cost[u] + ((WeightedEdge)e).weight;
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


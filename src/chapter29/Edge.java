package chapter29;

public class Edge implements Comparable<Edge> {
  public int u;
  public int v;
  public double weight; // The weight on edge (u, v)

  /** Create a weighted edge on (u, v) */
  public Edge(int u, int v, double weight) {
    this.u=u;
    this.v=v;
    this.weight = weight;
  }

  /** Compare two edges on weights */
  public int compareTo(Edge edge) {
    if (weight > edge.weight) {
      return 1;
    }
    else if (weight == edge.weight) {
      return 0;
    }
    else {
      return -1;
    }
  }

  public boolean equals(Object o) {
    return u == ((Edge)o).u && v == ((Edge)o).v;
  }
}

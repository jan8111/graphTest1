package chapter28;

import chapter29.WeightedGraph;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GraphView extends Pane {
  private WeightedGraph<? extends Displayable> graph;


  public GraphView drawline2(ArrayList<Integer> path2) {
    int x1=0;
    int y1=0;
    for (Integer v : path2) {
      int x2 = graph.getVertex(v).getX();
      int y2 = graph.getVertex(v).getY();

      if(x1!=0){
        Line  line1= new Line(x1, y1, x2, y2);
        line1.setStroke(Color.RED);
        getChildren().add(line1);
      }
      x1= x2;
      y1= y2;
    }

    return this;
  }

  public GraphView(WeightedGraph<? extends Displayable> graph) {
  this.graph= graph;

    // Draw vertices
    java.util.List<? extends Displayable> vertices = graph.getVertices();
    for (int i = 0; i < graph.getSize(); i++) {
      int x = vertices.get(i).getX();
      int y = vertices.get(i).getY();
      String name = vertices.get(i).getName();
      
      getChildren().add(new Circle(x, y, 16)); // Display a vertex
      getChildren().add(new Text(x - 8, y - 18, name)); 
    }
    
    // Draw edges for pairs of vertices
    for (int i = 0; i < graph.getSize(); i++) {
      java.util.List<Integer> neighbors = graph.getNeighbors(i);
      int x1 = graph.getVertex(i).getX();
      int y1 = graph.getVertex(i).getY();
      for (int v: neighbors) {
        int x2 = graph.getVertex(v).getX();
        int y2 = graph.getVertex(v).getY();

        // Draw an edge for (i, v)
        getChildren().add(new Line(x1, y1, x2, y2));
      }
    }


  }
}

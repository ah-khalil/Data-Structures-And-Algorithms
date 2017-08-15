//This class is responsible for creating and managing the network - this includes the calls made to the other accessors to acquire
//vertex and edge information

import java.io.*;

public class Network implements Serializable
{
  private Vertex[] verArray;
  private Edge[] edgeArray;
  private int verCount = 0;
  private int edgeCount = 0;
  private String filename = null;

//DEFAULT CONSTRUCTOR
  public Network()
  {
    verArray = new Vertex[5];
    edgeArray = new Edge[5];
    verCount = 0;
    edgeCount = 0;
  }

  public Network(int vertexInSize, int edgeInSize)
  {
    verArray = new Vertex[vertexInSize];
    edgeArray = new Edge[edgeInSize];
  }

//This method places an edge object into the edge array and increments the count
  public void intoEdgeArray(Edge inEdge)
  {
    edgeArray[edgeCount] = inEdge;
    edgeCount++;
  }

//This method places a vertex into the vertex array and increments the count
  public void intoVertexArray(Vertex inVertex)
  {
    verArray[verCount] = inVertex;
    verCount++;
  }

  public Vertex getVertex(int element)
  {
    return verArray[element];
  }

  public Edge getEdge(int element)
  {
    return edgeArray[element];
  }

  public int getVerCount()
  {
    return verArray.length;
  }

  public int getEdgeCount()
  {
    return edgeArray.length;
  }
}

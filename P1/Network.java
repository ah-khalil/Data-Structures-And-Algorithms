//This class is responsible for creating and managing the network - this includes the calls made to the other accessors to acquire
//vertex and edge information

import java.io.*;

public class Network
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

//If there are vertex and edges in their respective arrays this method will run through them
//and output the component information stored in them
  public void getNetworkInfo()
  {
    String result;
    if(verCount > 0)
    {
      System.out.println("\nVERTICES STORED IN NETWORK VERTEX ARRAY");
      for(int i = 0; i < verCount; i++)
      {
        result = "\nDevice Type: " + verArray[i].getDeviceType() + "\nName: " + verArray[i].getName() + "\nReliability Float: " + verArray[i].getRelFloat() + "\nCost: " + verArray[i].getCost();
        System.out.println(result);
      }
    }
    else
    {
      System.out.println("There are no Vertices stored");
    }

    if(edgeCount > 0)
    {
      System.out.println("\nEDGES STORED IN NETWORK EDGE ARRAY");
      for(int i = 0; i < edgeCount; i++)
      {
        result = "\nVertex Info: " + edgeArray[i].getvertexInfo() + "\nReliability Float: " + edgeArray[i].getRelFloat() + "\nCost: " + edgeArray[i].getCost();
        System.out.println(result);
      }
    }
    else
    {
      System.out.println("There are no Edges stored");
    }
  }
}

//This class is responsible for creating and managing Edge objects - this includes the limits of the edge objects

import java.io.*;

public class Edge
{
  private Vertex vertexOne;
  private Vertex vertexTwo;
  private double relFloat;
  private double cost;

//ALTERNATIVE CONSTRUCTOR
  public Edge(Vertex inVertexOne, Vertex inVertexTwo, double inRelFloat, double inCost)
  {
    vertexOne = inVertexOne;
    vertexTwo = inVertexTwo;
    setRelFloat(inRelFloat);
    setCost(inCost);
  }

//GETTERS
  public String getvertexInfo()
  {
    String vertexInfo = "\nVertex One: " + vertexOne.toString() + "\n\nVertex Two: " + vertexTwo.toString();
    return vertexInfo;
  }

  public double getRelFloat()
  {
    return relFloat;
  }

  public double getCost()
  {
    return cost;
  }

//SETTERS
  private void setRelFloat(double inRelFloat)
  {
    if((inRelFloat < 0.0) || (inRelFloat > 1.0))
    {
      throw new IllegalArgumentException("Reliability Cannot be > 1 or < 0");
    }
    relFloat = inRelFloat;
  }

  private void setCost(double inCost)
  {
    if(inCost < 0.0)
    {
      throw new IllegalArgumentException("Cost cannot be less than zero");
    }
    inCost = cost;
  }
}

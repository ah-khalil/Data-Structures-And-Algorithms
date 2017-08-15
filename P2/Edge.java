//This class is responsible for creating and managing Edge objects - this includes the limits of the edge objects

import java.io.*;

public class Edge implements Serializable
{
  private Vertex vertexOne;
  private Vertex vertexTwo;
  private String name;
  private String type;
  private double relFloat;
  private double cost;

//ALTERNATIVE CONSTRUCTOR
  public Edge(Vertex inVertexOne, Vertex inVertexTwo, String inName, String inType, double inRelFloat, double inCost)
  {
    vertexOne = inVertexOne;
    vertexTwo = inVertexTwo;
    setName(inName);
    setType(inType);
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

  public String getType()
  {
    return type;
  }

  public String getName()
  {
    return name;
  }

//SETTERS
  private void setName(String inName)
  {
    if(inName.equals("") || inName == null)
    {
      throw new IllegalArgumentException("Must have a valid name");
    }
  }

  private void setType(String inType)
  {
    if(inType.equals(""))
    {
      throw new IllegalArgumentException("Must have a valid type");
    }
  }

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

  public String toString()
  {
    String str = "Vertex One: " + vertexOne.getName() + "\nVertex Two: " + vertexTwo.getName() + "\nEdge Name: " + getName() + "\nEdge Type: " + getRelFloat() + " \nEdge Reliability Float: " + relFloat + "\nEdge Cost: " + cost;
    return str;
  }
}

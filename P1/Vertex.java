//The class is responsible for creating and managing the Vertex Objects - this includes the limits of vertex variables

import java.io.*;

public class Vertex
{
  public static final char DEVICETYPE_RV325 = 'R';
  public static final char DEVICETYPE_2921K9 = '2';

  private char deviceType;
  private String name;
  private double cost;
  private double relFloat;

//ALTERNATIV CONSTRUCTOR
  public Vertex(char inDeviceType, String inName, double inCost, double inRelFloat)
  {
    if((inDeviceType != DEVICETYPE_RV325) && (inDeviceType != DEVICETYPE_2921K9))
    {
      throw new IllegalArgumentException("Invalid Device Type Provided");
    }

    setRelFloat(inRelFloat);
    setName(inName);
    setCost(inCost);
    deviceType = inDeviceType;
  }

//GETTERS
  public String getName()
  {
    return name;
  }

  public char getDeviceType()
  {
    return deviceType;
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

  private void setDeviceType(char inDeviceType)
  {
    if((inDeviceType != DEVICETYPE_RV325) && (inDeviceType != DEVICETYPE_2921K9))
    {
      throw new IllegalArgumentException("Invalid Device Type Provided");
    }
  }

  private void setCost(double inCost)
  {
    if(inCost < 0.0)
    {
      throw new IllegalArgumentException("Cost cannot be less than zero");
    }
    inCost = cost;
  }

  private void setName(String inName)
  {
    if((inName == null) || (inName.equals("")))
    {
      throw new IllegalArgumentException("Name cannot be null");
    }
    name = inName;
  }

//TOSTRING - FOR THE TEST HARNESS
  public String toString()
  {
    String outputString = "\nDevice Type: " + deviceType + "\nDevice Name: " + name + "\nDevice Cost: " + cost + "\nReliability Float: " + relFloat;
    return outputString;
  }
}

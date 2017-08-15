//This class is responsible for the file input and output of the network

import java.io.*;
import java.util.StringTokenizer;
import java.lang.String;

public class FileIO
{
  //This method counts the number or lines for both vertex lines and edge lines
  public void countLines(String filename) throws FileNotFoundException, IOException
  {
    String line = "";
    String token = "";
    int vertexLineCount = 0;
    int edgeLineCount = 0;
    FileInputStream inStream = new FileInputStream(filename);
    InputStreamReader strReader = new InputStreamReader(inStream);
    BufferedReader buffRdr = new BufferedReader(strReader);
    StringTokenizer strtok;

    try
    {
      while((line = buffRdr.readLine()) != null)
      {
        strtok = new StringTokenizer(line, " <>", false);
        token = strtok.nextToken();
        if(token.equals("VERTEX"))
        {
          vertexLineCount++;
        }
        else if(token.equals("EDGE"))
        {
          edgeLineCount++;
        }
      }
      System.out.println("VERTEX LINE COUNT: " + vertexLineCount + " EDGE LINE COUNT: " + edgeLineCount);
      readFileInfo(filename, vertexLineCount, edgeLineCount);
    }

    catch(FileNotFoundException fnfe)
    {
      System.out.println("The file was not found: " + fnfe);
    }

    catch(IOException ioe)
    {
      System.out.println("Failed or interrupted IO operation: " + ioe);
    }

    finally
    {
      inStream.close();
    }
  }

//This method segments the read-in file by line and, based on the first word of each line, calls the appropriate method
  public void readFileInfo(String filename, int vertexLineCount, int edgeLineCount) throws FileNotFoundException, IOException
  {
    String line = "";
    String token = "";
    Network network = new Network(vertexLineCount, edgeLineCount);
    FileInputStream inStream = new FileInputStream(filename);
    InputStreamReader strReader = new InputStreamReader(inStream);
    BufferedReader buffRdr = new BufferedReader(strReader);
    StringTokenizer strTok;

    try
    {
      while((line = buffRdr.readLine()) != null)
      {
        strTok = new StringTokenizer(line, " <>", false);
        token = strTok.nextToken();
        if(token.equals("VERTEX"))
        {
          network.intoVertexArray(this.createVertex(line));
        }
        else if(token.equals("EDGE"))
        {
          network.intoEdgeArray(this.createEdge(line, network));
        }
        else
        {}
      }
    }

    catch(FileNotFoundException fnfe)
    {
      System.out.println(fnfe + ": The file was not found");
    }

    catch(IOException ioe)
    {
      System.out.println(ioe + ": Failed or interrupted IO operation");
    }

    finally
    {
      buffRdr.close();
    }

  }

//This method is called to extract necessary information from the import 'line' and create a Vertex object from it
  private Vertex createVertex(String line)
  {
    String[] splString = line.split("<| |\"");
    String name = "";
    double relFloat = 0.0;
    double cost = 0.0;
    char deviceType = '\u0000';

    for(int i = 0; i < splString.length; i++)
    {
      if(splString[i].startsWith("VERTEX"))
      {
        relFloat = Double.parseDouble(splString[i + 1].substring(4));
      }
      else if(splString[i].equals("RV325"))
      {
        deviceType = 'R';
      }
      else if(splString[i].equals("2921/K9"))
      {
        deviceType = '2';
      }
      else if((splString[i].startsWith("COST")))
      {
        cost = Double.parseDouble(splString[i].substring(5));
      }
      else if((splString[i].startsWith("NAME")))
      {
        if(splString[i + 3].equals(""))
        {
          name = splString[i + 1].concat(" " + splString[i + 2]);
        }
        else
        {
          name = splString[i + 1];
        }
      }
    }
    Vertex vertex = new Vertex(deviceType, name, cost, relFloat);
    System.out.println(vertex.toString());

    return vertex;
  }

//This method is called to make an edge object out of the infomation in the 'line' string that was passed to it
  private Edge createEdge(String line, Network network)
  {
    String[] splString = line.split("<| |\"");
    Vertex vertexOne = null;
    Vertex vertexTwo = null;
    String verName = "";
    String name = "";
    String type = "";
    double relFloat = 0.0;
    double cost = 0.0;

    for(int i = 0; i < splString.length; i++)
    {
      System.out.println(splString[i]);
      if(splString[i].startsWith("FROM"))
      {
        if(splString[i + 3].equals(""))
        {
          verName = splString[i + 1].concat(" " + splString[i + 2]);
        }
        else
        {
          verName = splString[i + 1];
        }
        for(int j = 0; j < network.getVerCount(); j++)
        {
          if(network.getVertex(j).getName().equals(verName))
          {
            vertexOne = new Vertex(network.getVertex(j));
          }
        }
      }
      else if(splString[i].startsWith("TO"))
      {
        if(splString[i + 3].equals(""))
        {
          verName = splString[i + 1].concat(" " + splString[i + 2]);
        }
        else
        {
          verName = splString[i + 1];
        }
        for(int j = 0; j < network.getVerCount(); j++)
        {
          if(network.getVertex(j).getName().equals(verName))
          {
            vertexTwo = new Vertex(network.getVertex(j));
          }
        }
      }
      else if(splString[i].startsWith("COST"))
      {
        cost = Double.parseDouble(splString[i].substring(5, 7));
      }
      else if(splString[i].startsWith("NAME"))
      {
        name = splString[i + 1];
      }
      else if(splString[i].startsWith("REL"))
      {
        relFloat = Double.parseDouble(splString[i].substring(4));
      }
      else if(splString[i].startsWith("TYPE"))
      {
        type = splString[i + 1];
      }
    }
    Edge edge = new Edge(vertexOne, vertexTwo, name, type, relFloat, cost);
    System.out.println(edge.toString());

    return edge;
  }
}

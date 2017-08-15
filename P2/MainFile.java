import java.io.*;

public class MainFile
{
  public static void main(String [] args) throws FileNotFoundException, IOException
  {
    FileIO fileio = new FileIO();
    String filename = args[0]; //input file name as argument just before runtime
    fileio.countLines(filename);
  }
}

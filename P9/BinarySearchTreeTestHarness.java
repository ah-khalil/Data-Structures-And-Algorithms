import java.io.*;
import java.util.StringTokenizer;

public class BinarySearchTreeTestHarness
{
	public static void main(String[]args)
	{
		BinarySearchTree bst = null;
		FileReader fr = null;
		BufferedReader br = null;
		StringTokenizer str = null;
		String line = null;

    //Outer Try-Catch Statement to read in the file and place it in the treee
		try{
			bst = new BinarySearchTree();
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);

			while((line = br.readLine()) != null){
				str = new StringTokenizer(line, ",", false);
				while(str.hasMoreTokens()){
          Object key = str.nextToken();
          String val = str.nextToken();
					bst.insert(val, key);
				}
			}

			bst.printTree();

      //Inner try-catch statement #1: Testing Tree Height (This should not throw an Exception)
      try{
        System.out.println("\n\n======================================");
        System.out.println("Currently Testing Tree Height");
        int height = bst.height();
        if(height == 0)
          throw new UnexpectedResultException("(Inside Try-Catch Block 2) There should be some height to the tree");
        else
          System.out.println("Success: Height of the tree is " + height);
        System.out.println("======================================");
      }catch(Exception e){System.out.println("Inside Try-Catch Block 2: " + e);}

      //Inner try-catch statement #2: Testing Tree Node Deletion (This should not throw an Exception)
      try{
        System.out.println("\n\n======================================");
        System.out.println("Currently Testing Node Deletion");
        System.out.println("Number of nodes currently in the tree: " + bst.count());
        bst.delete("14397374");

        if(bst.count() != 6999)
          throw new UnexpectedResultException("(Inside Try-Catch Block 2) Should Have Deleted 14397374");
        else
          System.out.println("Success - The number of remaining nodes is: " + bst.count());
        System.out.println("======================================");
      }catch(Exception e){System.out.println("Inside Try-Catch Block 2: " + e);}

      //Inner try-catch statement #3: Testing Tree Finding (This should not throw an Exception)
      try{
        System.out.println("\n\n======================================");
        System.out.println("Currently Testing Node Finding");
        System.out.println("Finding \"Maricela Landreneau\"");
        Object nodeKey = bst.find("Maricela Landreneau");

        if(!nodeKey.toString().equals("Maricela Landreneau"))
          throw new UnexpectedResultException("(Inside Try-Catch Block 2) Should Have Found 14644633");
        else
          System.out.println("Success - The Key: " + nodeKey.toString());
        System.out.println("======================================");
      }catch(Exception e){System.out.println("Inside Try-Catch Block 2: " + e);}

    }catch(FileNotFoundException fnfe){System.out.println("Inside Try-Catch Block 1: " + fnfe);}
     catch(IOException ioe){System.out.println("Inside Try-Catch Block 1: " + ioe);}
     catch(Exception e){System.out.println("Inside Try-Catch Block 1: " + e);}
	}
}

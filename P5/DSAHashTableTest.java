import java.util.*;
import java.io.*;
import java.lang.*;

public class DSAHashTableTest
{
	public static void main(String[]args) throws IOException
	{
		BinarySearchTree tree = new BinarySearchTree();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringTokenizer strTok = null;
		String inKey = null;
		String line = null;
		String token = null;
		Object inValue = null;

		if(args.length < 1)
			throw new IOException("Have not entered an argument");

		try
		{
			fis = new FileInputStream(args[0]);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			while((line = br.readLine()) != null)
			{
				strTok = new StringTokenizer(line, ",", false);
				while(strTok.hasMoreTokens())
				{
					inKey = strTok.nextToken();
					inValue = strTok.nextToken();
					hashT.put(inKey, inValue);
				}
			}
		}

		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			fis.close();
		}
	}
}

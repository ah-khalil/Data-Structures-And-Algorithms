import java.util.*;
import java.io.*;
import java.lang.*;

public class SortingTest
{
	public static void main(String[]args) throws IOException
	{
		Sorts sorts = new Sorts();
		FileReader fr = null;
		BufferedReader br = null;
		StringTokenizer strTok = null;
		Scanner scan = new Scanner(System.in);
		int inKey = 0;
		int[] arr = null;
		String line = null;

		if(args.length < 1)
			throw new IOException("Have not entered an argument");

		try
		{
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);
			int i = 0;
			System.out.println("b = bubbleSort, s = selectionSort, i = insertionSort, m = mergeSort, q = quickSort");
			System.out.print("Enter Character for Sorting Method: ");
			char sortAl = scan.next().charAt(0);
			arr = new int[7000];

			while((line = br.readLine()) != null)
			{
				strTok = new StringTokenizer(line, ",", false);
				inKey = Integer.parseInt(strTok.nextToken());
				arr[i] = inKey;
				i++;
			}

			switch(sortAl)
			{
				case 'b': sorts.bubbleSort(arr);
									break;
				case 's': sorts.selectionSort(arr);
									break;
				case 'i': sorts.insertionSort(arr);
									break;
				case 'm': sorts.mergeSort(arr);
									break;
				case 'q': sorts.quickSort(arr);
									break;
				default:  System.out.println("Please choose from the above selection");
									break;
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
			fr.close();
		}
	}
}

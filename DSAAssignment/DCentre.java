import java.util.*;

public class DCentre
{
	private final int DEFAULT_CAP = 1024;
	private DataStructures[] dcarray;
	private int count = 0;

	public DCentre()
	{
		dcarray = new DataStructures[DEFAULT_CAP];
	}

	public DCentre(int inNum)
	{
		dcarray = new DataStructures[inNum];
	}

	public void placeInto(DataStructures inStructure)
	{
		if((count + 1) > dcarray.length)
		{
			System.out.println("FULL");
		}

		else
		{
			dcarray[count] = inStructure;
			count++;
		}
	}

	public void placeInto(DataStructures inStructure, int index)
	{
		if(index > dcarray.length - 1)
		{
			System.out.println("FULL");
		}

		else
		{
			dcarray[index] = inStructure;
			count++;
		}
	}

	public boolean isFull()
	{
		boolean full = false;
		if(count == dcarray.length)
		{
			full = true;
		}
		return full;
	}

	public DataStructures dcIndex(int index)
	{
		return dcarray[index];
	}

	public int getCount()
	{
		int b, count = 0;
		for(b = 0; b < dcarray.length; b++)
		{
			if(dcarray[b] != null)
			{
				count++;
			}
		}
		return count;
	}

	public int getLength()
	{
		return dcarray.length;
	}
}

import java.util.*;
import io.*;

public class DSAStack extends DataStructures
{
	private Carton stack[];
	private int count;
	private final int DEFAULT_CAPACITY = 100;

	public DSAStack()
	{
		stack = new Carton [DEFAULT_CAPACITY];
		count = 0;
	}

	public DSAStack(int inCap)
	{
		stack = new Carton [inCap];
		count = 0;
	}

	public int getCount()
	{
		return count;
	}

	public boolean isEmpty()
	{
		boolean tempEmpty;
		if (getCount() == 0)
		{
			tempEmpty = true;
		}
		else
		{
			tempEmpty = false;
		}
		return tempEmpty;
	}

	public boolean isFull()
	{
		boolean tempFull;
		if(getCount() == stack.length)
		{
			tempFull = true;
		}
		else
		{
			tempFull = false;
		}
		return tempFull;
	}

	public void push(Carton inCarton)
	{
		if(isFull() == true)
		{
			throw new ArrayIndexOutOfBoundsException("The Stack is Full");
		}
		else
		{
			stack[count] = inCarton;
			count++;
		}
	}

	public Carton pop()
	{
		Carton topVal = null;

		topVal = peek();
		count--;

		return topVal;
	}

	public Carton peek()
	{
		Carton topValue = null;

		if(isEmpty() == true)
		{
			throw new ArrayIndexOutOfBoundsException("The Array is Empty");
		}
		else
		{
			topValue = stack[count - 1];
		}

		return topValue;
	}

	public int getLength()
	{
		return stack.length;
	}

	public int getConsign(int index)
	{
		return stack[index].getConsign();
	}

	public String getName(int index)
	{

		return stack[index].getName();
	}

	public String getType(int index)
	{

		return stack[index].getType();
	}

	public String getDate(int index)
	{

		return stack[index].getDate();
	}

	public String getAll(int index)
	{
		String all = Integer.toString(getConsign(index)) + ":" + getDate(index) + ":" + getType(index) + ":" + getName(index);
		return all;
	}

	public double getNameHash(int index)
	{
		return stack[index].hashName();
	}

	public double getTypeHash(int index)
	{
		return stack[index].hashType();
	}

	public void dateSort()
	{
		int i = 0;
		int j = 0;
		int min = 0;
		int[] datesort = new int[3];
		int[] datecompare = new int[3];
		Carton temp = null;

		for(i = 0; i < getCount(); i++)
		{
			min = i;
			for(j = i + 1; j < getCount(); j++)
			{
				int m = 0;
				int t = 0;
				for(String dateone : (getDate(i)).split("-"))
				{
					datesort[m] = Integer.parseInt(dateone);
					m++;
				}
				for(String datetwo : (getDate(j)).split("-"))
				{
					datecompare[t] = Integer.parseInt(datetwo);
					t++;
				}
				if(datesort[0] > datecompare[0])
					min = j;
				else if(datesort[0] == datecompare[0])
				{
					if(datesort[1] > datecompare[1])
						min = j;
				}
				else if((datesort[1] == datecompare[1]) && (datesort[0] == datesort[0]))
				{
					if(datesort[2] > datecompare[2])
						min = j;
				}
			}
			if(min != i)
			{
				temp = stack[min];
				stack[min] = stack[i];
				stack[i] = temp;
			}
		}
	}

	public void consignSort()
	{
		int i = 0;
		int j = 0;
		Carton temp = null;
		int min = 0;

		for(i = 0; i < getCount(); i++)
		{
			min = i;
			for(j = i + 1; j < getCount(); j++)
			{
				if(getConsign(min) > getConsign(j))
				{
					min = j;
				}
			}
			if(min != i)
			{
				temp = stack[min];
				stack[min] = stack[i];
				stack[i] = temp;
			}
		}
	}

	public void typeSort()
	{
		int i = 0;
		int j = 0;
		Carton temp = null;
		int min = 0;

		for(i = 0; i < getCount(); i++)
		{
			min = i;
			for(j = i + 1; j < getCount(); j++)
			{
				if(getTypeHash(min) > getTypeHash(j))
				{
					min = j;
				}
			}
			if(min != i)
			{
				temp = stack[min];
				stack[min] = stack[i];
				stack[i] = temp;
			}
		}
	}

	public void nameSort()
	{
		int i = 0;
		int j = 0;
		Carton temp = null;
		int min = 0;

		for(i = 0; i < getCount(); i++)
		{
			min = i;
			for(j = i + 1; j < getCount(); j++)
			{
				if(getNameHash(min) > getNameHash(j))
				{
					min = j;
				}
			}
			if(min != i)
			{
				temp = stack[min];
				stack[min] = stack[i];
				stack[i] = temp;
			}
		}
	}


	public int binarySearchConsign(double inNum)
	{
		int matchIndx = -1;
		int lowb = 0;
		int upb = getCount();
		boolean found = false;

		while((!found) && (lowb < upb))
		{
			int mid = (lowb + upb)/2;

			if((double)stack[mid].getConsign() < inNum)
				lowb = mid + 1;
			else if((double)stack[mid].getConsign() > inNum)
				upb = mid - 1;
			else
			{
				matchIndx = mid;
				found = true;
			}
		}
		return matchIndx;
	}

	public int binarySearchDate(double inNum)
	{
		TaskFile tsk = new TaskFile();
		int[] datearray = new int[3];
		int matchIndx = -1;
		int lowb = 0;
		int upb = getCount();
		boolean found = false;
		while((!found) && (lowb < upb))
		{
			int t = 0;
			int mid = (lowb + upb)/2;

			for(String num : (stack[mid].getDate()).split("-")) //split the date string and place each element of the date into datearray
			{
				datearray[t] = Integer.parseInt(num);
				t++;
			}
			if(tsk.hashDate(datearray) < inNum)
				lowb = mid + 1;
			else if(tsk.hashDate(datearray) > inNum)
				upb = mid - 1;
			else
			{
				matchIndx = mid;
				found = true;
			}
		}
		return matchIndx;
	}


	public int binarySearchType(double inNum)
	{
		int matchIndx = -1;
		int lowb = 0;
		int upb = getCount();
		boolean found = false;
		while((!found) && (lowb < upb))
		{
			int mid = (lowb + upb)/2;
			if(stack[mid].hashType() < inNum)
				lowb = mid + 1;
			else if(stack[mid].hashType() > inNum)
				upb = mid - 1;
			else
			{
				matchIndx = mid;
				found = true;
			}
		}
		return matchIndx;
	}

	public int binarySearchName(double inNum)
	{
		int lowb = 0;
		int upb = getCount();
		int matchIndx = -1;
		boolean found = false;
		while((!found) && (lowb < upb))
		{
			int mid = (lowb + upb)/2;
			if(stack[mid].hashName() < inNum)
				lowb = mid + 1;
			else if(stack[mid].hashName() > inNum)
				upb = mid - 1;
			else
			{
				matchIndx = mid;
				found = true;
			}
		}
		return matchIndx;
	}
}

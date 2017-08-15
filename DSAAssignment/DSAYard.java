import java.io.*;
import java.util.*;
import io.*;

public class DSAYard extends DataStructures
{
	private int DEFAULT_CAPACITY = 200;
	private int count;
	private Carton[] generalarray;

	public DSAYard()
	{
		generalarray = new Carton[DEFAULT_CAPACITY];
		count = 0;
	}

	public DSAYard(int inYard)
	{
		generalarray = new Carton[inYard];
		count = 0;
	}

	public boolean isFull()
	{
		boolean temp = false;

		if(count == generalarray.length)
			temp = true;
		else
			temp = false;
		return temp;
	}

	public boolean isEmpty()
	{
		boolean temp = false;

		if(generalarray == null)
			temp = true;
		else
			temp = false;
		return temp;
	}

	public void insert(Carton inCarton)
	{
		if(isFull())
			throw new ArrayIndexOutOfBoundsException("The Yard is Full");
		generalarray[count] = inCarton;
		count++;
	}

	public Carton peek(int index)
	{
		Carton carton = generalarray[index];
		return carton;
	}

	public Carton eject(int index)
	{
		Carton carton = generalarray[index];
		generalarray[index] = null;
		count--;

		return carton;
	}

	public int getLength()
	{
			return generalarray.length;
	}

	public int getCount()
	{
		return count;
	}

	public int getConsign(int index)
	{
		return generalarray[index].getConsign(); //EDIT THIS LINE LATER ON
	}

	public String getName(int index)
	{

		return generalarray[index].getName();
	}

	public String getType(int index)
	{

		return generalarray[index].getType();
	}

	public String getDate(int index)
	{

		return generalarray[index].getDate();
	}

	public String getAll(int index)
	{
		String all = Integer.toString(getConsign(index)) + ":" + getDate(index) + ":" + getType(index) + ":" + getName(index);
		return all;
	}

	public double getNameHash(int index)
	{
		return generalarray[index].hashName();
	}

	public double getTypeHash(int index)
	{
		return generalarray[index].hashType();
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
				temp = generalarray[min];
				generalarray[min] = generalarray[i];
				generalarray[i] = temp;
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
				temp = generalarray[min];
				generalarray[min] = generalarray[i];
				generalarray[i] = temp;
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
				temp = generalarray[min];
				generalarray[min] = generalarray[i];
				generalarray[i] = temp;
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
				temp = generalarray[min];
				generalarray[min] = generalarray[i];
				generalarray[i] = temp;
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
			if((double)generalarray[mid].getConsign() < inNum)
				lowb = mid + 1;
			else if((double)generalarray[mid].getConsign() > inNum)
				upb = mid - 1;
			else
			{				matchIndx = mid;
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
			for(String num : (generalarray[mid].getDate()).split("-")) //split the date string and place each element of the date into datearray
			{				datearray[t] = Integer.parseInt(num);
				t++;
			}
			if(tsk.hashDate(datearray) < inNum)
				lowb = mid + 1;
			else if(tsk.hashDate(datearray) > inNum)
				upb = mid - 1;
			else
			{				matchIndx = mid;
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
			if(generalarray[mid].hashType() < inNum)
				lowb = mid + 1;
			else if(generalarray[mid].hashType() > inNum)
				upb = mid - 1;
			else
			{				matchIndx = mid;
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
			if(generalarray[mid].hashName() < inNum)
				lowb = mid + 1;
			else if(generalarray[mid].hashName() > inNum)
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

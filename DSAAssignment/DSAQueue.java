import java.util.*;

public class DSAQueue extends DataStructures
{
	private Carton queue[];
	private int count = 0;
	private int maxSize;
	private final int DEFAULT_CAPACITY = 100;

	public DSAQueue()
	{
		queue = new Carton[DEFAULT_CAPACITY];
		count = 0;
	}

	public DSAQueue(int inCapacity)
	{
		maxSize = inCapacity;
		queue = new Carton[maxSize];
		count = 0;
	}

	public void enqueue(Carton inCarton)
	{
		if(count == queue.length)
		{
			throw new ArrayIndexOutOfBoundsException("Queue Overflow");
		}
		else
		{
			queue[count] = inCarton;
			count++;
		}
	}

	public Carton dequeue()
	{
		Carton temp = null;

		if (isEmpty() == true)
		{
			throw new ArrayIndexOutOfBoundsException("The Array is Empty");
		}

		else
		{
			temp = queue[0];

			for(int i = 0; i < queue.length - 2; i++)
			{
				queue[i] = queue[i + 1];
			}
			count--;
		}
		return temp;
	}

	public int getCount()
	{
		return count;
	}

	public boolean isEmpty()
	{
		boolean temp = false;
		if(getCount() == 0)
		{
			temp = true;
		}
		else
		{
			temp = false;
		}
		return temp;
	}

	public boolean isFull()
	{
		boolean temp = false;

		if(getCount() == queue.length)
		{
			temp = true;
		}

		return temp;
	}

	public double getNameHash(int index)
	{
		return queue[index].hashName();
	}

	public double getTypeHash(int index)
	{
		return queue[index].hashType();
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
				temp = queue[min];
				queue[min] = queue[i];
				queue[i] = temp;
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
				temp = queue[min];
				queue[min] = queue[i];
				queue[i] = temp;
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
				temp = queue[min];
				queue[min] = queue[i];
				queue[i] = temp;
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
				temp = queue[min];
				queue[min] = queue[i];
				queue[i] = temp;
			}
		}
	}

	public int binarySearchConsign(double inNum)
	{
		int matchIndx = -1;
		int lowb = 0;
		int mid = 0;
		int upb = getCount();
		boolean found = false;

		while((!found) && (lowb < upb))
		{
			mid = (lowb + upb)/2;
			if((double)queue[mid].getConsign() < inNum)
				lowb = mid + 1;
			else if((double)queue[mid].getConsign() > inNum)
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
			for(String num : (queue[mid].getDate()).split("-")) //split the date string and place each element of the date into datearray
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
			if(queue[mid].hashType() < inNum)
				lowb = mid + 1;
			else if(queue[mid].hashType() > inNum)
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
			if(queue[mid].hashName() < inNum)
				lowb = mid + 1;
			else if(queue[mid].hashName() > inNum)
				upb = mid - 1;
			else
			{
				matchIndx = mid;
				found = true;
			}
		}
	return matchIndx;
	}

	public int getLength()
	{
			return queue.length;
	}

	public int getConsign(int index)
	{
		return queue[index].getConsign();
	}

	public String getName(int index)
	{

		return queue[index].getName();
	}

	public String getType(int index)
	{

		return queue[index].getType();
	}

	public String getDate(int index)
	{

		return queue[index].getDate();
	}

	public String getAll(int index)
	{
		String all = Integer.toString(getConsign(index)) + ":" + getDate(index) + ":" + getType(index) + ":" + getName(index);
		return all;
	}
}

import java.util.*;
import java.lang.Throwable.*;

public class DSAHashTable
{
	private final int MAX_STEP = 7;
	private DSAHashEntry[] hashTable;
	private DSAHashEntry[] oldTable;
	private int count;

//CONSTRUCTOR
	public DSAHashTable(int inTableSize)
	{
		hashTable = new DSAHashEntry[nextPrime(inTableSize)];
		count = 0;
	}

//places the key and value into a hash entry and calls the other put(DSAHashEntry)
	public void put(String inKey, Object inValue)
	{
		DSAHashEntry hshEnt = new DSAHashEntry(inKey, inValue);
		put(hshEnt);
	}

//places the hashEntry in the hashTable
//first checks to see if the table is reached the bounds
	public void put(DSAHashEntry inHashEntry)
	{
		int hashVal = 0;
		checkSize();

		hashVal = hash(inHashEntry.getKey());

		while(hashTable[hashVal] != null)
		{
			hashVal = (hashVal + stepHash(hashVal));
		}

		hashTable[hashVal] = inHashEntry;
		count++;
	}

//first checks to see if the key, and the object associated with it
//exists in the table, then checks the state, and then returns the
//object of the key
	public Object get(String inKey)
	{
		int hashIndex = hash(inKey);
		int origIndex = hashIndex;
		Object retValue = null;
		boolean found = false;
		boolean giveUp = false;

		while(!found && !giveUp)
		{
			if(hashTable[hashIndex].getState() == 0)
			{
				giveUp = true;
			}
			else if(hashTable[hashIndex].getKey() == inKey)
			{
				found = true;
			}
			else
			{
				hashIndex = (hashIndex + 1) % hashTable.length;
				if(hashIndex == origIndex)
				{
					giveUp = true;
				}
			}
		}

		if(!found)
		{
			throw new ElementNotFoundException("No corresponding element to begin with");
		}

		retValue = hashTable[hashIndex].getValue();
		return retValue;
	}

//increments the input number until it's a prime number
//returns the prime number
	public int nextPrime(int newSize)
	{
		do
		{
			newSize++;
		}while(isPrime(newSize) == false);

		return newSize;
	}

//checks the size of the table to see if it is above or below threshold
	public void checkSize()
	{
		if(abvThreshold())
		{
			reSize(nextPrime(hashTable.length * 2));
		}
		else if(belThreshold())
		{
			reSize(nextPrime(hashTable.length / 2));
		}
	}

//creates new hashTable from new prime number size, places old entries into new table
	private void reSize(int newSize)
	{
		int j = 0;
		oldTable = new DSAHashEntry[hashTable.length];

		for(int i = 0; i < hashTable.length; i++)
		{
			if(hashTable[i] != null)
			{
				DSAHashEntry entry = new DSAHashEntry(hashTable[i].getKey(), hashTable[i].getValue());
				oldTable[j] = entry;
				j++;
			}
		}

		hashTable = new DSAHashEntry[newSize];
		System.out.println("New Size is: " + hashTable.length);

		count = 0;
		for(int i = 0; i < j; i++)
		{
			put(oldTable[i].getKey(), oldTable[i].getValue());
		}
	}

//checks to see if the number is prime. Returns whether or not it is
	public boolean isPrime(int inNum)
	{
		boolean prime = false;
		if(inNum <= 1 && inNum % 2 == 0)
		{
			prime = false;
		}
		else
		{
			for(int i = 3; i*i <= inNum; i+=2)
			{
				if(inNum % i == 0)
				{
					prime = false;
				}
			}
			prime = true;
		}
		return prime;
	}

//the double hashing method. returns the step size for the given key
	public int stepHash(int inKey)
	{
		int stepHash = MAX_STEP - (inKey % MAX_STEP);
		System.out.println("Step Hash for, " + inKey + ", is: " + stepHash);
		return stepHash;
	}

//nullifies the hash entry index the key is associated with
//returns the object of that index
	public Object remove(String inKey)
	{
		Object val = get(inKey);
		hashTable[find(inKey)].wasUsed();
		hashTable[find(inKey)] = null;
		count--;

		if(belThreshold())
		{
			reSize(hashTable.length / 2);
		}

		return val;
	}

//finds the index that the key is associated with
//returns the index
	private int find(String inKey)
	{
		int index = hash(inKey);

		while(forUse(index) == false)
		{
			index = (index + stepHash(index)) % hashTable.length;
		}

		return index;
	}

//checks to see if the index of the hashTable is empty
//returns whether or not it is
	private boolean forUse(int index)
	{
		DSAHashEntry fVal = hashTable[index];
		boolean avail = false;

		if(fVal == null)
			avail = true;

		return avail;
	}

//checks to see if the hashEntry the key hashes to contains something
//(checks if it is removed or not)
	private boolean containKey(String inKey)
	{
		DSAHashEntry data = hashTable[find(inKey)];
		boolean doescontain = false;

		if(data == null)
		{
			doescontain = false;
		}
		else if(data.getState() == -1)
		{
			doescontain = true;
		}
		else
		{
			doescontain = true;
		}

		return doescontain;
	}

//the hash function, takes a key and produces an exclusive number out of it
//that corresponds to the hash table element slot number. Returns the hash index
//remainder against the hash table length
	private int hash(String inKey)
	{
		int hashIdx = 0;

		for (int ii = 0; ii < inKey.length(); ii++)
		{
			hashIdx = (33 * hashIdx) + inKey.charAt(ii);
		}
		System.out.println("The Hash Index for the Key, " + inKey + " is: " + hashIdx % hashTable.length);
		return hashIdx % hashTable.length;
	}

//returns whether or not the table is filled below 25%
	private boolean belThreshold()
	{
		return ((0.25 * hashTable.length) > (count/hashTable.length * 100));
	}

//returns whether or not the table is filled above 75%
	private boolean abvThreshold()
	{
		return ((0.75 * hashTable.length) < (count/hashTable.length * 100));
	}

//returns table size
	public int tableSize()
	{
		return hashTable.length;
	}
}

public class DSAQueue
{
	private Object queue[];
	private int count = 0;
	private int maxSize;
	private final int DEFAULT_CAPACITY = 100;

	/*DEFAULT CONSTRUCTOR: create an Object queue array with
	Default Capacity. Initialize count to 0;*/
	public DSAQueue()
	{
		queue = new Object[DEFAULT_CAPACITY];
		count = 0;
	}

	/*ALTERNATE CONSTRUCTOR: Uses import number as maxSize; initializes array
	with maxSize*/
	public DSAQueue(int inCapacity)
	{
		maxSize = inCapacity;
		queue = new Object[maxSize];
		count = 0;
	}

	/*Place the import Object into the Queue. If the Queue is full, throw an
	ArrayIndexOutOfBoundsException, stating Queue Overflow. If not
	place the Object in the next space available and get ready to place
	another one in*/
	public void enqueue(Object inVal)
	{
		if(count == queue.length)
		{
			throw new ArrayIndexOutOfBoundsException("Queue Overflow");
		}
		else
		{
			queue[count] = inVal;
			count++;
		}
	}

	/*Remove the First Object in the Queue. If the Queue is empty, throw
	and ArrayIndexOutOfBoundsException, stating The Array is Empty. If not
	place to-be-removed Object in variable 'str' and shuffle all elements in
	the queue forward by one. Returns 'str'.*/
	public Object dequeue()
	{
		Object str = null;
		if (isEmpty() == true)
		{
			throw new ArrayIndexOutOfBoundsException("The Array is Empty");
		}

		else
		{
			str = queue[0];
			for(int i = 0; i < queue.length - 1; i++)
			{
				queue[i] = queue[i + 1];
			}
			count--;
		}
		return str;
	}

	/*Returns amount of items in Queue*/
	public int getCount()
	{
		return count;
	}

	/*Checks if the Queue is Empty*/
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
}

import java.util.*;
import io.*;

public class DSAStack
{
	private Object stack[];
	private int count;
	private final int DEFAULT_CAPACITY = 100;

	/*DEFAULT CONSTRUCTOR: create an Object stack array with
	Default Capacity. Initialize count to 0;*/
	public DSAStack()
	{
		stack = new Object [DEFAULT_CAPACITY];
		count = 0;
	}

	/*ALTERNATE CONSTRUCTOR: Uses import number as size of array*/
	public DSAStack(int inCap)
	{
		stack = new Object [inCap];
		count = 0;
	}

	/*Returns amount of items in Stack*/
	public int getCount()
	{
		return this.count;
	}

	/*Checks if the Stack is Empty*/
	public boolean isEmpty()
	{
		return this.count == 0;
	}

	/*Checks if the Stack if Full*/
	public boolean isFull()
	{
		return this.count == stack.length;
	}

	/*Place the import Object into the Stack. If the Stack is full, throw an
	ArrayIndexOutOfBoundsException, stating The Stack is Full. If not
	place the Object in the next space available and get ready to place
	another one in*/
	public void push(Object inVal)
	{
		if(isFull() == true)
		{
			throw new ArrayIndexOutOfBoundsException("The Stack is Full");
		}

		stack[count] = inVal;
		count++;
	}

	/*Remove the Top Object in the Stack. Using peek() if the Stack
	is empty, throw an ArrayIndexOutOfBoundsException, stating
	The Array is Empty and if it isn't, peek() the top value out but
	decrement count to indicate it's been popped*/
	public Object pop()
	{
		Object topVal = peek();
		count--;

		return topVal;
	}

	/*Returns what's on the top of the Stack or, if empty, throws an
	ArrayIndexOutOfBoundsException, stating The Array is Empty*/
	public Object peek()
	{
		Object topValue = null;

		if(isEmpty() == true)
		{
			throw new ArrayIndexOutOfBoundsException("The Array is Empty");
		}
		return stack[count - 1];
	}
}

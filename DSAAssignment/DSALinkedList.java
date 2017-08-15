import io.*;

public class DSALinkedList
{
	private DSAListNode head;
	private DSAListNode tail;
	private DSAListNode current;
	private int count;

	//Default Constructor; no alternate
	public DSALinkedList()
	{
		head = null;
		tail = null;
		current = null;
		count = 0;
	}

	//A method for inserting nodes at the tail
	public void insert(Object inValue)
	{
		DSAListNode newnode = new DSAListNode(inValue);

		//same as in previous method
		if(isEmpty())
		{
			head = newnode;
			tail = newnode;
			current = newnode;
		}

		else
		{
			tail.setNext(newnode);
			tail = newnode;
		}

		count++;
	}

	//Method for removing head
	public Object remove()
	{
		Object nodeValue = null;

		nodeValue = head.getValue();
		head = head.getNext();

		count--;

		return nodeValue;
	}

	//Returns whether or not the list is empty
	public boolean isEmpty()
	{
		boolean temp = false;

		if(head == null)
			temp = true;

		return temp;
	}

	public Object moveOne()
	{
		Object value = null;

		value = current.getValue();
		current = current.getNext();

		return value;
	}

	public Object peek()
	{
		Object nodeValue = null;

		nodeValue = head.getValue();

		return nodeValue;
	}

	public void reset()
	{
		current = head;
	}

	public int getCount()
	{
		return count;
	}
}

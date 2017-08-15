import io.*;

public class DSAListNode
{
	public Object value;
	public DSAListNode next;
	public DSAListNode previous;

	//Alternate Constructor; the node should have value
	public DSAListNode(Object inValue)
	{
		value = inValue;
		next = null;
		previous = null;
	}

	//GETTER METHODS

	//returns the value of the node
	public Object getValue()
	{
		return value;
	}

	//returns the node that is next
	public DSAListNode getNext()
	{
		return next;
	}

	//returns the node that is previous (the 'doubly' part of doubly-linked list)
	public DSAListNode getPrevious()
	{
		return previous;
	}

	//SETTER METHODS

	//sets the value of the node
	public void setValue(Object inValue)
	{
		value = inValue;
	}

	//sets what the next node will be
	public void setNext(DSAListNode inNext)
	{
		next = inNext;
	}

	//sets what the previous node will be
	public void setPrevious(DSAListNode inPrevious)
	{
		previous = inPrevious;
	}
}
import io.*;

public class DSAListNode<E>
{
	public E value;
	public DSAListNode<E> next;
	public DSAListNode<E> previous;

	//Alternate Constructor; the node should have value
	public DSAListNode(E inValue)
	{
		value = inValue;
		next = null;
		previous = null;
	}

	//GETTER METHODS

	//returns the value of the node
	public E getValue()
	{
		return value;
	}

	//returns the node that is next
	public DSAListNode<E> getNext()
	{
		return next;
	}

	//returns the node that is previous (the 'doubly' part of doubly-linked list)
	public DSAListNode<E> getPrevious()
	{
		return previous;
	}

	//SETTER METHODS

	//sets the value of the node
	public void setValue(E inValue)
	{
		value = inValue;
	}

	//sets what the next node will be
	public void setNext(DSAListNode<E> inNext)
	{
		next = inNext;
	}

	//sets what the previous node will be
	public void setPrevious(DSAListNode<E> inPrevious)
	{
		previous = inPrevious;
	}
}

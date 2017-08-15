import java.util.*;

public class DSALinkedList<E> implements Iterable<E>
{
	private DSAListNode<E> head;
	private DSAListNode<E> tail;
	private int count;

	public Iterator<E> iterator()
	{
		return new DSALinkedListIterator<E>(this);
	}

	private class DSALinkedListIterator<E> implements Iterator<E>
	{
		private DSAListNode<E> iterNext;

		public DSALinkedListIterator(DSALinkedList<E> list)
		{
			iterNext = list.head;
		}

		public boolean hasNext()
		{
			return (iterNext != null);
		}

		public E next()
		{
			E value;
			if(iterNext == null)
			{
				value = null;
			}
			else
			{
				value = iterNext.getValue();
				iterNext = iterNext.getNext();
			}

			return value;
		}

		public void remove()
		{
			throw new UnsupportedOperationException("That Operation is not Supported");
		}
	}
	//Default Constructor; no alternate
	public DSALinkedList()
	{
		head = null;
		tail = null;
		count = 0;
	}

	//Method for inserting at the head
	public void insertFirst(E inValue)
	{
		DSAListNode<E> newnode = new DSAListNode<E>(inValue);
		/*If the list is empty make the aforementioned node the tail and head,
		and set the pointers in both positions to be null; there isn't anything there*/
		if(isEmpty())
		{
			head = newnode;
			tail = newnode;
			head.setNext(null);
			tail.setPrevious(null);
		}

		/*If there is something in the list make the newnode have the previous
		head as its neighbour node. Then set head as newnode.*/
		else
		{
			newnode.setNext(head);
			head = newnode;
		}
		count++;
	}

	//A method for inserting nodes at the tail
	public void insertLast(E inValue)
	{
		DSAListNode<E> newnode = new DSAListNode<E>(inValue);

		//same as in previous method
		if(isEmpty())
		{
			head = newnode;
			tail = newnode;
		}

		/*If there is something in the list make the newnode have the previous tail
		as its neighbour and set the tail as newnode*/
		else
		{
			newnode.setPrevious(tail);
			tail = newnode;
		}
		count++;
	}

	//Returns whether or not the list is empty
	public boolean isEmpty()
	{
		return (head == null) && (tail == null);
	}

	//Method for peeking--without removing--the head of the list
	public E peekFirst()
	{
		E nodeValue = null;

		//if it isn't empty return the value of head
		if(!isEmpty())
			nodeValue = head.getValue();

		return nodeValue;
	}

	//Same as above but for the tail
	public E peekLast()
	{
		E nodeValue = null;

		//if it isn't empty return the value of tail
		if(!isEmpty())
			nodeValue = tail.getValue();

		return nodeValue;
	}

	//Method for removing head
	public E removeFirst()
	{
		E nodeValue = null;

		/*if list isn't empty set nodeValue as the value of head before
		removing the node by setting head pointer to the next node*/
		if(!isEmpty())
		{
			nodeValue = head.getValue();
			head = head.getNext();
			count--;
		}

		return nodeValue;
	}

	//Method for removing tail
	public E removeLast()
	{
		E nodeValue = null;

		/*if list isn't empty set nodeValue as the value of tail before
		removing the node by setting tail pointer to the previous node*/
		if(!isEmpty())
		{
			nodeValue = tail.getValue();
			tail = tail.getPrevious();
			count--;
		}

		return nodeValue;
	}

	public int getCount()
	{
		return count;
	}
}

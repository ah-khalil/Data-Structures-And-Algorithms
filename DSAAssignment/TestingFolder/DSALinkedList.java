public class DSALinkedList
{
  private DSAListNode head;
  private DSAListNode tail;
  private int count;

  public DSALinkedList()
  {
    head = null;
    tail = null;
    count = 0;
  }

  public boolean isEmpty()
  {
    boolean temp = false;
    if(head == null && tail == null)
    {
      temp = true;
    }

    return temp;
  }

  public void insertFirst(int inValue)
  {
    DSAListNode node = new DSAListNode(inValue);

    if(isEmpty())
    {
      head = node;
      tail = node;
    }

    else
    {
      node.setNext(head);
      head = node;
    }
    count++;
  }

  public void insertLast(int inValue)
  {
    DSAListNode node = new DSAListNode(inValue);

    if(isEmpty())
    {
      head = node;
      tail = node;
    }

    else
    {
      node.setPrevious(tail);
      tail = node;
    }
    count++;
  }

  public int deleteFirst() throws Exception
  {
    int temp = head.getValue();
    if(isEmpty())
    {
      throw new Exception("The Linked List is Empty!");
    }

    else
    {
      head = head.getNext();
      head = null;
    }
    count--;

    return temp;
  }

  public DSAListNode deleteLast() throws Exception
  {
    DSAListNode temp = tail;
    if(isEmpty())
    {
      throw new Exception("The Linked List is Empty");
    }

    else
    {
      tail = tail.getPrevious();
      tail = null;
    }
    count--;

    return temp;
  }

    public int getCount()
    {
      return count;
    }

}

public class DSAListNode
{
  private int value;
  private DSAListNode next;
  private DSAListNode previous;

  public DSAListNode(int inValue)
  {
    value = inValue;
    next = null;
    previous = null;
  }

  public void setValue(int inValue)
  {
    value = inValue;
  }

  public void setNext(DSAListNode inNext)
  {
    next = inNext;
  }

  public void setPrevious(DSAListNode inPrevious)
  {
    previous = inPrevious;
  }

  public DSAListNode getNext()
  {
    return next;
  }

  public DSAListNode getPrevious()
  {
    return previous;
  }

  public int getValue()
  {
    return value;
  }
}

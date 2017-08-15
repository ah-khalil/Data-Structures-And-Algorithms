public class DSAHeap
{
  private class DSAHeapEntry
  {
    public int priority;
    public Object value;
    public DSAHeapEntry(int inPriority, Object inValue)
    {

    }
  }

  private DSAHeapEntry[] m_heap;
  private int m_count;

  public DSAHeap(int maxSize)
  {
    m_heap = new DSAHeapEntry[maxSize];
  }
  public void add(int priority, Object value)
  {

  }
  public Object remove()
  {

  }
  public void heapSort(Object [] list)
  {

  }
  //trickleUp and trickleDown MUST be recursive
  private void trickleUp(int index)
  {

  }
  private void trickleDown(int index)
  {

  }
}

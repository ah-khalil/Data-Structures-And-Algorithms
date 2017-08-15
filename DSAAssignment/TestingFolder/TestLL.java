public class TestLL
{
  public static void main(String [] args) throws Exception
  {
    DSALinkedList link = new DSALinkedList();

    for(int i = 0; i < 5; i++)
    {
      link.insertLast(i);
    }

    for(int ii = 0; ii < 5; ii++)
    {
      System.out.println("Number " + ii + ": " + link.deleteFirst());
      System.out.println("Count: " + link.getCount());
    }
  }
}

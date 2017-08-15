import java.util.*;

public class UnitTestDSALinkedList
{
  public static void main(String[]args)
  {
    Integer num = null;
    DSALinkedList list = null;

    System.out.println("\n====Testing Inserting and Removing To and From Head====\n");
    try
    {
      list = new DSALinkedList();
      for(int i = 0; i < 21; i++)
      {
        num = new Integer(i);
        list.insertFirst(num);
      }

      System.out.println("Number of Elements in the List: " + list.getCount() + "\n");
      System.out.println("Element at the Front of the List: " + list.peekFirst() + "\n");

      for(int i = 0; i < list.getCount(); i++)
      {
        System.out.println(i + "th Element Removed From List Head: " + list.removeFirst());
      }
    }
    catch(Exception e)
    {
      System.out.println("This failed: " + e);
    }

    System.out.println("\n====Testing Inserting and Removing To and From Tail====\n");
    try
    {
      list = new DSALinkedList();
      for(int i = 0; i < 21; i++)
      {
        num = new Integer(i);
        list.insertLast(num);
      }

      System.out.println("Number of Elements in the List: " + list.getCount() + "\n");
      System.out.println("Element at the Back of the List: " + list.peekLast() + "\n");

      for(int i = 0; i < list.getCount(); i++)
      {
        System.out.println(i + "th Element Removed From List Head: " + list.removeLast());
      }
    }
    catch(Exception e)
    {
      System.out.println("This failed: " + e);
    }
  }
}

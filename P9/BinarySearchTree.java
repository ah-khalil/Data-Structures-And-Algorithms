import java.util.*;

/*
Binary Search Tree Class - Ali Khalil, 1/06/2016
*/

//Outer class for the tree functions
public class BinarySearchTree
{
  //Inner class for the nodes that make up the tree
  private class TreeNode
  {
    private String key;
    private Object value;
    private TreeNode leftchild;
    private TreeNode rightchild;

    //ALTERNATE CONSTRUCTOR
    public TreeNode(String inKey, Object inValue)
    {
      if(inKey == null)
        throw new IllegalArgumentException("Key Cannot Be Null");

      key = inKey;
      value = inValue;
      rightchild = null;
      leftchild = null;
    }

    //toString method for the node key
    public String toString()
    {
      return key.toString();
    }

    //set what will be the left child of this node
    public void setLeft(TreeNode inLeft)
    {
      leftchild = inLeft;
    }

    //set what will be the right child of this node
    public void setRight(TreeNode inRight)
    {
      rightchild = inRight;
    }

    //Get the left child of this node
    public TreeNode getLeft()
    {
      return leftchild;
    }

    //Get the right child of this node
    public TreeNode getRight()
    {
      return rightchild;
    }

    //Get the key of this node
    public String getKey()
    {
      return key;
    }

    //Get the value of this node
    public Object getValue()
    {
      return value;
    }
  }

  private TreeNode root;
  private int count;

    //DEFAULT CONSTRUCTOR
    public BinarySearchTree()
    {
      root = null;
      count = 0;
    }

    //This method kicks off the recursie find function using the import key
    //Returns the value of the node with the matching key
    public Object find(String inKey)
    {
      return findRecursive(inKey, root);
    }

    //This method is recursively called for every node that isn't the node being
    //looked for
    //Starts with the root node and branches it's way down the tree
    //Returns the value of the node being looked for
    private Object findRecursive(String inKey, TreeNode currNode)
    {
      Object val = null;

      if(currNode == null)
        throw new NoSuchElementException("Key " + inKey + " was not found");

      else if(inKey.equals(currNode.getKey()))
        val = currNode.getKey();

      else if(inKey.compareTo(currNode.getKey()) < 0)
        val = findRecursive(inKey, currNode.getLeft());

      else
        val = findRecursive(inKey, currNode.getRight());

      return val;
    }

    //This method kick-starts the insert recursion method
    //Count increments to indicate that one more node was added
    public void insert(String inKey, Object inValue)
    {
      root = insert(inKey, inValue, root);
      count++;
    }

    //This method is recursively called until pointer points to a leaf node
    //Assigns node to be inserted as leaf node of the previous node
    //Returns the TreeNode just inserted
    public TreeNode insert(String inKey, Object inValue, TreeNode curr)
    {
      TreeNode updateNode = curr;

      if(curr == null)
      {
        TreeNode newnode = new TreeNode(inKey, inValue);
        updateNode = newnode;
      }

      else if(inKey.equals(curr.getKey()))
      {}

      else if(inKey.compareTo(curr.getKey()) < 0)
        curr.setLeft(insert(inKey, inValue, curr.getLeft()));

      else
        curr.setRight(insert(inKey, inValue, curr.getRight()));

      return updateNode;
    }

    //This method kick-starts the delete recursion method
    //Decrements count to indicate a deleted node
    public void delete(String inKey)
    {
      root = delete(inKey, root);
      count--;
    }

    //This method makes the comparisons with the key and the node being pointed at
    //Returns node that takes it's place as parents to deleted node's children
    public TreeNode delete(String inKey, TreeNode curr)
    {
      TreeNode updateNode = curr;
      if(curr == null)
      {}

      else if(inKey.equals(curr.getKey()))
        updateNode = deleteNode(inKey, curr);

      else if(inKey.compareTo(curr.getKey()) < 0)
        curr.setLeft(delete(inKey, curr.getLeft()));

      else
        curr.setRight(delete(inKey, curr.getRight()));

      return updateNode;
    }

    //This method is recursively called until the node with the import key is found
    //Returns node that takes it's place as parents to deleted node's children
    private TreeNode deleteNode(String inKey, TreeNode delNode)
    {
      TreeNode updateNode = null;

      if((delNode.getLeft() == null) && (delNode.getRight() == null))
        updateNode = null;

      else if((delNode.getLeft() != null) && (delNode.getRight() == null))
        updateNode = delNode.getLeft();

      else if((delNode.getLeft() == null) && (delNode.getRight() == null))
        updateNode = delNode.getRight();

      else
      {
        updateNode = promoteSuccessor(delNode.getRight());

        if(updateNode != delNode.getRight())
          updateNode.setRight(delNode.getRight());

        updateNode.setLeft(delNode.getLeft());
      }

      return updateNode;
    }


    //This method determines the successor to the deleted node
    //Returns the successor
    private TreeNode promoteSuccessor(TreeNode curr)
    {
      TreeNode successor = null;

      if(curr.getLeft() == null)
        successor = curr;

      else
      {
        if(curr.getLeft() != null)
        {
          successor = promoteSuccessor(curr.getLeft());
          if(successor == curr.getLeft())
            curr.setLeft(successor.getRight());
        }
      }

      return successor;
    }

    //This method returns the number of nodes in the tree
    public int count()
    {
      return count;
    }

    //This method kick-starts the height recursive method
    //Returns the height of the tree
    public int height()
    {
      return heightRec(root);
    }

    //This method is recursively called until it traverses the whole tree
    //Counts how low hanging the tree is
    //Returns the height of the tree
    public int heightRec(TreeNode currNode)
    {
      int total = 0;
      int leftheight = 0;
      int rightheight = 0;

      if(currNode == null)
        total = -1;

      else
      {
        leftheight = heightRec(currNode.getLeft());
        rightheight = heightRec(currNode.getRight());

        if(leftheight > rightheight)
          total = leftheight + 1;
        else
          total = rightheight + 1;
      }

      return total;
    }

    //This method kick-starts that tree-printing recursive method
    public void printTree()
    {
      printSubTree(root, "");
    }

    //This method is recursively called until the tree is fully traversed
    //For every node, it prints out the key
    public void printSubTree(TreeNode root, String indent)
    {
      if(root != null)
      {
        if(root.getLeft() != null)
        {
          printSubTree(root.getLeft(), indent + "      ");
          System.out.println(indent + "     /");
        }

        System.out.println(indent + root.toString());

        if(root.getRight() != null)
        {
          System.out.println(indent + "     \\");
          printSubTree(root.getRight(), indent + "      ");
        }
      }
    }
}

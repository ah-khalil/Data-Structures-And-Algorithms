/**
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
  /*Outputs everything in the import array to console*/
  public static void outputToConsole(int[] A)
  {
    for(int i = 0; i < A.length - 1; i++)
    {
      System.out.println(A[i]);
    }
  }
  // bubble sort
  public static void bubbleSort(int[] A)
  {
    int pass = 0;
    int temp = 0;
    boolean sorted = false;
    //While the array is not sorted...
    do
    {
      sorted = true;
      //for every entry in the array minus however many passes it's made...
      for(int ii = 0; ii < (A.length - 1 - pass); ii++)
      {
        //if the current element is larger that the next one...
        if(A[ii] > A[ii+1])
        {
          //swap the two elements
          temp = A[ii];
          A[ii] = A[ii+1];
          A[ii+1] = temp;
          sorted = false;
        }
      }
      pass++;
    } while(!sorted);

    outputToConsole(A);
  }

  // selection sort
  public static void selectionSort(int[] A)
  {
    int ii = 0;
    int minPos = 0;
    //for every element in the array...
    for(ii = 0; ii < A.length - 1; ii++)
    {
      minPos = ii;
      //for every element one element ahead of element to be checked...
      for(int j = ii + 1; j < A.length; j++)
      {
        //comparing elements determine which is smaller
        if(A[j] < A[minPos])
          minPos = j;
      }
      //swap A[ii] with the one that is smaller
      int temp = A[minPos];
      A[minPos] = A[ii];
      A[ii] = temp;
    }

    outputToConsole(A);
  }

  // insertion sort
  public static void insertionSort(int[] A)
  {
    int ii = 0;
    int temp = 0;
    //for every element in the array...
    for(int nn = 1; nn < A.length; nn++)
    {
      temp = A[nn];
      ii = nn;
       //while ii is above the temp element...
       while((ii > 0) && (A[ii - 1] > temp))
      {
        //shuffle backwards until ii is less than temp element
        A[ii] = A[ii - 1];
        ii = ii - 1;
      }

      A[ii] = temp;
    }

    outputToConsole(A);
  }

  // mergeSort - front-end for kick-starting the recursive algorithm
  public static void mergeSort(int[] A)
  {
    mergeSortRecurse(A, 0, A.length - 1);
    outputToConsole(A);
  }
  //recursion method to divide range of array
  private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
  {
    if(leftIdx < rightIdx)
    {
      int midIdx = (leftIdx + rightIdx) / 2;
      mergeSortRecurse(A, leftIdx, midIdx);
      mergeSortRecurse(A, midIdx + 1, rightIdx);
      merge(A, leftIdx, midIdx, rightIdx);
    }
  }
  //makes element comparisons and places numbers back into A[] array
  private static int[] merge(int[] A, int leftIdx, int midIdx, int rightIdx)
  {
    int[] tempArr = new int[A.length];

    //place every element in A[] within leftIdx and rightIdx into tempArr
    for (int i = leftIdx; i <= rightIdx; i++)
    {
      tempArr[i] = A[i];
    }

    int i = leftIdx;
    int j = midIdx + 1;
    int k = leftIdx;

    /*Check both sides of the array to see which one is smaller
    then shove the smaller value back into main array (A[])*/
    while (i <= midIdx && j <= rightIdx)
    {
      if (tempArr[i] <= tempArr[j])
      {
        A[k] = tempArr[i];
        i++;
      }
      else
      {
        A[k] = tempArr[j];
        j++;
      }
      k++;
    }

    //Copy whatever else is in tempArr and place it in A[]
    while (i <= midIdx)
    {
      A[k] = tempArr[i];
      k++;
      i++;
    }

    return A;
  }

  // quickSort - front-end for kick-starting the recursive algorithm
  public static void quickSort(int[] A)
  {
    quickSortRecurse(A, 0, A.length - 1);
    outputToConsole(A);
  }
  private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
  {
    int pivotIdx = 0;
    int newPivotIndex = 0;
    if(rightIdx > leftIdx)
    {
      pivotIdx = (leftIdx + rightIdx) / 2;
      newPivotIndex = doPartitioning(A, leftIdx, rightIdx, pivotIdx);

      quickSortRecurse(A, leftIdx, newPivotIndex - 1);
      quickSortRecurse(A, newPivotIndex + 1, rightIdx);

    }
  }
  private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
  {
    int currIdx = 0;
    int temp = 0;
    int newPivotIndex = 0;

    int pivotVal = A[pivotIdx];
    A[pivotIdx] = A[rightIdx];
    A[rightIdx] = pivotVal;

    currIdx= leftIdx;

    for(int ii = leftIdx; ii < rightIdx - 1; ii++)
    {
      if(A[ii] < pivotVal)
      {
        temp = A[ii];
        A[ii] = A[currIdx];
        A[currIdx] = temp;
        currIdx = currIdx + 1;
      }
    }

    newPivotIndex = currIdx;
    A[rightIdx] = A[newPivotIndex];
    A[newPivotIndex] = pivotVal;

    return newPivotIndex;
  }//doPartitioning


}//end Sorts class

public class QueueTestHarness
{
	public static void main(String [] args)
	{
		int n = 10;
		DSAQueue myQueue = new DSAQueue(10);

		/*This try-block shows basic enqueueing and dequeueing of the Queue*/
		try
		{
			myQueue.enqueue("1");
			myQueue.enqueue("2");
			myQueue.enqueue("3");
			System.out.println("\nExpected Output: 1");
			System.out.println("'" + myQueue.dequeue() + "'"); //Expected Output: '1'
			myQueue.enqueue("4");
			myQueue.enqueue("5");
			System.out.println("\nExpected Output: 2");
			System.out.println("'" + myQueue.dequeue() + "'"); //Expected Output: '2'
			System.out.println("\nExpected Output: 3");
			System.out.println("'" + myQueue.dequeue() + "'"); //Expected Output: '3'
			System.out.println("\nExpected Output: 4");
			System.out.println("'" + myQueue.dequeue() + "'"); //Expected Output: '4'
			System.out.println("\nExpected Output: 5");
			System.out.println("'" + myQueue.dequeue() + "'"); //Expected Output: '5'
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		/*This try-catch-block shows the expected Exceptions to be thrown in case of
		an Array Off-Bounds Dequeueing*/
		try
		{
			System.out.println("\n>>>>ENQUEUEING ITEMS INTO QUEUE<<<<\n");

			for(int i = 0; i < n; i++)
			{
				System.out.println("Item Being Inserted Into Queue: " + "'" + i + "'");
				myQueue.enqueue(i);
			}

			System.out.println("\n>>>>DEQUEUEING ITEMS OUT OF QUEUE<<<<\n");

			for(int i = 0; i < n; i++)
			{
				System.out.print(myQueue.dequeue() + "\n");
			}

			System.out.println("\nTHE NEXT DEQUEUE SHOULD TRIGGER AN Array Index Out Of Bounds Exception -> The Queue is Empty!\n");
			myQueue.dequeue();
		}

		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			aioobe.printStackTrace();
		}


		/*This try-catch-block shows the expected Exceptions to be thrown in case of
		an Array Off-Bounds Enqueueing*/
		try
		{
			System.out.println("\n>>>>ENQUEUEING ITEMS INTO QUEUE<<<<\n");

			for(int i = 0; i < n; i++)
			{
				System.out.println("Item Being Inserted Into Queue: " + "'" + i + "'");
				myQueue.enqueue(i);
			}

			System.out.println("\nTHE NEXT ENQUEUE SHOULD TRIGGER AN Array Index Out Of Bounds Exception -> Queue Overflow!\n");
			myQueue.enqueue("100");
		}

		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			aioobe.printStackTrace();
		}
	}
}

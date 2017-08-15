public class StackTestHarness
{
	public static void main(String [] args)
	{
		int n = 10;
		DSAStack myStack = new DSAStack(n);


		/*This try-catch-block shows the expected Exceptions to be thrown in case of
		an Array Off-Bounds Popping*/
		try
		{
			for(int i = 0; i < n; i++)
			{
				myStack.push(i);
			}

			System.out.println("\n>>>>POPPING ITEMS OUT OF STACK<<<<\n");

			for(int i = 0; i < n; i++)
			{
				System.out.println(myStack.pop());
			}

			System.out.println("\nTHE NEXT POP SHOULD TRIGGER AN Array Index Out Of Bounds Exception -> The Stack is Empty!\n");
			myStack.pop();
		}

		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			aiobe.printStackTrace();
		}

		/*This try-catch-block shows the expected Exceptions to be thrown in case of
		an Array Off-Bounds Pushing*/
		try
		{
			for(int i = 0; i < n; i++)
			{
				myStack.push(i);
			}

			System.out.println("\nTHE NEXT PUSH SHOULD TRIGGER AN Array Index Out Of Bounds Exception -> The Stack is Full!\n");

			myStack.push("100");
		}

		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			aiobe.printStackTrace();
		}
	}
}

//This is the main() class that is used to test the Vertex, Edge and Network classes.
//Everything after "SUCCESS" should be an exception as they have variables that are
//above or below the limits imposed in the aforementioned classes

import java.io.*;

public class VertexTestHarness
{

	public static void main(String[]args)
	{
		char deviceType;
		String name;
		double vertexcost, edgecost;
		double vertexrelFloat, edgerelfloat;
		Vertex vertexOne;
		Vertex vertexTwo;
		Network networkOne;

		//Everything in this try block should work because they are within the specified limits
		try
		{
			deviceType = 'R';
			name = "DELL";
			vertexcost = 50.00;
			vertexrelFloat = 0.8;
			vertexOne = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			System.out.println("\nINFO FROM VERTEX ONE: " + vertexOne.toString());

			deviceType = '2';
			name = "HP";
			vertexcost = 30.00;
			vertexrelFloat = 0.4;
			vertexTwo = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			System.out.println("\nINFO FROM VERTEX TWO: " + vertexTwo.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//Everything in the subsequent try blocks should not work because they are either above or below the limits specified
		//Each try block has a reason for failure and that is commented
		try
		{
			deviceType = 'X'; //invalid device type - either 'R' or '2'
			name = "DELL";
			vertexcost = 50.00;
			vertexrelFloat = 0.8;
			vertexOne = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			vertexOne.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			deviceType = 'R';
			name = ""; //invalid device name - should not be blank
			vertexcost = 50.00;
			vertexrelFloat = 0.8;
			vertexOne = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			vertexOne.toString();
		}

		catch(Exception e)
		{
 			e.printStackTrace();
		}

		try
		{
			deviceType = 'R';
			name = "DELL";
			vertexcost = -0.10; //invalid cost - cannot be less than zero
			vertexrelFloat = 0.8;
			vertexOne = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			vertexOne.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			deviceType = 'R';
			name = "DELL";
			vertexcost = 50.00;
			vertexrelFloat = -0.1; //invalid Reliability float - cannot be less than 0.0
			vertexOne = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			vertexOne.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			deviceType = 'R';
			name = "DELL";
			vertexcost = 50.00;
			vertexrelFloat = 1.2; //invalid Reliability float - cannot be greater than 1.0
			vertexOne = new Vertex(deviceType, name, vertexcost, vertexrelFloat);
			vertexOne.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

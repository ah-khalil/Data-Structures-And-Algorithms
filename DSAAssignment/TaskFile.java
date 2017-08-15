import java.util.*;
import java.io.*;
//This class is responsible for figuring out the task from the input file and following through with the task
public class TaskFile
{
	//This method figures out the task by splitting the input file
	public String[] findTask(String filename) throws FileNotFoundException, IOException
	{
		BufferedReader bfrdr = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		String[] csvelements = (bfrdr.readLine()).split(":"); //split the information in the single line in the file and place it into an array
		bfrdr.close();

		return csvelements;
	}

	//This method kickstarts the entire carton-adding feature
	public int[] add(String[] csvelements)
	{
		String date = csvelements[2] + "-" + csvelements[3] + "-" + csvelements[4]; //The date is the 3rd, 4th and 5th element combined in the array
		int[] datearray = new int[3];
		int t = 0;

		for(String num : date.split("-")) //split the date string and place each element of the date into datearray
		{
			datearray[t] = Integer.parseInt(num);
			t++;
		}
		return datearray;
	}

	//This method hashes a number from the date, particularly the year
	public double hashDate(int[] datearray)
	{
		char[] chyear = null;
		int sdate = 0;
		int tyear = 0;
		double hash = 0.0;

		if(datearray[0] == 0 && datearray[1] == 0 && datearray[2] == 0) //If the Carton has unlimited warranty then assign hash the number 200.0
			hash = 200.00;
		else //if the carton doesn't have unlimited warranty...
		{
			chyear = (String.valueOf(datearray[0])).toCharArray(); //...seperate each number in Year in an array...
			sdate = datearray[0] + datearray[1] + datearray[2]; //...calculate the sum of the numbers of the date...
			tyear = ((chyear[0] - '0') * 1111) + ((chyear[1] - '0') * 111 * 2) +
							((chyear[2] - '0') * 11 * 2) + ((chyear[3] - '0') * 1 * 2); //...with each number in chyear multiply certain constants...
			hash = 	((double)sdate/(double)tyear) * 100.00; //...and then divide the sdate and tyear, after typecasting them to double, and then multiply by 100
		}
		return hash;
	}

	//This method hashes a number out of a string
	public double hashTN(String inString)
	{
		double htn = 0.0;
		for(int i = 0; i < inString.length(); i++) //Moving through the length of the string
		{
			htn = (31 * htn) + inString.charAt(i) + i*33; //Create a hash number from each letter of the string
		}

		return htn; //return hash number
	}

	//This method is responsible for placing the carton into a Stack
	public void addToStack(double inHash, Carton inCarton, DCentre centre)
	{
		int i = 0;
		int indx = 0;
		DSALinkedList link = new DSALinkedList();
		DSAStack dead;

		while(!(centre.dcIndex(i) instanceof DSAStack) && i < centre.getCount()) //Find a stack without stepping over the centre limits
		{
			i++;
		}
		while(centre.dcIndex(i).getCount() != 0) //Remove everything from the current stock room and place it in a Linked List
		{
			link.insert(((DSAStack)centre.dcIndex(i)).pop());
		}
		link.insert(inCarton); //Place the import carton into the linked list

		dead = new DSAStack((centre.dcIndex(i).getLength()) + 1); //initalize a new stack with one more room

		while(link.getCount() != 0) //place all of the cartons from the linked list to the new stack
		{
			dead.push((Carton)link.remove());
		}
		centre.placeInto(dead, i); //place this new stack into the position in the centre where the original stack was

		for(indx = 0; indx < dead.getCount(); indx++) //Move through the new stack. When the carton in the index is the same as the import carton, print the indexes and break from the for loop
		{
			if(((DSAStack)centre.dcIndex(i)).getAll(indx) == inCarton.getAll())
			{
				System.out.println(i + ":" + indx);
				break;
			}
		}
	}

	//This method is responsible for placing the carton into a Queue
	public void addToQueue(double inHash, Carton inCarton, DCentre centre)
	{
		int i = 0;
		int indx = 0;
		DSALinkedList link = new DSALinkedList();
		DSAQueue roll;

		while(!(centre.dcIndex(i) instanceof DSAQueue) && i < centre.getCount()) //Find a queue without stepping over the centre limits
		{
			i++;
		}
		while(centre.dcIndex(i).getCount() != 0) //Remove everything from the current stock room and place it in a Linked List
		{
			link.insert(((DSAQueue)centre.dcIndex(i)).dequeue());
		}
		link.insert(inCarton); //Place the import carton into the linked list

		roll = new DSAQueue((centre.dcIndex(i).getLength()) + 1); //initalize a new queue with one more room

		while(link.getCount() != 0) //place all of the cartons from the linked list to the new queue
		{
			roll.enqueue((Carton)link.remove());
		}
		centre.placeInto(roll, i); //place this new queue into the position in the centre where the original queue was

		for(indx = 0; indx < roll.getCount(); indx++) //Move through the new stack. When the carton in the index is the same as the import carton, print the indexes and break from the for loop
		{
			if(((DSAQueue)centre.dcIndex(i)).getAll(indx) == inCarton.getAll())
			{
				System.out.println(i + ":" + indx);
				break;
			}
		}
	}

	//This method is responsible for placing the carton into a Yard. It is basically the same as above but with a yard, not a queue or a stack
	public void addToYard(double inHash, Carton inCarton, DCentre centre)
	{
		int i = 0;
		int j = 0;
		int indx = 0;
		DSALinkedList link = new DSALinkedList();
		DSAYard yard;

		while(!(centre.dcIndex(i) instanceof DSAYard) && i < centre.getCount())
		{
			i++;
		}
		while(centre.dcIndex(i).getCount() != 0)
		{
			link.insert(((DSAYard)centre.dcIndex(i)).eject(j));
			j++;
		}
		link.insert(inCarton);

		yard = new DSAYard((centre.dcIndex(i).getLength()) + 1);

		while(link.getCount() != 0)
		{
			yard.insert((Carton)link.remove());
		}
		centre.placeInto(yard, i);

		for(indx = 0; indx < yard.getCount(); indx++) //Move through the new stack. When the carton in the index is the same as the import carton, print the indexes and break from the for loop
		{
			if(((DSAYard)centre.dcIndex(i)).getAll(indx) == inCarton.getAll())
			{
				System.out.println(i + ":" + indx);
				break;
			}
		}
	}

	//This method removes a carton from the centre
	public void shipCarton()
	{

	}

	//This method searches for the specified carton given by the conditions array
	public void searchCentre(String[] conditions, DCentre centre)
	{
		DSALinkedList list = new DSALinkedList();
		DSALinkedList indexList = new DSALinkedList();
		int[] datearray = new int[3];
		int[] conditionarray = new int[3];
		int idx = 0;

		for(int i = 0; i < centre.getCount(); i++) //for the length of the centre...
		{
			if(!conditions[1].equals("")) //if the first element is not empty
			{
				centre.dcIndex(i).consignSort(); //sort the current index of the centre by consignment sort
				if(centre.dcIndex(i) instanceof DSAStack)
					idx = (((DSAStack)centre.dcIndex(i)).binarySearchConsign(Double.parseDouble(conditions[1]))); //search for the same consign in ith stock room using binary search
				else if(centre.dcIndex(i) instanceof DSAQueue)
					idx = (((DSAQueue)centre.dcIndex(i)).binarySearchConsign(Double.parseDouble(conditions[1])));
				else if(centre.dcIndex(i) instanceof DSAYard)
					idx = (((DSAYard)centre.dcIndex(i)).binarySearchConsign(Double.parseDouble(conditions[1])));

				if(idx != -1) //if something is actually found
				{
					list.insert(centre.dcIndex(i).getAll(idx)); //place the carton info in the linked list
					indexList.insert(i + ":" + idx); //place the stock room and centre index in another linked list
				}
			}

			if(!conditions[2].equals(""))
			{
				int q = 0;
				for(String num : (conditions[2]).split("-")) //split the date string and place each element of the date into conditionarray
				{
					conditionarray[q] = Integer.parseInt(num);
					q++;
				}
				centre.dcIndex(i).dateSort(); //sort the current index of the centre by date
				if(centre.dcIndex(i) instanceof DSAStack)
					idx = (((DSAStack)centre.dcIndex(i)).binarySearchDate(hashDate(conditionarray))); //search for the same hashed number from the date in ith stock room using binary search
				else if(centre.dcIndex(i) instanceof DSAQueue)
					idx = (((DSAQueue)centre.dcIndex(i)).binarySearchDate(hashDate(conditionarray)));
				else if(centre.dcIndex(i) instanceof DSAYard)
					idx = (((DSAYard)centre.dcIndex(i)).binarySearchDate(hashDate(conditionarray)));

				if(idx != -1)
				{
					for(int b = 0; b < idx; b++)
					{
						list.insert(centre.dcIndex(i).getAll(b));
						indexList.insert(i + ":" + b);
					}
				}
			}

			if(!conditions[3].equals(""))
			{
				centre.dcIndex(i).typeSort(); //sort the current index of the centre by type
				if(centre.dcIndex(i) instanceof DSAStack)
					idx = (((DSAStack)centre.dcIndex(i)).binarySearchType(hashTN(conditions[3]))); //search for the same hashed number from the type in ith stock room using binary search
				else if(centre.dcIndex(i) instanceof DSAQueue)
					idx = (((DSAQueue)centre.dcIndex(i)).binarySearchType(hashTN(conditions[3])));
				else if(centre.dcIndex(i) instanceof DSAYard)
					idx = (((DSAYard)centre.dcIndex(i)).binarySearchType(hashTN(conditions[3])));

				if(idx != -1)
				{
					list.insert(centre.dcIndex(i).getAll(idx));
					indexList.insert(i + ":" + idx);
				}
			}

			if(!conditions[4].equals(""))
			{
				centre.dcIndex(i).typeSort(); //sort the current index of the centre by name
				if(centre.dcIndex(i) instanceof DSAStack)
					idx = (((DSAStack)centre.dcIndex(i)).binarySearchName(hashTN(conditions[4]))); //search for the same hashed number from the name in ith stock room using binary search
				else if(centre.dcIndex(i) instanceof DSAQueue)
					idx = (((DSAQueue)centre.dcIndex(i)).binarySearchName(hashTN(conditions[4])));
				else if(centre.dcIndex(i) instanceof DSAYard)
					idx = (((DSAYard)centre.dcIndex(i)).binarySearchName(hashTN(conditions[4])));

				if(idx != -1)
				{
					list.insert(centre.dcIndex(i).getAll(idx));
					indexList.insert(i + ":" + idx);
				}
			}
		}

		if(conditions[0].charAt(0) == 'S') //if the first element is 'S'
		{
			outputSearch(list, indexList, conditions); //call outputSearch, giving it the two linked lists and the condition array
		}
	}

	//This method outputs the location and the details of the carton searched for
	public void outputSearch(DSALinkedList list, DSALinkedList indexList, String[] conditions)
	{
		double hres = 0.00;
		double hsrchcon = 0.00;
		int iter = 0;

		hsrchcon = (hashTN(conditions[1] + ":" + conditions[2] + ":" + conditions[3] + ":" + conditions[4])); //make a string out of every element in the condition other than the first and hash them

		if(list.getCount() > 0) //if there is something in the linked list
		{
			for(iter = 0; iter < list.getCount(); iter++) //then run through the linked list, hash the carton information of the current list node
			{
				String cartoninfo = (String)list.moveOne();
				hres = hashTN(cartoninfo);
				if(hres == hsrchcon) //if the hashed carton info is the same as the hash of the combined conditions string
				{
					System.out.println(indexList.remove() + ":" + cartoninfo); //print out the info
				}
			}
		}

		else
		{
			System.out.println("NOT FOUND"); //if it's not found, say so
		}
	}

	//This method writes the current details of the centre to a file
	public void writeCentre(DCentre centre, String filename) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename + "-output"))); //the file name of the resultant file will be the name of the file plus "-output"

		for(int i = 0; i < centre.getCount(); i++) //run through the centre
		{
			if(centre.dcIndex(i) instanceof DSAQueue) //every time a DSAQueue is found, write "R:"
			{
				writer.write("R:");
			}
			else if(centre.dcIndex(i) instanceof DSAStack)  //every time a DSAStack is found, write "D:"
			{
				writer.write("D:");
			}
			else if(centre.dcIndex(i) instanceof DSAYard)  //every time a DSAYard is found, write "Y:"
			{
				writer.write("Y:");
			}

			for(int j = 0; j < centre.dcIndex(i).getCount(); j++) //for each element within the ith stock room
			{
				writer.write(Integer.toString(centre.dcIndex(i).getConsign(j))); //write the consignment note of that carton
				writer.write(":"); //write a ":"
			}
			writer.write("\n"); //when that loop is over, write a new line
		}

		writer.write("%"); //when the entire forloop is over, insert "%"
		writer.write("\n"); //write the newline character

		for(int i = 0; i < centre.getCount(); i++) //for each element in the centre
		{
			for(int j = 0; j < centre.dcIndex(i).getCount(); j++) //for each carton within the ith stock room
			{
				writer.write((centre.dcIndex(i).getAll(j))); //write all the details of that carton
				writer.write("\n"); //write a new line
			}
		}
		writer.close(); //close the stream
	}

}

import java.util.*;
import java.io.*;

public class DCFile //This class reads the Distribution Centre File and creates cartons, structures and centres based on it's design
{
	private int geosize = 0;
	public DCentre centre;
	private DSAStack dead;
	private DSAQueue roll;
	private DSAYard yard;
	//This function takes the input file from main(), reads the lines and places them in a linked list.
	//It also catches the exceptions generated from I/O
	public DSALinkedList inputFile(String filename) throws IOException
	{
		DSALinkedList link = new DSALinkedList();
		FileInputStream in;
		InputStreamReader read;
		BufferedReader buff = null;
		int lineCount = 0;
		String line;

		try
		{
			in = new FileInputStream(filename);
			read = new InputStreamReader(in);
			buff = new BufferedReader(read);

			while((line = buff.readLine()) != null) //while the buffer hasn't reached the end of file...
			{
				if((line.length() == 0) || (line.charAt(0) == '#')) //...if the lines doesn't begin with '#' or isn't blank...
				{} //...do nothing

				else
				{
					link.insert(line); //if there is something there that isn't a '#' then place it into the linked list
				}
			}
		}

		catch(IOException e) //catch an input output exception
		{
			e.printStackTrace(); //print the error
		}

		buff.close();

		return link;
	}

	//This function counts the number of DC Geometry Section lines there are
	public int count(DSALinkedList link)
	{
		String line = null;
		int count = 0;

		for(int i = 0; i < link.getCount(); i++) //from the beginning and all the way to the end of the linked list...
		{
			line = (String)link.moveOne(); //move the list node pointer over to the next one available (note that the first time this is called it'll point to the first node)
			if(line.charAt(0) == 'D' || line.charAt(0) == 'R' || line.charAt(0) == 'Y') //...if the line begins with 'D', 'R' or 'Y'
				count++; //...increment count
		}
		geosize = count; //the size of the centre is based on geosize; if the centre is
										 //comprised of structures and the amount of structures is represented
										 //by the amount of D-R-Y letters, then centre's size equals geosize
		link.reset(); //place pointer back at beginning of linked list

		return count;
	}

	//This function splits the geo and description sections of the linked list into their very own arrays
	public void split(DSALinkedList link, int count)
	{
		String[] arraygeo = new String[count];
		String[] arraydesc = new String[link.getCount() - count]; //link.getCount() - count is equivalent to 'total amount of lines - number of lines in the geo section'
		String descline = null;

		for(int i = 0; i < count; i++) //from the beginning of the geo section to the end...
		{
			arraygeo[i] = (String)link.moveOne(); //...place all of the geo section lines of the linked list into arraygeo
		}

		descline = (String)link.moveOne(); //to skip the '%'

		for(int y = 0; y < (link.getCount() - count) - 1; y++) //from beginning of desc section to the end...
    {
			descline = (String)link.moveOne(); //...initalize descline to be the next line of the desc section
			arraydesc[y] = descline; //set yth element of arraydesc to be descline
		}

		link.reset(); //set linked list pointer back to head

		makeCarton(arraygeo, arraydesc);
	}

	//This function makes and places the cartons based on the input file while also checking that
	//consignment notes in the description section are present in the geometry section
	private void makeCarton(String[] arraygeo, String[] arraydesc)
	{
		String[] descarray = new String[4];
		String[] geoarray = null;
		Carton carton = null;
		boolean isSame = false;
		int k = 1;

		for(int i = 0; i < arraygeo.length; i++) //this forloop moves through each line in the desc section
		{
			isSame = false;
			geoarray = arraygeo[i].split(":"); //split ith line of desc section by the delimiter ":" and assign descarray spots the result
			isNull();
			//if first element of ith row of geo section is one of these letters, create appropriate structure
			switch(geoarray[0].charAt(0))
			{
				case 'D': dead = new DSAStack(geoarray.length + 1);
									centre.placeInto(dead);
									break;
				case 'Y': yard = new DSAYard(geoarray.length + 1);
									centre.placeInto(yard);
									break;
				case 'R': roll = new DSAQueue(geoarray.length + 1);
									centre.placeInto(roll);
									break;
			}
			k = 1;
			while(k < geoarray.length) //for each row of geo section sift through the consign numbers for comparison
			{
				if(geoarray[k].equals("")) //if kth element is blank, skip to next consign note
				{
					k++;
				}
				for(int j = 0; j < arraydesc.length - 1; j++) //from the first desc section row to the last
				{
					descarray = new String[(arraydesc[j].split(":")).length];  //jth row is split and initalized using the split result array's length
					descarray = arraydesc[j].split(":");
					if(Integer.parseInt(geoarray[k]) == Integer.parseInt(descarray[0])) //if the int form of the kth element of geo section is equal to the first
					{																																		//element of the jth row of desc section...
						k++; //...move to next geo consign note...
						isSame = true; //...signify that a match has been made...
						carton = new Carton(Integer.parseInt(descarray[0]), descarray[1], descarray[2], descarray[3]); //initalize a carton with the jth row of desc section details
						if(geoarray[0].charAt(0) == 'D')
							dead.push(carton);
						else if(geoarray[0].charAt(0) == 'Y')
							yard.insert(carton);
						else if(geoarray[0].charAt(0) == 'R')
							roll.enqueue(carton);
						break; //break from for loop to compare using next consign note
					}
				}
				if(isSame != true) //if none of the consign notes in jth line of geo section match the target note in the desc section...
				{
					System.out.println("\n***A CONSIGNMENT NOTE IS NOT PRESENT IN BOTH SECTIONS***\n"); //...print this out...
					System.exit(0); //...and exit the program
				}
			}
		}
	}

//This function just determines whether or not the Distribution Centre, centre, was initalized and initalize it if it wasn't
	private void isNull()
	{
		if(centre == null)
		{
			centre = new DCentre(geosize);
		}
	}
}

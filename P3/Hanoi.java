import io.*;
public class Hanoi
{
	//this method is responsible for moving the disks and recursively calling itself
	public void moveDisk(int disks, String start, String auxilary, String end, String spaces)
	{
		if(disks == 1) //base case: if there is one disk in the start peg...
		{
			System.out.println("(8)" + spaces + "moveDisk(" + disks + ", " + start + ", " + auxilary + ", " + end + ")"); //...print out the function parameters, space indents and line number
			spaces = spaces + "  "; //...increment space by two spaces
			System.out.println(spaces + "n = " + disks + " start = " + start + " end = " + end); //...print out disk number, departing peg and arriving peg
			System.out.println("(11)" + spaces + "Disk 1 moves from " + start + "---->" + end); //...print out disk one's movements and the line number
		}

		else //if there are more than one disk...
		{
			System.out.println("(19)" + spaces + "moveDisk(" + disks + ", " + start + ", " + auxilary + ", " + end + ")"); //...print out the function parameters, space indents and line number
			System.out.println(spaces + "n = " + disks + " start = " + start + " end = " + end + " auxilary = " + auxilary); //...print disk number, departing peg, arriving peg and auxilary peg
			spaces = spaces + "  "; //...increment space by two spaces
			moveDisk(disks - 1, start, end, auxilary, spaces); //...recursive call: not auxilary is in 'end' position because that is the peg the disk will arrive to. Also, there is one less disk
			System.out.println("(21)" + spaces + "Disk " + disks + " moves from " + start + "---->" + end);  //...print out the disks movement and the line number
			moveDisk(disks - 1, auxilary, start, end, spaces); //...recursively call again to move disk from auxilary to end peg
		}
	}
}

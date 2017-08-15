import io.*;
public class Towers
{
	public static void main (String [] args)
	{
		int disks = ConsoleInput.readInt("How Many Disks"); //How mant disks do you want to use?
		Hanoi hanoi = new Hanoi();
		hanoi.moveDisk(disks, "A", "B", "C", "");
	}
}

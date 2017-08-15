import java.lang.ArrayIndexOutOfBoundsException;

public class DSAAssignment
{
	public static void main(String [] args) throws Exception
	{
		TaskFile task = new TaskFile();
		DSAAssignment dsaa = new DSAAssignment();
		DCFile dcfile = new DCFile();
		DSALinkedList ll = dcfile.inputFile(args[0]);
		String[] str = null;
		Carton carton = null;
		double hash = 0.00;
		int count = dcfile.count(ll);

		dcfile.split(ll, count);
		dsaa.sortDate(dcfile.centre);

		str = task.findTask(args[1]);

		if(str[0].charAt(0) == 'A')
		{
			if(str.length != 7)
			{
				throw new ArrayIndexOutOfBoundsException("Must Have Each Element of Task File Separated by Colons (':')");
			}
			carton = new Carton(Integer.parseInt(str[1]), str[2] + "-" + str[3] + "-" + str[4], str[5], str[6]);
			hash = task.hashDate(task.add(str));
			if(hash > 90.0 && hash < 95.0)
			{
				task.addToStack(hash, carton, dcfile.centre);
			}
			else if(hash >= 95.0)
			{
				task.addToQueue(hash, carton, dcfile.centre);
			}
			else
			{
				task.addToYard(hash, carton, dcfile.centre);
			}
			task.writeCentre(dcfile.centre, args[0]);
		}

		else if(str[0].charAt(0) == 'S')
		{
				task.searchCentre(str, dcfile.centre);
		}
	}

	public void sortType(DCentre centre)
	{
		for(int b = 0; b < centre.getCount(); b++)
		{
			if(centre.dcIndex(b) instanceof DSAStack)
				((DSAStack)centre.dcIndex(b)).typeSort();
			else if(centre.dcIndex(b) instanceof DSAQueue)
				((DSAQueue)centre.dcIndex(b)).typeSort();
			else if(centre.dcIndex(b) instanceof DSAYard)
				((DSAYard)centre.dcIndex(b)).typeSort();
		}
	}

	public void sortName(DCentre centre)
	{
		for(int b = 0; b < centre.getCount(); b++)
		{
			if(centre.dcIndex(b) instanceof DSAStack)
				((DSAStack)centre.dcIndex(b)).nameSort();
			else if(centre.dcIndex(b) instanceof DSAQueue)
				((DSAQueue)centre.dcIndex(b)).nameSort();
			else if(centre.dcIndex(b) instanceof DSAYard)
				((DSAYard)centre.dcIndex(b)).nameSort();
		}
	}

	public void sortDate(DCentre centre)
	{
		for(int b = 0; b < centre.getCount(); b++)
		{
			if(centre.dcIndex(b) instanceof DSAStack)
				((DSAStack)centre.dcIndex(b)).dateSort();
			else if(centre.dcIndex(b) instanceof DSAQueue)
				((DSAQueue)centre.dcIndex(b)).dateSort();
			else if(centre.dcIndex(b) instanceof DSAYard)
				((DSAYard)centre.dcIndex(b)).dateSort();
		}
	}

	public void sortConsign(DCentre centre)
	{
		for(int b = 0; b < centre.getCount(); b++)
		{
			if(centre.dcIndex(b) instanceof DSAStack)
				((DSAStack)centre.dcIndex(b)).consignSort();
			else if(centre.dcIndex(b) instanceof DSAQueue)
				((DSAQueue)centre.dcIndex(b)).consignSort();
			else if(centre.dcIndex(b) instanceof DSAYard)
				((DSAYard)centre.dcIndex(b)).consignSort();
		}
	}
}

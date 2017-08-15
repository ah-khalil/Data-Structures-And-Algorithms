import java.io.*;

public class Carton
{
	private int conote;
	private String type;
	private String name;
	private String date;

	public Carton() //DEFAULT CONSTRUCTOR
	{
		conote = 0000;
		type = "";
		name = "";
		date = "0000-00-00";
	}

	public Carton(int inConote, String inDate, String inType, String inName) //ALTERNATE CONSTRUCTOR
	{
		if(confirmConote(inConote))
		{
			conote = inConote;
		}

		type = inType;
		name = inName;

		date = inDate;
	}

	private boolean confirmConote(int inConote)
	{
		boolean confirm = false;

		if(inConote < 1024)
		{
			confirm = true;
		}

		return confirm;
	}

	public int getConsign()
	{
		return conote;
	}

	public String getDate()
	{
		return date;
	}

	public String getType()
	{
		return type;
	}

	public String getName()
	{
		return name;
	}

	public String getAll()
	{
		String all = Integer.toString(getConsign()) + " " + getDate() + " " + getType() + " " + getName();
		return all;
	}

	public double hashType() //hashes the type of the carton
	{
		double htype = 0.0;
		for(int i = 0; i < type.length(); i++)
		{
			htype = (31 * htype) + type.charAt(i) + i*33;
		}
		return htype;
	}

	public double hashName() //hashes the name of the carton
	{
		double hname = 0.0;
		for(int i = 0; i < name.length(); i++)
		{
			hname = (31 * hname) + name.charAt(i) + i*33;
		}
		return hname;
	}
}

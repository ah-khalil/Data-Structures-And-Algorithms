import java.util.*;

public class DSAHashEntry
{
	private String key;
	private Object value;
	private int state;

	public DSAHashEntry()
	{
		key = "";
		value = null;
		state = 0;
	}

	public DSAHashEntry(String inKey, Object inValue)
	{
		key = inKey;
		value = inValue;
		state = 1;
	}

	public String getKey()
	{
		return key;
	}

	public Object getValue()
	{
		return value;
	}

	public int getState()
	{
		return state;
	}

	public void wasUsed()
	{
		state = -1;
	}
}

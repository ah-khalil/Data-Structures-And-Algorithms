import java.io.*;
import java.util.*;

//THIS ABSTRACT CLASS WAS MADE SO THAT AND OBJECT ARRAY IS NOT NEEDED. IT MAKES ACCESSING THE METHODS OF THE ADTs A LOT EASIER
//AS THEY ALL SHARE THE METHODS THAT ARE CONTAINED HERE
public abstract class DataStructures
{
	public abstract int binarySearchConsign(double inNum); //returns index of the matching number (when compared with a consign note) within a sorted array, found through binary search

	public abstract int binarySearchDate(double inNum); //returns index of the matching number (when compared with a date) within a sorted array, found through binary search

	public abstract int binarySearchType(double inNum); //returns index of the matching number (when compared with a type) within a sorted array, found through binary search

	public abstract int binarySearchName(double inNum); //returns index of the matching number (when compared with a name) within a sorted array, found through binary search

	public abstract double getNameHash(int index); //gets the hash of the type of the name

	public abstract double getTypeHash(int index); //gets the hash of the type of the carton

	public abstract void typeSort(); //uses selection sort to sort the cartons by the hash of the string type

	public abstract void nameSort(); //uses selection sort to sort the cartons by the hash of the string name

	public abstract void dateSort(); //uses selection sort to sort the cartons by date

	public abstract void consignSort(); //uses selection sort to sort the cartons yb consign

	public abstract int getLength(); //gets the length of the array in the data structure

	public abstract int getCount(); //gets how much of the stock room is filled

	public abstract boolean isFull(); //checks if the stock room is full

	public abstract int getConsign(int index); //gets the consignment code of its carton in the index

	public abstract String getName(int index); //gets the name of its carton in the index

	public abstract String getType(int index); //gets the type of its carton in the index

	public abstract String getDate(int index); //gets the expiry date of its carton in the index

	public abstract String getAll(int index); //gets all details of the carton in the index
}

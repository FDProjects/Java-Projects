// Author: Frank Dong
// Date: Oct 10, 2014
// Purpose:  a program which will munipulate arrays in order to add digits together.
import java.awt.*;
import hsa.Console;

public class BigIntTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	int start = 0;
	while (start == 0)
	{
	    c.print ("Please enter 1 for Integer Adding, anything else for Array Adding: ");
	    int selectionValue = c.readInt ();
	    if (selectionValue == 1)
	    {
		c.print ("Please enter the integer value you wish to add: ");
		int userValue = c.readInt ();
		BigPositiveInt userArray, result;
		userArray = new BigPositiveInt (1, 1);
		result = new BigPositiveInt (1, 1);
		userArray.get (c);
		result = userArray.add (userValue);
		c.println ("The value is: " + result.toString ());
	    }
	    else
	    {
		BigPositiveInt integer1, integer2, result;
		integer1 = new BigPositiveInt (1, 1);
		integer2 = new BigPositiveInt (1, 1);
		c.print("1:");
		integer1.get (c);
		c.print("2:");
		integer2.get (c);
		result = new BigPositiveInt (0);
		result = integer2.add (integer1);
		c.println ("The value is: " + result.toString ());
	    }
	    c.print ("Please enter 0 to restart, anything else to end: ");
	    start = c.readInt ();
	}
    }
} // BigIntTest class


/*Class BigPostiveInt
  This class contains methods for array munipulation as well as digits array, size and sign
 Fields:
	digits - a integer array that contains the digits of a big number
	size - an integer value which contains the size of the number
	sign - determines whether the number is +/-
 Methods:
	constructors
	get - will get the size and values from the user
	addDigitFront - will add a "digit" to the front of the array
	addDigitBack - will add a "digit" to the back of the array
	toString - converts the array to a string representing a big number
	add - Will add a given integer  to the current integer array
	add (overloaded) - will add a given array to the current array

*/
class BigPositiveInt
{
    protected int[] digits;
    protected int size;
    protected String sign;

    public BigPositiveInt (int size, int digit)
    {
	this.sign = "+";
	if (digit == 0)
	{
	    size = 1;
	}
	this.digits = new int [size];
	this.size = size;
	for (int count = 0 ; count < size ; count++)
	{
	    this.digits [count] = digit;
	}
    }


    public BigPositiveInt (int size)
    {
	this (size, 0);
    }


    // Author: Frank Dong
    // Date: Oct 11, 2014
    // Purpose: will convert the array to a string representing a big number
    // Parameters: None
    // Return Values: output
    public String toString ()
    {
	String output = "";
	String strDigit = "";
	for (int t = this.size - 1 ; t >= 0 ; t--)
	{
	    strDigit = String.valueOf (this.digits [t]);
	    output = output + "" + strDigit;
	}
	return output;
    }


    /*----------------------------------------------
    Author: Given Resources
    Method get
    Gets a bigInt from the user.
    Parameters: Console object.
    Return Value: None
    ----------------------------------------------*/
    public void get (Console c)
    {
	String a;
	c.print ("Please enter an array number value: ");
	a = c.readString ();
	this.size = 0;

	for (int i = 0 ; i < a.length () ; i++)
	    this.addDigitFront (a.charAt (i) - '0');

	this.removeLeadingZeros ();
    }


    /*----------------------------------------------
    Author: Given Resources
    Method removeLeadingZeros
    Removes any excess zeros at the front of the array.
    Parameters: None
    Return Value: None
    ----------------------------------------------*/
    public void removeLeadingZeros ()
    {
	int[] old;
	while ((this.digits [this.size - 1] == 0) && (size > 1))
	    this.size--;

	old = this.digits;
	this.digits = new int [this.size];

	for (int a = 0 ; a < this.size ; a++)
	    this.digits [a] = old [a];
    }


    /*----------------------------------------------
    Author: Given Resources
    Method addDigitFront
    Adds a given integer to the front of the current
    BigInt.
    Parameters: Given integer.
    Return Value: None
    ----------------------------------------------*/
    public void addDigitFront (int added)
    {
	int[] oldDigit;
	oldDigit = this.digits;

	this.size++;
	this.digits = new int [this.size];

	for (int i = 1 ; i < this.size ; i++)
	    this.digits [i] = oldDigit [i - 1];
	this.digits [0] = added;
    }


    /*----------------------------------------------
    Author: Given Resources
    Method addDigitBack
    Adds a given integer to the back of the current
    BigInt.
    Parameters: Given integer.
    Return Value: None
    ----------------------------------------------*/
    public void addDigitBack (int added)
    {
	int[] oldDigit;
	oldDigit = this.digits;

	this.size++;
	this.digits = new int [this.size];

	for (int i = 0 ; i < this.size - 1 ; i++)
	    this.digits [i] = oldDigit [i];
	this.digits [this.size - 1] = added;
    }


    /*
    Method: add
    Purpose: adds a given integer to the current BigInt
    Parameters: given integer
    Return Value: addition
    */
    public BigPositiveInt add (int givenInt)
    {
	int length = 1;
	int t = givenInt;
	while ((t = t / 10) != 0)
	{
	    length = length + 1;
	}
	int arrayLength;
	int arrayInt = 0;
	int carry = 0;
	int remainder = 0;
	int integerDigit = 0;
	int arrayCount = 0;

	BigPositiveInt addValue;
	if (this.size >= length)
	{
	    addValue = new BigPositiveInt ((this.size + 1), 1);
	    arrayLength = (this.size + 1);
	}
	else
	{
	    addValue = new BigPositiveInt (length + 1);
	    arrayLength = length + 1;
	}
	for (int i = 0 ; i < (arrayLength) ; i++)
	{

	    integerDigit = givenInt % 10;
	    if (arrayCount < (this.size))
		arrayInt = this.digits [arrayCount];
	    else
		arrayInt = 0;
	    remainder = (integerDigit + arrayInt) % 10;
	    if (((remainder + carry) >= 10) && (i == (arrayLength - 1)))
	    {
		addValue.digits [i + 1] = 0;
		addValue.digits [i] = 0;

	    }
	    else if (remainder + carry >= 10)
		addValue.digits [i] = 0;
	    else
		addValue.digits [i] = remainder + carry;

	    givenInt = (int) Math.floor (givenInt / 10);
	    carry = (int) Math.floor (((integerDigit + arrayInt) + carry) / 10);
	    arrayCount++;
	}
	addValue.removeLeadingZeros ();
	return addValue;
    }


    /*
    Method: add(overloaded)
    Purpose: adds a given BigInt to the current BigInt
    Parameters: given BigInt
    Return Value: addition
    */
    public BigPositiveInt add (BigPositiveInt givenArray)
    {
	BigPositiveInt addedValue;
	int additionLength = 0;
	int currentInt = 0;
	int givenInt = 0;
	int remainder = 0;
	int carry = 0;
	int subCount = 0;
	int highCount = 0;
	if (this.size >= givenArray.size)
	{
	    additionLength = (this.size + 1);
	    addedValue = new BigPositiveInt ((this.size + 1), 1);
	}
	else
	{
	    additionLength = ((givenArray.size) + 1);
	    addedValue = new BigPositiveInt ((givenArray.size) + 1, 1);
	}
	for (int i = 0 ; i < (additionLength) ; i++)
	{
	    if (subCount < (this.size))
		currentInt = this.digits [subCount];
	    else
		currentInt = 0;
	    if (highCount < (givenArray.size))
		givenInt = givenArray.digits [highCount];
	    else
		givenInt = 0;

	    remainder = (currentInt + givenInt) % 10;
	    if (((remainder + carry) >= 10) && (i == (additionLength - 1)))
	    {
		addedValue.digits [i] = 0;
		addedValue.digits [i + 1] = 0;

	    }
	    else if ((remainder + carry) >= 10)
		addedValue.digits [i] = 0;
	    else
		addedValue.digits [i] = remainder + carry;
	    carry = (int) Math.floor (((currentInt + givenInt) + carry) / 10);
	    subCount = subCount + 1;
	    highCount = highCount + 1;
	}
	addedValue.removeLeadingZeros ();
	return addedValue;
    }
}

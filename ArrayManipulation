// Author: Frank Dong
// Date: Oct, 4, 2014
// Purpose: A sorted array program which will manipulate arrays (merge them together, 
//          get rid of duplications & find the longest run)
import java.awt.*;
import hsa.Console;

public class SortedInt
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        int[] a = {};
        int[] b = {};
        
        SortedIntGroup x,y,z;
        c.print ("Please enter the size of your first array: ");
        int arraySize1 = c.readInt ();
        x = new SortedIntGroup (arraySize1);
        x.elements = new int [arraySize1];
        for (int i = 0 ; i < arraySize1; i++)
            {
                c.print("Please enter array number " + (i+1) + ": ");
                int userArray1 = c.readInt ();
                x.elements [i]= userArray1;
            }
            
        y = new SortedIntGroup ();    
        c.print ("Please enter the size of your second array: ");
        int arraySize2 = c.readInt ();
        y = new SortedIntGroup (arraySize2);
        y.elements = new int [arraySize2];
        for (int i = 0 ; i < arraySize2; i++)
            {
                c.print("Please enter array number " + (i+1) + ": ");
                int userArray2 = c.readInt ();
                y.elements [i]= userArray2;
            }
        
        c.println ("First Array:" + x.toString ());
        c.println ("Second Array:" + y.toString ());
        z = new SortedIntGroup ();
        z = x.merge (y);
        z.dropDups();
        c.println("Duplications Dropped: " + z.toString());
        c.println ("Merged: " + z.toString ());
        Run d;
        d = new Run ();
        d = x.longestRun ();
        c.println ("=============Longest Run================");
        c.println ("Start at " + d.start);
        c.println ("Length of " + d.length);
    }
} // SortedInt class

/*Class SortedIntGroup
  This class contains methods for array munipulation as well as size and elements field
 Fields:
        elements - a integer array that contains values that are to be munipulated
        size - a integer value which contains the size of the array
 Methods:
        constructors
        toString - converts elements to a string with the size printed in brackets
        sorted - returns true if the array is sorted
        dropDups - removes any duplicate values from the element
        merge - Will merge the current SortedIntGroup with another
        longestRun - will find the longest run in elements
*/
class SortedIntGroup
{
    protected int[] elements;
    protected int size;

    public SortedIntGroup (int sortedSize)
    {
        this.elements = new int [sortedSize];
        for (int t = 0 ; t < sortedSize ; t++)
        {
            this.elements [t] = (t + 1);
        }
        this.size = sortedSize;
    }


    public SortedIntGroup (int[] givenArray)
    {
        this.size = givenArray.length;
        this.elements = new int [this.size];
        for (int t = 0 ; t < this.size ; t++)
        {
            this.elements [t] = givenArray [t];
        }
        if (sorted () == false)
        {
            this.elements = new int [0];
            this.size = 0;
        }
    }


    public SortedIntGroup ()
    {
        this (0);
    }


    // Author: Frank Dong
    // Date: Oct 4, 2014
    // Purpose: will convert element to a string with the size printed in brackets
    // Parameters: None
    // Return Values: output
    public String toString ()
    {
        String output = "";
        for (int i = 0 ; i < this.size ; i++)
        {
            output += this.elements [i] + " ";
        }
        output += "(" + Integer.toString (this.size) + ")";
        return output;
    }


    // Author: Frank Dong
    // Date: Oct 4, 2014
    // Purpose: returns true if the element is sorted (ascending order)
    // Parameters: None
    // Return Values: isSorted
    private boolean sorted ()
    {
        boolean isSorted = true;
        int oldValue = 0;
        for (int i = 0 ; i < this.size ; i++)
        {
            if (oldValue > this.elements [i])
            {
                isSorted = false;
            }
            oldValue = this.elements [i];
        }
        return isSorted;
    }


    // Author: Frank Dong
    // Date: Oct 4, 2014
    // Purpose: will remove any duplicate values from element
    // Parameters: None
    // Return Values: None
    public void dropDups ()
    {
        int tempValue = 0;
        int size = 0;
        int newArray[];
        newArray = new int [this.size];
        for (int i = 0 ; i < this.size ; i++)
        {
            int elementsTemp = this.elements [i];
            if (elementsTemp != tempValue)
            {
                newArray [size] = elementsTemp;
                size = size + 1;
            }
            tempValue = elementsTemp;
        }
        this.size = size;
        this.elements = newArray;
    }


    // Author: Frank Dong
    // Date: Oct 5, 2014
    // Purpose: will merge the current SortedIntGroup with a second and create a third
    // Parameters: SortedIntGroup givenArray
    // Return Values: x
    public SortedIntGroup merge (SortedIntGroup givenArray)
    {
        SortedIntGroup x;
        x = new SortedIntGroup ();
        int newArray[];
        int size = 0;
        newArray = new int [this.size + givenArray.elements.length];
        if (sorted () == false || x.sorted () == false)
        {
            x = new SortedIntGroup ();
        }
        else
        {
            int elementsCount = 0;
            int givenCount = 0;
            while (size < newArray.length)
            {
                if (elementsCount >= this.size)
                {
                    newArray [size] = givenArray.elements [givenCount];
                    givenCount = givenCount + 1;
                }
                else if (givenCount >= givenArray.elements.length)
                {
                    newArray [size] = this.elements [elementsCount];
                    elementsCount = elementsCount + 1;
                }
                else if (this.elements [elementsCount] <= givenArray.elements [givenCount])
                {
                    newArray [size] = this.elements [elementsCount];
                    elementsCount = elementsCount + 1;
                }
                else if (givenCount <= givenArray.elements.length - 1)
                {
                    newArray [size] = givenArray.elements [givenCount];
                    givenCount = givenCount + 1;
                }
                size = size + 1;
            }
        }
        x.size = size;
        x.elements = newArray;
        return x;
    }


    // Author: Frank Dong
    // Date: Oct 6, 2014
    // Purpose: will return the longes run in element
    // Parameters: None
    // Return Values: x
    public Run longestRun ()
    {
        Run x = new Run ();
        int count = 0;
        int longestCount = 1;

        while (count < this.size - 1)
        {
            if (this.elements [count] + 1 == this.elements [count + 1])
            {
                longestCount = longestCount + 1;
                if (longestCount > x.length)
                {
                    x.length = longestCount;
                    x.start = count + 1 - x.length + 1;
                }
            }
            else
            {
                longestCount = 1;
            }
            count = count + 1;
        }
        return x;
    }
}

/*Class Run
  This class contains fields longestRun uses
 Fields:
        start - a integer array that contains the start value of the longestRun
        length - a integer value which contains the length of the longestRun
*/
class Run
{
    public int start;
    public int length;
}



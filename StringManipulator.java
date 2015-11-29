// Author: Frank Dong
// Date: Oct, 19, 2014
// Purpose: A replace/permutations program which will manipulate 
//          strings in order to replace and compare values
import java.awt.*;
import hsa.Console;

/*Class Strings
  This class contains the main method, as well as methods for string munipulation
 Methods:
        replace - replaces a given string value with another given string value.
        permuted - will determine whether a string is a permutation of another
*/
public class Strings
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        int inputRestart = 0;
        while (inputRestart == 0)
        {
            c.print ("=======================================================================");
            c.print ("Please enter 1 for replace program, anything else for permuted program: ");
            int selectionInput = c.readInt ();
            if (selectionInput == 1)
            {
                c.print ("Please enter a general string: ");
                String userString = c.readLine ();
                c.print ("Please enter the string you wish to replace: ");
                String userOldValue = c.readLine ();
                c.print ("Please enter the string you wish to replace the old string with: ");
                String userNewValue = c.readLine ();
                c.println ("Your new string is:" + replace (userString, userOldValue, userNewValue));
            }
            else
            {
                c.print ("Please enter your first string: ");
                String firstUserString = c.readLine ();
                c.print ("Please enter your second string to compare: ");
                String secondUserString = c.readLine ();
                c.println ("The two values are: " + permuted (firstUserString, secondUserString) + " for permutations");
            }
            c.println ("Please enter 0 to restart, anything else to end: ");
            inputRestart = c.readInt ();
        }
    } // main method
    
    // Author: Frank Dong
    // Date: Oct 19, 2014
    // Purpose: will replace values in a user string with a new value
    // Parameters: String givenString, String oldString, String newString
    // Return Values: outputString + givenString
    public static String replace (String givenString, String oldString, String newString)
    {
        String outputString = "";
        String outputFront = "";
        while (givenString.indexOf (oldString) != -1)
        {
            outputFront = givenString.substring (0, givenString.indexOf (oldString));
            outputString = outputString + outputFront + newString;
            givenString = givenString.substring ((outputFront.length () + oldString.length ()), givenString.length ());
        }
        return (outputString + givenString);
    }

    // Author: Frank Dong
    // Date: Oct 20, 2014
    // Purpose: determine whether a string is the permutation of the other
    // Parameters: String givenFirst, String givenSecond
    // Return Values: isPerm
    public static boolean permuted (String givenFirst, String givenSecond)
    {
        StringBuffer b = new StringBuffer ();
        b.append (givenFirst);
        boolean isPerm = true;
        if (b.length () == givenSecond.length ())
        {
            for (int i = givenSecond.length () ; i > 0 ; i--)
            {
                if (b.indexOf (givenSecond.substring (i - 1, i)) < 0)
                {
                    isPerm = false;
                }

                else if (b.indexOf (givenSecond.substring (i - 1, i)) > 0)
                {
                    b.deleteCharAt (b.indexOf (givenSecond.substring (i - 1, i)));
                }
            }
        }
        else
        {
            isPerm = false;
        }
        return isPerm;
    }
} // Strings class

// Author: Frank Dong
// Date: Oct 16, 2015
// Purpose: A quick recursion class.
import java.awt.*;
import hsa.Console;

public class Recursion
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
        c = new Console ();
        long number = calcGCD(18,12);
        c.print (number);
        //displayCountdown(5);

    } // main method
    
    // Author: Frank Dong
    // Date: Sept 14, 2014
    // Purpose: will calculate and return the greatest common divisor
    // Input: long m, long n
    // Output: return GCD
    public static long calcGCD (long m, long n)
    {
        long GCD = 0;
        if (n == 0)
        {
            GCD = m;
        }
        else
        {
            GCD = calcGCD(n,(m % n));
        }
        return GCD;
    }
    
    // Author: Frank Dong
    // Date: Sept 14, 2014
    // Purpose: will recursively print from numbers n to 1. (no loops)
    // Input: int n
    // Output: none
    public static void displayCountdown (int n)
    {
        if (n == 1)
        {
            c.print ("1");
        }
        else
        {
            c.print (n + ",");
            n = n - 1;
            displayCountdown (n);
        }
    }
} // Recursion class

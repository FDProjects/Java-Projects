// Title: Methods.Java
// Author: Frank Donga
// Started: Sept 11, 2015
// Purpose: A program which will returning prime numbers corresponding to the user input and 
//          calculate the area of a curve by using rectangles.
import java.awt.*;
import hsa.Console;
import java.lang.Math;

public class Prime_Numbers
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
        c = new Console ();
        int y = 14;
        TwoPrime x;
        x = findTwoPrimeGap (y);
        c.println (x.first);
        c.println (x.second);
    }

    // Author: Frank Dong
    // Date: Sept 11, 2014
    // Purpose: Will return true if number given is prime
    // Input: int x
    // Output: return prime
    public static boolean isPrime (int x)
    {
        boolean prime = false;
        if (x == 2 || x == 3)
            prime = true;
        else if (x < 2 || (x % 2) == 0)
            prime = false;
        else
        {   
            double number = x;
            double count = 3;
            while (count < Math.sqrt(x) && (number % count) != 0)
            {
                count = count + 2;
            }
            if ((number % count) == 0)
            {
                prime = false;
            }
            else
            {
                prime = true;
            }
         } 
         return prime;
     }
    
    // Author: Frank Dong
    // Date: Sept 11, 2014
    // Purpose: Will calculate the number of primes between a given range
    // Input: int x, int y
    // Output: return primeAmount 
    public static int calcNumOfPrimes (int x, int y)
    {
        if (y > x)
        {
            int temp = x;
            x = y;
            y = temp;
        }
        
        int primeAmount = 0;
        int count = 0;
        while (y <= x)
        {
            boolean prime = isPrime(y);
            y = y + 1;
            if (prime == true)
            {
                primeAmount = primeAmount + 1;
            }
        }
        return primeAmount;
     }

    // Author: Frank Dong
    // Date: Sept 12, 2014
    // Purpose: Will print all the primes between a certain range
    // Input: int x, int y
    // Output: None
    public static void printPrimes (int x, int y)
    {
        if (y > x)
        {
            int temp = x;
            x = y;
            y = temp;
        }
            
            while (y <= x)
            {
                boolean prime = isPrime(y);
                if (prime == true)
                {
                    c.print (" " + y);
                }
                y = y + 1;
            }
    }
    
    // Author: Frank Dong
    // Date: Sept 12, 2014
    // Purpose: Will return the first two prime numbers with a gap of x
    // Input: int x
    // Output: return twoPrime
    public static TwoPrime findTwoPrimeGap (int x)
    {
        long gapAmount = x;
        long primeNumber1 = 0;
        long primeNumber2 = 0;
        int count = 0;
        while (primeNumber1 - primeNumber2 != gapAmount)
        {
                primeNumber2 = primeNumber1;
                boolean primeValue = false; 
                while (primeValue != true)
                {
                    count = count + 1;
                    primeValue = isPrime (count);
                }
                primeNumber1 = count;
        }
        TwoPrime twoPrime = new TwoPrime();
        twoPrime.first = primeNumber2;
        twoPrime.second = primeNumber1;
        return twoPrime; 
    }
            
            
} // Prime_Numbers class

class TwoPrime
{
    public long first;
    public long second;
}

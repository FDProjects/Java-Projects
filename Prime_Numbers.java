// Title: Methods.Java
// Author: Frank Donga
// Date: Sept 11, 2014
// Purpose: A program which will return prime numbers corresponding to the user input,
//          and calculate the area of a curve by using rectangles
import java.awt.*;
import hsa.Console;
import java.lang.Math;

public class Methods
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        int programStart = 1;

        while (programStart == 1)
        {
            c.println ("======================METHODS PROGRAM=========================");
            c.println ("Please enter the number corresponding to your choice:");
            c.println ("1 - Prime Numbers");
            c.println ("2 - Numerical Integration");
            c.println ("3 - Recursion");
            c.println ("Anything else - End Program");
            int userInput = c.readInt ();

            if (userInput == 1)
            {
                c.println ("-----------------------Prime Numbers-----------------------");
                c.print ("Please enter 1 for the listing of prime numbers program or anything else for the prime number gap program: ");
                int selectionInput = c.readInt ();
                if (selectionInput == 1)
                {
                    c.print ("Please enter your first integer: ");
                    int x = c.readInt ();
                    c.print ("Please enter your second integer: ");
                    int y = c.readInt ();
                    printPrimes (x, y);
                    c.println ("                              ");
                }
                else
                {
                    c.print ("Please enter a positive integer gap: ");
                    int y = c.readInt ();
                    TwoPrime x;
                    x = findTwoPrimeGap (y);
                    c.println ("Numbers are " + x.first + " & " + x.second);
                }
                
             }
             else if (userInput == 2)
             {
                c.println ("-------------------Numerical Integration-------------------");
                c.print ("Please enter a double value radius: ");
                double x = c.readInt ();
                c.println ("The area of the semicricle is: " + findArea(x));
             }
             else if (userInput == 3)
             {
                c.println ("-------------------------Recursion-------------------------");
                c.print ("Please enter 1 for GCD calculation or anything else for Countdown Display: ");
                int selectionInput = c.readInt ();
                if (selectionInput == 1)
                {
                    c.print ("Please enter your first long value number: ");
                    long m = c.readInt ();
                    c.print ("Please enter your second long value number: ");
                    long n = c.readInt ();
                    c.println ("The greatest common divisor is: " + calcGCD(m,n));
                }
                else
                {
                    c.print ("Please enter a integer value you wish to start your countdown from: ");
                    int n = c.readInt ();
                    displayCountdown (n);
                    c.println ("                                 ");
                }
              }
              else
              {
                programStart = 0;
              }
           }

        } // main method

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
                while (count < Math.sqrt (x) && (number % count) != 0)
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
                boolean prime = isPrime (y);
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
                boolean prime = isPrime (y);
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
            TwoPrime twoPrime = new TwoPrime ();
            twoPrime.first = primeNumber2;
            twoPrime.second = primeNumber1;
            return twoPrime;
        }

        // Author: Frank Dong
        // Date: Sept 13, 2014
        // Purpose: Will find the height
        // Input: double x, double r
        // Output: return the value of the sqrt
        public static double findHeight (double x, double r)
        {
            return Math.sqrt (Math.pow (r, 2) - (Math.pow (x - r, 2)));
        }

        // Author: Frank Dong
        // Date: Sept 14, 2014
        // Purpose: Given the radius, will return the area of the semicircle.
        // Input: double r
        // Output: newArea
        public static double findArea (double r)
        {
            int recAmount = 50;
            double newArea = 0.0;
            double oldArea = 0.0;
            double difference = 1.0;
            while (difference >= 0.00005)
            {
                double width;
                double height;
                double radius = r;
                double rectangleArea;
                double areaTotal = 0.0;
                int count = 0;
                while (count < recAmount)
                {
                    width = (2 * radius) / recAmount;
                    height = findHeight ((count * width), radius);
                    rectangleArea = width * height;
                    areaTotal = areaTotal + rectangleArea;
                    count = count + 1;
                }
                newArea = areaTotal;
                difference = newArea - oldArea;
                oldArea = newArea;
                recAmount = recAmount * 2;
            }
            return newArea;
        }

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
                GCD = calcGCD (n, (m % n));
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

    } // Methods class


    class TwoPrime
    {
        public long first;
        public long second;
    }



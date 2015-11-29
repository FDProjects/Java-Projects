// Author: Frank Dong
// Date: Oct, 8, 2014
// Purpose: A polynomial program which will munipulate arrays in order to simulate a polynomial
import java.awt.*;
import hsa.Console;

public class Poly
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        int start = 0;
        while (start == 0)
        {
            Polynomial x, y;
            x = new Polynomial (0);
            x.get (c);
            c.print ("Please enter a value for x: ");
            int userValue = c.readInt ();
            c.println ("The Polynomial is: " + x.toString ());
            c.println ("The Polymonial with the value x is " + x.value (userValue));
            y = x.derivative ();
            c.println ("The derivative is: " + y.toString ());
            c.println ("=====================================================");
            c.print ("Please enter 0 to restart, anything else to exit: ");
            start = c.readInt ();
        }

    } // main method
} // Poly class

/*Class Polynomial
  This class contains methods for array munipulation as well as coeff and degree fields
 Fields:
        coeff - a double array that contains values of a polynomial
        degree - a integer value which contains the degree of the polynomial
 Methods:
        constructor
        toString - converts the array to a string representing a polynomial
        get - will get the degree and coefficients from the user
        value - will calculate the value of the polynomial given a value for x
        derivative - will return the deriative of the given polynomial
*/
class Polynomial
{
    protected double[] coeff;
    protected int degree;

    public Polynomial (int givenDegree)
    {
        this.coeff = new double [givenDegree + 1];
        this.degree = givenDegree;
    }


    // Author: Frank Dong
    // Date: Oct 8, 2014
    // Purpose: will convert the array to a string representing a polynomial
    // Parameters: None
    // Return Values: output
    public String toString ()
    {
        String output = "";
        for (int t = this.degree ; t >= 0 ; t--)
        {
            if (this.coeff [t] == 0)
            {
                output = output + "";
            }
            if (this.coeff [t] > 0)
            {
                output = output + "+";
            }

            if (t == this.degree)
            {
                if (t == 1 && this.coeff [t] == 1)
                {
                    output = "x";
                }
                else if (t == 1 && this.coeff [t] == -1)
                {
                    output = "-x";
                }
                else if (t == 1)
                {
                    output = this.coeff [t] + "x";
                }
                else if (this.coeff [t] == 1)
                {
                    output = "x^" + t;
                }
                else if (this.coeff [t] == -1)
                {
                    output = "-x^" + t;
                }
                else
                {
                    output = this.coeff [t] + "x^" + t;
                }
            }
            else if (t == 1)
            {
                if (this.coeff [t] < 0 && this.coeff [t] != -1)
                {
                    output = output + this.coeff [t] + "x";
                }
                else if (this.coeff [t] == 1)
                {
                    output = output + "x";
                }
                else if (this.coeff [t] == -1)
                {
                    output = output + "-x";
                }
                else
                {
                    output = output + this.coeff [t] + "x";
                }
            }
            else if (t == 0)
            {
                output = output + this.coeff [t];
            }
            else if (this.coeff [t] < 0)
            {
                if (this.coeff [t] == -1)
                {
                    output = output + "-x^" + t;
                }
                else
                {
                    output = output + this.coeff [t] + "x^" + t;
                }
            }
            else if (this.coeff [t] >= 0)
            {
                if (this.coeff [t] == 1)
                {
                    output = output + "x^" + t;
                }
                else
                {
                    output = output + this.coeff [t] + "x^" + t;
                }
            }
        }
        return output;
    }


    // Author: Frank Dong
    // Date: Oct 8, 2014
    // Purpose: will allow the user to enter the degree and the coefficients for the polynomial array
    // Parameters: Console c
    // Return Values: None
    public void get (Console c)
    {
        c.print ("Please enter the degree of your polynomial: ");
        this.degree = c.readInt ();
        this.coeff = new double [this.degree + 1];
        for (int count = this.degree ; count >= 0 ; count--)
        {
            c.print ("Please enter double coefficent number " + count + ": ");
            this.coeff [count] = c.readDouble ();
        }
    }


    // Author: Frank Dong
    // Date: Oct 9, 2014
    // Purpose: will calculate the value of the polynomial given a value for x
    // Parameters: x
    // Return Values: output
    public double value (double x)
    {
        double output = 0;
        for (int t = this.degree ; t > 0 ; t--)
        {
            if (t == this.degree)
            {
                output = (this.coeff [t] * x) + this.coeff [t - 1];
            }
            else
            {
                output = output * x + this.coeff [t - 1];
            }
        }
        return output;
    }


    // Author: Frank Dong
    // Date: Oct 10, 2014
    // Purpose: will determine the derivative of the current polynomial.
    // Parameters: None
    // Return Values: Polynomial x
    public Polynomial derivative ()
    {
        Polynomial x;
        x = new Polynomial (this.degree);
        double[] newArray;
        newArray = new double [this.coeff.length];
        for (int i = (newArray.length - 1) ; i > 0 ; i--)
        {
            newArray [i - 1] = this.coeff [i] * i;
        }
        x.coeff = newArray;
        x.degree = newArray.length - 2;
        return x;
    }
}

// Author: Frank Dong
// Date: Sept, 23, 2014
// Purpose: A monomial program which will munipulate the monomial in certain ways
import java.awt.*;
import hsa.Console;
import java.lang.Math;

public class Mono
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        Monomial x;
        x = new Monomial ();
        x.get (c);
        //c.println (x.canDivide (6));
        c.println(x.toString ());
        //c.println (x.divide (6, 2).coefficient);
        //c.println (x.divide (6, 2).exponent);
        c.println (x.value (2));
        c.println (x.pow (6).coefficient);
        c.println (x.pow (6).exponent);
        c.println (x.equal (2,4));
        // Place your program here.  'c' is the output console
    } // main method
} // Mono class

/*Class Monomial
  This class stores a monomial and allows the user to do a variety of munipulations to the monomial
 Fields:
        coefficient - a double value that is the first value of a monomial
        exponent - a interger value that is the end exponenet part of a monomial
 Methods:
        constructors
        simplify - simplifies the exponent to 0 if the coefficient is 0
        get - gets the fields from the user
        toString - converts the Monomial into a string value in the form of eX^a
        canAdd - returns whether a given monomial can add the current
        canDivide - returns whether a given monomial can divide the current
        add - adds a given monomial with the current
        subtract - subtracts a given monomial with the current
        mutiply - mutiplies a given monomial with the current
        divide - divides a given monomial with the current
        value - returns a value for the monomial given a value for x
        pow - returns a new Monomial raised to a given power
        equal - returns whether a given monomial is equal to the current
*/
class Monomial
{
    public double coefficient;
    public int exponent;

    public Monomial (double c, int e)
    {
        this.coefficient = c;
        this.exponent = e;
        simplify ();
    }


    public Monomial ()
    {
        this.coefficient = 0;
        this.exponent = 0;
    }


// Author: Frank Dong
// Date: Sept 23, 2014
// Purpose: Will set the exponent to zero if the coefficient is zero
// Parameters: None
// Return Values: None    
    public void simplify ()
    {
        if (this.coefficient == 0)
        {
            this.exponent = 0;
        }
    }

// Author: Frank Dong
// Date: Sept 23, 2014
// Purpose: Will get the field values from the user
// Parameters: Console c
// Return Values: None  
    public void get (Console c)
    {
        c.print ("Please enter a double value coefficient: ");
        this.coefficient = c.readDouble ();
        c.print ("Please enter a integer value exponent: ");
        this.exponent = c.readInt ();
        simplify ();
    }

// Author: Frank Dong
// Date: Sept 23, 2014
// Purpose: Returns a string representing the Monomial
// Parameters: None
// Return Values: userOutputFinal  
    public String toString ()
    {
        String userOutputFinal = this.coefficient + "x^" + this.exponent;
        if (this.exponent == 0)
        {
            userOutputFinal = Double.toString (this.coefficient);
        }
        if (this.exponent == 1)
        {
            userOutputFinal = Double.toString (this.coefficient) + "x";
        }
        if (this.coefficient == 0)
        {
            userOutputFinal = "0";
        }
        if (this.coefficient == -1)
        {
            userOutputFinal = "-x^" + Double.toString (this.exponent);
        }
        return userOutputFinal;
    }

// Author: Frank Dong
// Date: Sept 24, 2014
// Purpose: returns true if a given Monomial can be added to this one
// Parameters: givenExponent
// Return Values: addMono  
    public boolean canAdd (int givenExponent)
    {
        boolean addMono = false;
        if (givenExponent == this.exponent)
        {
            addMono = true;
        }
        return addMono;
    }

// Author: Frank Dong
// Date: Sept 23, 2014
// Purpose: returns true if a given Monomial can be divided to this one
// Parameters: givenCoefficient
// Return Values: divideMono
    public boolean canDivide (double givenCoefficient)
    {
        boolean divideMono = true;
        double secondCoefficient = this.coefficient;
        if (secondCoefficient == 0)
        {
            divideMono = false;
        }
        return divideMono;
    }

// Author: Frank Dong
// Date: Sept 24, 2014
// Purpose: Adds a given monomial to the current monomial
// Parameters: givenCoefficient, givenExponent
// Return Values: x
    public Monomial add (double givenCoefficient, int givenExponent)
    {
        Monomial x = new Monomial ();
        if (canAdd (givenExponent))
        {
            x.coefficient = this.coefficient + givenCoefficient;
            x.exponent = this.exponent;
            x.simplify ();
        }
        return x;
    }

// Author: Frank Dong
// Date: Sept 24, 2014
// Purpose: subtracts a given monomial with the current monomial
// Parameters: givenCoefficient, givenExponent
// Return Values: x
    public Monomial subtract (double givenCoefficient, int givenExponent)
    {
        Monomial x = new Monomial ();
        if (canAdd (givenExponent))
        {
            x.coefficient = givenCoefficient - this.coefficient;
            x.exponent = this.exponent;
            x.simplify ();
        }
        return x;
    }

// Author: Frank Dong
// Date: Sept 24, 2014
// Purpose: multiplies a given monomial with the current monomial
// Parameters: givenCoefficient, givenExponent
// Return Values: x
    public Monomial multiply (double givenCoefficient, int givenExponent)
    {
        Monomial x = new Monomial ();
        x.coefficient = givenCoefficient * this.coefficient;
        x.exponent = givenExponent + this.exponent;
        x.simplify ();
        return x;
    }

// Author: Frank Dong
// Date: Sept 24, 2014
// Purpose: divides a given monomial with the current monomial
// Parameters: givenCoefficient, givenExponent
// Return Values: x
    public Monomial divide (double givenCoefficient, int givenExponent)
    {
        Monomial x = new Monomial ();
        if (canDivide (givenCoefficient))
        {
            x.coefficient = givenCoefficient / this.coefficient;
            x.exponent = givenExponent - this.exponent;
            x.simplify ();
        }
        return x;
    }

// Author: Frank Dong
// Date: Sept 25, 2014
// Purpose: Determines the value of a monomial with a given x value
// Parameters: x
// Return Values: monoValue 
    public double value (double x)
    {
        double monoValue = this.coefficient * (Math.pow(x,this.exponent));
        return monoValue;
    }
    
// Author: Frank Dong
// Date: Sept 25, 2014
// Purpose: Returns a new Monomial with a raised given power
// Parameters: givenPower
// Return Values: x     
    public Monomial pow (int givenPower)
    {
        Monomial x = new Monomial ();
        x.coefficient = this.coefficient;
        x.exponent = givenPower;
        return x;
    }

// Author: Frank Dong
// Date: Sept 25, 2014
// Purpose: Determines whether a given Monomial is equal to the current Monomial
// Parameters: givenCoefficient, givenExponent
// Return Values: isEqual
    public boolean equal (double givenCoefficient, int givenExponent)
    {
        boolean isEqual = false;
        if (this.coefficient == givenCoefficient && this.exponent == givenExponent)
        {
            isEqual = true;
        }
        return isEqual;
    }
}

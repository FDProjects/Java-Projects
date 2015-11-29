// Author: Frank Dong
// Date: Nov 15, 2015
// Purpose: A program which prints diamonds through using the * symbol
import java.awt.*;
import hsa.Console;

public class Diamonds
{
    static Console c;

    public static void main (String[] args)
    {
        c = new Console ();
        int userInput = 0;
        int userSelection = 0;
        String starOutput = " ";
        int space = 1;

        while (userInput < 1 || userInput > 25 || userInput % 2 == 0)
        {
            c.println ("Please input an integer number between 1 and 25, no multiples of 2 please!:");
            userInput = c.readInt ();
        }
            int rows = userInput;
            for (int i = 0 ; i < rows ; i++)
            {
                starOutput = starOutput + "*";
            }
            c.println (starOutput);
            rows = userInput + 2;

            starOutput = "";
            while (rows >= 1)
            {
                if (rows == userInput + 2)
                {
                    for (int i = 0 ; i < rows ; i++)
                    {
                        starOutput = starOutput + "*";
                    }
                    c.println (starOutput);
                    rows = rows - 2;
                }
                else
                {
                    starOutput = "";
                    for (int i = 0 ; i < space ; i++)
                    {
                        starOutput = starOutput + " ";
                    }
                    for (int i = 0 ; i < rows ; i++)
                    {
                        starOutput = starOutput + "*";
                    }
                    rows = rows - 2;
                    c.println (starOutput);
                    space = space + 1;
                }
            }
    }
}

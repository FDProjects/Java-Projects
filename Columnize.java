// Author: Frank Dong
// Date: Oct 23, 2014
// Purpose: A columnizing program which will munipulate 
//          strings in order to display each in 2 rows of 35
import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

public class Columnize
{
    static Console c;           // The output console

    public static void main (String[] args)
        throws IOException
    {
        c = new Console ();
        c.print ("Please enter the name of the file you wish to read: ");
        String fileName = c.readLine ();
        String outputString = "";
        BufferedReader input;
        input = new BufferedReader (new FileReader (fileName));
        String line = input.readLine ();
        while (line != null)
        {
            outputString = outputString + line;
            line = input.readLine ();
        }
        c.print ("Please enter the name of your output file: ");
        fileName = c.readLine ();
        PrintWriter output;
        output = new PrintWriter (new FileWriter (fileName));

        String s = outputString;
        StringTokenizer t = new StringTokenizer (s);
        String lineString = "";
        String newWord = "";
        String outputJustifed = "";
        int lineLength = 0;
        int lineCount = 0;
        while (t.hasMoreTokens ())
        {
            newWord = t.nextToken ();
            lineLength = lineLength + (newWord.length () + 1);
            if (t.hasMoreTokens () == false)
            {
                lineString = lineString + (newWord + " ");
            }
            if (lineLength <= 35 & t.hasMoreTokens () != false)
            {
                lineString = lineString + (newWord + " ");
            }
            else
            {
                lineLength = 0;
                String newString = "  ";
                String replaceString = " ";
                String outString = "";
                String outputFront = "";
                while (lineLength <= 35)
                {
                    if (lineString.indexOf (replaceString) != -1)
                    {
                        outputFront = lineString.substring (0, lineString.indexOf (replaceString));
                        outString = outString + outputFront + newString;
                        lineString = lineString.substring ((outputFront.length () + replaceString.length ()), lineString.length ());
                        lineLength = outString.length () + lineString.length ();
                    }
                    else if (lineString.indexOf (replaceString) == -1)
                    {
                        lineString = outString + lineString;
                        replaceString = newString;
                        newString = newString + " ";
                        outString = "";
                    }
                }
                outputJustifed = outputJustifed + (outString + lineString);
                lineString = "";
                lineString = lineString + (newWord + " ");
                lineLength = 0;
                lineLength = lineLength + (newWord.length () + 1);
                lineCount = lineCount + 1;
            }
        }
        StringBuffer a = new StringBuffer ();
        a.append (outputJustifed.substring (0, (((lineCount) / 2) * 36)));
        StringBuffer b = new StringBuffer ();
        b.append (outputJustifed.substring((((lineCount) / 2) * 36), outputJustifed.length()));
        int currentCount = 0;
        int aCount = 0;
        int bCount = 0;
        while ((lineCount/2) > currentCount)
        {
            output.print (a.substring(aCount, (aCount + 36)));
            output.print ("     ");
            output.print (b.substring(bCount, (bCount + 36)));
            output.println ();
            aCount = aCount + 36;
            bCount = bCount + 36;
            currentCount = currentCount + 1;
        }
        if (lineCount%2 != 0 && lineCount/2 == currentCount)
            {
                output.print ("                                         ");
                output.print (b.substring(bCount, b.length()));
                System.out.println (b.substring(bCount, (b.length())));
                output.println ();
                bCount = bCount + 36;
                currentCount = currentCount + 1;
            }
        output.close ();
    } // main method
} // Columnize class



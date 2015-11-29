// Author: Frank Dong
// Date: Oct, 16, 2015
// Purpose: To create a scrabble program which will use graphics to generate a
//          small scrabble class.
import java.awt.*;
import hsa.Console;
import java.lang.Math;
import java.lang.Character;

public class Scrabble
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        int runProgram = '1';
        while (runProgram == '1')
        {  
            c.println ("Please enter a character value for your first tile");
            char key1;
            key1 = c.getChar();
            key1 = Character.toUpperCase(key1);
            
            c.println ("Please enter a character value for your second tile");
            char key2;
            key2 = c.getChar();
            key2 = Character.toUpperCase(key2);
            
            c.println ("Please enter a character value for your third tile");
            char key3;
            key3 = c.getChar();
            key3 = Character.toUpperCase(key3);
            
            c.println ("Please enter a character value for your fourth tile");
            char key4;
            key4 = c.getChar();
            key4 = Character.toUpperCase(key4);
            
            c.println ("Please enter a character value for your fifth tile");
            char key5;
            key5 = c.getChar();
            key5 = Character.toUpperCase(key5);
            c.clear();
            
            ScrabbleHand x;
            x = new ScrabbleHand (key1,key2,key3,key4,key5);

            c.println (" ");
            c.println (" ");
            c.println (" ");
            c.println ("Please enter 1 to run program again, anything else to end");
            x.print (c);
            char key6;
            key6 = c.getChar();
            runProgram = (int)key6;
        }
     } // main method
} // Scrabble class


    class Tile
    {
        public char letter;

        public Tile ()
        {
            this.letter = (char) (Math.random () * 26 + 'A');
        }

        public Tile (char letterValue)
        {
            this.letter = letterValue;
        }

        public String toString ()
        {
            String tileLetter;
            tileLetter = Character.toString (this.letter);
            return tileLetter;
        }

        public int value ()
        {
            int letterValue = 0;
            if (this.letter == 'A' || this.letter == 'E' || this.letter == 'I' || this.letter == 'L' || this.letter == 'N' || this.letter == 'O' || this.letter == 'R' || this.letter == 'S' || this.letter == 'T' || this.letter == 'U')
            {
                letterValue = 1;
            }
            else if (this.letter == 'D' || this.letter == 'G')
            {
                letterValue = 2;
            }
            else if (this.letter == 'B' || this.letter == 'C' || this.letter == 'M' || this.letter == 'P')
            {
                letterValue = 3;
            }
            else if (this.letter == 'F' || this.letter == 'H' || this.letter == 'V' || this.letter == 'W' || this.letter == 'Y')
            {
                letterValue = 4;
            }
            else if (this.letter == 'K')
            {
                letterValue = 5;
            }
            else if (this.letter == 'J' || this.letter == 'X')
            {
                letterValue = 8;
            }
            else if (this.letter == 'Q' || this.letter == 'Z')
            {
                letterValue = 10;
            }
            return letterValue;
        }

        public void print (Console c, int x, int y)
        {
            String tileValue = toString ();
            String letterValue = Integer.toString (value ());
            Font a = new Font ("Arial", Font.BOLD, 30);
            c.setFont (a);
            c.drawString (tileValue, x + 3, y + 28);
            Font b = new Font ("Serif", Font.BOLD, 15);
            c.setFont (b);
            c.drawString (letterValue, x + 28, y + 32);
            c.drawRect (x, y, 45, 42);
        }
    }


    class ScrabbleHand
    {
        public Tile tile1;
        public Tile tile2;
        public Tile tile3;
        public Tile tile4;
        public Tile tile5;

        public ScrabbleHand ()
        {
            this.tile1 = new Tile ();
            this.tile2 = new Tile ();
            this.tile3 = new Tile ();
            this.tile4 = new Tile ();
            this.tile5 = new Tile ();
        }

        public ScrabbleHand (char value1, char value2, char value3, char value4, char value5)
        {
            this.tile1 = new Tile (value1);
            this.tile2 = new Tile (value2);
            this.tile3 = new Tile (value3);
            this.tile4 = new Tile (value4);
            this.tile5 = new Tile (value5);
        }

        public int value ()
        {
            int totalValue = tile1.value () + tile2.value () + tile3.value () + tile4.value () + tile5.value ();
            return totalValue;
        }

        public String toString ()
        {
            return (tile1.toString () + "," + tile2.toString () + "," + tile3.toString () + "," + tile4.toString () + "," + tile5.toString ());
        }

        public void print (Console c)
        {
            tile1.print (c, 1, 5);
            tile2.print (c, 48, 5);
            tile3.print (c, 95, 5);
            tile4.print (c, 142, 5);
            tile5.print (c, 189, 5);

            String totalValue = Integer.toString (value ());
            Font a = new Font ("Serif", Font.BOLD, 38);
            c.setFont (a);
            c.drawString ("(" + totalValue + ")", 250, 32);
        }
    }



// The "QueueExercise" class.
//Author: Frank Dong
//Data: Jan 19, 2015
//Purpose: A fun queues program.
import java.awt.*;
import hsa.Console;

public class QueueExercise
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console ();
        StraightLineArray x;
        ShiftedArray y;
        LinkedListQ z;

        x = new StraightLineArray ();
        y = new ShiftedArray ();
        z = new LinkedListQ ();
        int programRun = 0;

        while (programRun == 0)
        {
            c.println ("Please enter:");
            c.println ("1 - add onto queue");
            c.println ("2 - remove from queue");
            c.println ("3 - reset ALL queues");
            c.println ("Anything else - exit");
            int userChoice = c.readInt ();

            if (userChoice == 1)
            {
                x.add (c);
                y.add (c);
                z.add (c);
            }
            else if (userChoice == 2)
            {
                x.delete (c);
                y.delete (c);
                z.delete (c);
            }
            else if (userChoice == 3)
            {
                x.reset ();
                y.reset ();
                z.reset ();
            }
            c.clear ();
            c.println ("Queue");
            x.printArray (c);
            c.println ();
            y.printArray (c);
            c.println ();
            z.toString (c);

            c.print ("Please enter 0 to continue, anything else to exit: ");
            programRun = c.readInt ();
        }
    } // main method
} // QueueExercise class

//Class Queue (Abstract)
//Methods:  void add            -   an abstract method for adding in the queue
//          void delete         -   an abstract method for deleting in the queue
//          void reset          -   an abstract method for resetting the queue
abstract class Queue
{
    // Purpose: an abstract add method
    // Parameters: Console c
    // Return Values: None
    abstract public void add (Console c);
    
    // Purpose: an abstract delete method
    // Parameters: Console c
    // Return Values: None
    abstract public void delete (Console c);
    
    // Purpose: an abstract reset method
    // Parameters: None
    // Return Values: None
    abstract public void reset ();
}

//Class StraightLineArray (extends Queues)
//Fields:
//          array - an integer array
//          front - indicates the front of the array
//          back - indicates the back of the array
//          count - counts the amount of entires the user adds
//Methods:  constructor
//          void add            -   will add a number onto the queue
//          void delete         -   will remove a number from the queue
//          void reset          -   will reset the queue
//          printArray          -   will print the queue
class StraightLineArray extends Queue
{
    public int[] array;
    public int front;
    public int back;
    public int count;

    public StraightLineArray ()
    {
        this.front = 0;
        this.back = 0;
        this.array = new int [10];
        this.count = 1;
    }

    // Purpose: will add a value onto the queue using the count field
    // Parameters: Console c
    // Return Values: None
    public void add (Console c)
    {
        if (this.back == 10)
        {
            c.println ("The array is full, can't add!");
        }
        else
        {
            this.array [back] = this.count;
            this.back = this.back + 1;
            this.count = this.count + 1;
        }
    }

    // Purpose: will delete a value from the front of the queue
    // Parameters: Console c
    // Return Values: None
    public void delete (Console c)
    {
        if (front >= back)
        {
            c.println ("The array is empty, nothing to remove!");
        }
        else
        {
            this.array [front] = 0;
            front = front + 1;
        }
    }

    // Purpose: will reset the queue
    // Parameters: None
    // Return Values: None
    public void reset ()
    {
        this.array = new int [10];
        this.back = 0;
        this.front = 0;
        this.count = 0;
    }

    // Purpose: will print the queue
    // Parameters: Console c
    // Return Values: None
    public void printArray (Console c)
    {
        for (int t = 0 ; t < 10 ; t++)
        {
            if (this.array [t] == 0)
            {
                c.print ("[ ]");
            }
            else
            {
                c.print ("[" + this.array [t] + "]");
            }
        }
    }
}

//Class ShiftedArray (extends Queues)
//Fields:
//          array - an integer array
//          front - indicates the front of the array
//          back - indicates the back of the array
//          count - counts the amount of entires the user adds
//Methods:  constructor
//          void add            -   will add a number onto the queue
//          void delete         -   will remove a number from the queue
//          void reset          -   will reset the queue
//          printArray          -   will print the queue
class ShiftedArray extends Queue
{
    public int[] array;
    public int front;
    public int back;
    public int count;

    public ShiftedArray ()
    {
        this.front = 0;
        this.back = 0;
        this.array = new int [10];
        this.count = 1;
    }

    // Purpose: will add a value onto the queue using the count field
    // Parameters: Console c
    // Return Values: None
    public void add (Console c)
    {
        if (this.back == 10)
        {
            c.println ("The array is full, can't add!");
        }
        else
        {
            this.array [back] = this.count;
            this.back = this.back + 1;
            this.count = this.count + 1;
        }
    }

    // Purpose: will delete a value from the front of the queue and shift the rest of the array
    // Parameters: Console c
    // Return Values: None
    public void delete (Console c)
    {
        if (front == back)
        {
            c.println ("The array is empty, nothing to remove!");
        }
        else
        {
            for (int t = 0 ; t < this.back ; t++)
            {
                if (t == 9)
                {
                    this.array [t] = 0;
                }
                else
                {
                    this.array [t] = this.array [t + 1];
                }
            }
            this.back = this.back - 1;
        }
    }

    // Purpose: will reset the queue
    // Parameters: None
    // Return Values: None
    public void reset ()
    {
        this.array = new int [10];
        this.back = 0;
        this.front = 0;
        this.count = 1;
    }

    // Purpose: will print the queue
    // Parameters: Console c
    // Return Values: None
    public void printArray (Console c)
    {
        for (int t = 0 ; t < 10 ; t++)
        {
            if (this.array [t] == 0)
            {
                c.print ("[ ]");
            }
            else
            {
                c.print ("[" + this.array [t] + "]");
            }
        }
    }
}

/*Class Node
Purpose: create a Node class that contains data and next node
Fields:
    data -string value
    next -points to next node
Methods:
    constructor -creates a Node given all the data
    clone -creates a clone of the node
    toString -creates a string of all connected nodes
*/
class Node implements Cloneable
{
    public int data;
    public Node next;
    
    //Purpose: creates a Node given all the data
    //Parameters: info
    //Return: none
    public Node (int info)
    {
        data = info;
        next = null;
    }

    //Purpose: creates a clone of the node
    //Parameters: none
    //Return: Object
    public Object clone ()
        throws CloneNotSupportedException
    {
        Node newObject = (Node) super.clone ();
        if (next == null)
            newObject.next = null;
        else
            newObject.next = (Node) next.clone ();
        return newObject;
    }
    
    //Purpose: creates a string of all connected nodes
    //Parameters: none
    //Return: none
    public String toString ()
    {
        String s = "";
        s = s + "[" + this.data + "]";
        if (next != null)
            s = s + next.toString ();
        return s;
    }
}
//Class LinkedListQ (extends Queues)
//Fields:
//          front - indicates the front of the linked list
//          back - indicates the back of the linked list
//          count - counts the amount of entires the user adds
//Methods:  constructor
//          void add            -   will add a number onto the queue
//          void delete         -   will remove a number from the queue
//          void reset          -   will reset the queue
//          printArray          -   will print the queue
class LinkedListQ extends Queue
{
    public Node front;
    public Node back;
    public int count;

    public LinkedListQ ()
    {
        this.front = null;
        this.back = null;
        this.count = 1;
    }

    // Purpose: will add onto the linked list through the count field
    // Parameters: Console c
    // Return Values: None
    public void add (Console c)
    {
        Node newNode = new Node (this.count);
        if ((this.back == null) && (this.front == null))
        {
            this.front = newNode;
            this.back = newNode;
        }
        else
        {
            this.back.next = newNode;
            this.back = newNode;
        }
        this.count = this.count + 1;
    }

    // Purpose: will delete from the linked list
    // Parameters: Console c
    // Return Values: None
    public void delete (Console c)
    {
        if ((this.back == null) && (this.front == null))
        {
            c.println ("Nothing in the list!");
        }
        else
        {
            this.front = this.front.next;
        }
    }
    
    // Purpose: will reset the linked list queue
    // Parameters: Console c
    // Return Values: None
    public void reset ()
    {
        this.front = null;
        this.back = null;
        this.count = 1;
    }

    // Purpose: will convert the linked list queue into a string and print it
    // Parameters: Console c
    // Return Values: None
    public void toString (Console c)
    {
        Node newNode = this.front;
        if (this.front == null)
        {
            c.println ("null");
        }
        else
        {
            c.println (newNode.toString ());
        }
    }
}

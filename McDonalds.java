// Author: Frank Dong
// Date: Dec, 10, 2014
// Purpose: A McDonalds program which will simulate the amount of customers waiting in line
import java.awt.*;
import hsa.Console;

public class McDonalds
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
        c = new Console (30, 155);
        Restaurant obj1 = new Restaurant ();
        obj1.run (c);
        // Place your program here.  'c' is the output console
    } // main method
} // McDonalds class
/*
Class Customer
    A node that holds a customer's information used in a queue

Fields:
    orderTime   the order time the customer will have
    waitTime    how long the customer has waited
    next        the next customer in line

Methods:
    constructor
***********************************************************************************/
class Customer
{
    protected int orderTime;
    protected int waitTime;
    protected Customer next;

    /**************************************************
    Constructor for class Customer
    parameters: the max order time the customer may
                take
    return value: none
    **************************************************/
    public Customer (int x)
    {
        orderTime = (int) (Math.random () * x) + 1;
        waitTime = 0;
        next = null;
    }
}
/////////////////    Queue class    ////////////////////////////
/***********************************************************************************
Class Queue
    A link list of customers

Fields:
    first   the first customer in line

Methods:
    constuctor
    empty           checks if the queue(linklist) is empty
    add             adds a new customer to the back of the queue
    remove          removes the first customer from the queue
    customersWait   all customers in the queue has theur waitTime increased by 1
***********************************************************************************/
class Queue
{
    protected Customer list;
    protected Customer back;

    /**************************************************
    Constructor for class Queue
    parameters: none
    return value: none
    **************************************************/
    public Queue ()
    {
        this.list = null;
        this.back = null;
    }


    /**************************************************
    Method isEmpty
        checks if the queue is empty
        parameters: none
        return value: true or false
    **************************************************/

    public boolean isEmpty ()
    {
        return this.list == null;
    }


    public void add (int x)
    {
        Customer newNode = new Customer (x);
        if ((this.list == null) && (this.back == null))
        {
            this.list = newNode;
            this.back = newNode;
        }
        else if ((this.list == this.back) && (this.list != null))
        {
            this.list.next = newNode;
            this.back = newNode;
        }
        else
        {
            this.back.next = newNode;
            this.back = newNode;
        }
    }


    public Customer remove ()
    {
        Customer remove = this.list;
        if (!this.isEmpty ())
        {
            if (this.list.next == null)
            {
                this.list = null;
                this.back = null;
            }
            else
            {
                this.list = this.list.next;
                if (this.list.next == null)
                    this.back = this.list;
            }
        }
        else
            remove = null;
        return remove;
    }


    public void customerWait ()
    {
        Customer current = this.list;
        if (!this.isEmpty ())
        {
            while (current != null)
            {
                current.waitTime++;
                current = current.next;
            }
        }
    }


    public int length ()
    {
        int count = 0;
        Customer temp = this.list;
        Customer temp2 = this.back;
        if ((temp == null) && (temp2 == null))
        {
            count = 0;
        }
        else if ((temp == temp2) && (temp != null))
        {
            count = 1;
        }
        else
        {
            while (temp != temp2)
            {
                temp = temp.next;
                count = count + 1;
            }
        }
        return (count);
    }
}
/////////////////////    Cashier class   ////////////////////////
/***********************************************************************************
Class Cashier
cashier holds its queues statistics

Fields:
    inLine          holds the number of customers in the queue
    customerServed  holds the number of customers removed from queue
    freeTime        the amount of time the line was empty
    timeToFillOrder the amount of time until the next customer is served

Methods:
    constructor
***********************************************************************************/

class Cashier
{
    public int inLine;
    public int customersServed;
    public int freeTime;
    public int timeToFillOrder;

    /**************************************************
    Constructor for class Cashier
    parameters: none
    return value: none
    **************************************************/
    public Cashier ()
    {
        inLine = 0;
        customersServed = 0;
        freeTime = 0;
        timeToFillOrder = 0;
    }
}

class Restaurant
{
    public int n;
    public int totalMins;
    public int clock;
    public int arrivalProbability;
    public int maxOrderTime;
    public int totalWaitTime;
    public int avgWaitTime;
    public int totalLineLength;
    public int avgLineLength;
    public int totalCustomers;
    public int totalOrderTime;
    public boolean oneLine;
    public Queue[] custQue;
    public Queue[] servingQue;
    public Queue oneLineQue;
    public Cashier[] cashQue;

    public Restaurant ()
    {
        this.n = 0;
        this.totalMins = 0;
        this.clock = 0;
        this.arrivalProbability = 0;
        this.maxOrderTime = 0;
        this.totalWaitTime = 0;
        this.avgWaitTime = 0;
        this.totalLineLength = 0;
        this.avgLineLength = 0;
        this.totalCustomers = 0;
        this.totalOrderTime = 0;
        this.oneLine = false;
    }


    public void getInfo (Console c)
    {
        int temp = 0;
        do
        {
            c.println ("Please enter the number of cashiers: ");
            this.n = c.readInt ();
        }
        while (this.n < 1 || this.n > 5);

        do
        {
            c.println ("Is there only 1 queue?([1] for 1 Queue[2] for n(n=# of cashiers) Queues): ");
            temp = c.readInt ();
        }
        while (temp < 1 || temp > 2);
        if (temp == 1)
            this.oneLine = true;
        else
            temp = this.n;


        this.cashQue = new Cashier [this.n];
        for (int count = 0 ; count < this.n ; count++)
        {
            this.cashQue [count] = new Cashier ();
        }

        this.custQue = new Queue [temp];
        for (int count = 0 ; count < temp ; count++)
        {
            this.custQue [count] = new Queue ();
        }
        if (this.oneLine)
        {
            this.oneLineQue = new Queue ();
        }
        this.servingQue = new Queue [this.n];
        for (int count = 0 ; count < this.n ; count++)
            this.servingQue [count] = new Queue ();


        do
        {
            c.println ("Please enter the arrival probability of a customer: ");
            this.arrivalProbability = c.readInt ();
        }
        while (this.arrivalProbability < 1);

        do
        {
            c.println ("Please enter the max order time of a customer: ");
            this.maxOrderTime = c.readInt ();
        }
        while (this.maxOrderTime < 1);

        do
        {
            c.println ("Please enter how long the simulation will run: ");
            this.totalMins = c.readInt ();
        }
        while (this.totalMins < 1);
    }


    public void newCustomerArrives ()
    {
        int lengthOfQueue = 0;
        int count = 0;
        int shortestQueue = 0;
        int randomNum = 0;
        randomNum = (int) (Math.random () * this.arrivalProbability);
        if (randomNum == 0)
        {
            if (this.oneLine)
            {
                this.oneLineQue.add (this.maxOrderTime);
            }
            else
            {
                lengthOfQueue = this.cashQue [0].inLine;
                while (count < this.n)
                {
                    if (this.cashQue [count].inLine < lengthOfQueue)
                    {
                        lengthOfQueue = this.cashQue [count].inLine;
                        shortestQueue = count;
                    }
                    count++;
                }
                this.custQue [shortestQueue].add (this.maxOrderTime);
                this.cashQue [shortestQueue].inLine++;
            }
        }

    }


    public void serversDoJob ()
    {
        Customer removed = null;
        for (int count = 0 ; count < this.n ; count++)
        {
            if (this.cashQue [count].timeToFillOrder == 0)
            {
                removed = this.servingQue [count].remove ();

                if (removed != null)
                {
                    this.totalCustomers++;
                    this.totalOrderTime = this.totalOrderTime + removed.orderTime;
                    this.totalWaitTime = this.totalWaitTime + removed.waitTime;
                    this.cashQue [count].customersServed++;
                    this.cashQue [count].inLine--;
                    if (this.cashQue [count].inLine < 0)
                        this.cashQue [count].inLine = 0;
                }
                if (this.oneLine)
                {
                    if (this.oneLineQue.list != null)
                    {
                        removed = this.oneLineQue.remove ();
                        this.cashQue [count].timeToFillOrder = removed.orderTime;
                        this.cashQue [count].inLine++;
                        this.servingQue [count].list = removed;
                    }
                }
                else
                {
                    if (this.custQue [count].list != null)
                    {
                        removed = this.custQue [count].remove ();
                        this.cashQue [count].timeToFillOrder = removed.orderTime;
                        this.servingQue [count].list = removed;

                    }
                }
            }

            /* else
             {
                 removed = this.servingQue [count].remove ();
                 if (removed != null)
                 {
                     this.totalCustomers++;
                     this.totalOrderTime = this.totalOrderTime + removed.orderTime; //this.custQue [count].list.orderTime;
                     this.totalWaitTime = this.totalWaitTime + removed.waitTime; //this.custQue [count].list.waitTime;
                     this.cashQue [count].customersServed++;
                     this.cashQue [count].inLine--;
                     if (this.cashQue [count].inLine < 0)
                         this.cashQue [count].inLine = 0;
                 }
                 if (this.custQue [count].list != null)
                 {
                     removed = this.custQue [count].remove ();
                     this.cashQue [count].timeToFillOrder = removed.orderTime;
                     this.servingQue [count].list = removed;

                 }*/
        }
        for (int count = 0 ; count < this.n ; count++)
        {
            if (this.cashQue [count].timeToFillOrder == 0)
                this.cashQue [count].freeTime++;
            else
                this.cashQue [count].timeToFillOrder--;
        }
    }


    public void screenSetUp (Console c)
    {
        int count = 0;
        int subCount = 0;
        c.clear ();
        c.println ("------------------------------------------------------------MCDONALDS SIMULATION------------------------------------------------------------");
        c.println ("O = Customer");
        c.println ("");
        for (count = 0 ; count < this.n ; count++)
        {
            c.println ("");
            c.print ("Cashier " + (count + 1) + " ||");
            if (!this.oneLine)
            {
                if (this.servingQue [count].list != null)
                {
                    c.print (" O");
                    c.print (" (Order:" + this.servingQue [count].list.orderTime + "; Wait Time:" + this.servingQue [count].list.waitTime + ") ");
                }
                Customer current = this.custQue [count].list;
                for (subCount = 1 ; subCount < this.cashQue [count].inLine ; subCount++)
                {
                    c.print (" O");
                }
            }
            else
            {
                if (this.servingQue [count].list != null)
                {
                    c.print (" O");
                    c.print (" (Order:" + this.servingQue [count].list.orderTime + ";Wait Time:" + this.servingQue [count].list.waitTime + ") ");
                }
                if (count == this.n / 2)
                {
                    c.print ("        ");
                    Customer current = this.oneLineQue.list;
                    while (current != null)
                    {
                        c.print (" O");
                        current = current.next;
                    }
                }
            }
        }
    }


    public void printStatistics (Console c)
    {
        c.println ("\n");
        this.totalLineLength = 0;

        if (!this.oneLine)
        {
            for (int count = 0 ; count < this.n ; count++)
                this.totalLineLength += this.cashQue [count].inLine;
        }
        else
        {
            this.totalLineLength = this.oneLineQue.length () + 1;
            c.println ("Single Queue: " + this.totalLineLength);
        }

        if (this.totalCustomers == 0)
        {
            this.avgLineLength = 0;
            this.avgWaitTime = 0;
        }
        else
        {
            if (this.oneLine)
                this.avgLineLength = this.totalLineLength;
            else
                this.avgLineLength = this.totalLineLength / this.n;
            this.avgWaitTime = this.totalWaitTime / this.totalCustomers;
        }

        for (int count = 0 ; count < this.n ; count++)
        {
            c.print ("Cashier " + (count + 1) + ": [Customers Served: " + this.cashQue [count].customersServed + "; FreeTime: " + this.cashQue [count].freeTime + ";In Line " + this.cashQue [count].inLine + "]");
            c.println ("");
        }
        c.println ("");
        c.println ("Simulation Time: " + this.clock + "/" + this.totalMins);
        c.println ("Arrival Probability: 0-" + this.arrivalProbability);
        c.println ("Max Order Time: " + this.maxOrderTime);
        c.println ("Total Customers Served: " + this.totalCustomers);
        c.println ("Total Order Time: " + this.totalOrderTime);
        c.println ("Total Wait Time: " + this.totalWaitTime);
        c.println ("Average Wait Time: " + this.avgWaitTime);
        c.println ("Total Line Length: " + this.totalLineLength);
        c.println ("Average Line Length: " + this.avgLineLength);

    }


    public void run (Console c)
    {
        String seeSim = "";
        String next = "";
        c.println ("Do you wish to see every minute of the simulation (press '1'), or just the enstate (press '2'): ");
        seeSim = c.readString ();
        getInfo (c);
        c.clear ();
        this.screenSetUp (c);
        this.clock = 0;
        c.println ("\nPress anything to continue: ");
        c.getChar ();
        if (seeSim.equals ("1"))
        {
            while (this.clock < this.totalMins)
            {
                this.clock++;
                this.newCustomerArrives ();
                this.serversDoJob ();
                this.screenSetUp (c);
                this.printStatistics (c);
                c.println ("");
                c.println ("Press anything to continue: ");
                c.getChar ();
                //this.serversDoJob ();
                if (this.oneLine)
                {
                    Customer current = this.oneLineQue.list;
                    //for (int count = 0 ; count < this.n ; count++)
                    // {
                    //    if (this.servingQue [count].list != null)
                    //        this.servingQue [count].list.waitTime++;
                    // }
                    while (current != null)
                    {
                        current.waitTime++;
                        current = current.next;
                    }
                }
                else
                {
                    for (int count = 0 ; count < this.n ; count++)
                        this.custQue [count].customerWait ();
                }
            }
        }


        else if (seeSim.equals ("2"))
        {
            while (this.clock < this.totalMins)
            {
                this.clock++;
                this.newCustomerArrives ();
                this.serversDoJob ();
                if (this.oneLine)
                {
                    Customer current = this.oneLineQue.list;
                    while (current != null)
                    {
                        current.waitTime++;
                        current = current.next;
                    }
                }
                else
                {
                    for (int count = 0 ; count < this.n ; count++)
                        this.custQue [count].customerWait ();
                }
            }
            this.screenSetUp (c);
            this.printStatistics (c);


        }
    }
}



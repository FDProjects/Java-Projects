// The "BinarySearchTreeTest" class.
//Author: Frank Dong
//Data: Jan 19, 2015
//Purpose:  A program which implements the use of a binary tree.
import hsa.*;
import java.util.*;
import java.lang.Math;

class BinarySearchTreeTest
{
    static Console c = new Console ();

    public static void main (String[] args)
    {
        int programRun = 0;
        BinarySearchTree tree = new BinarySearchTree ();
        while (programRun == 0)
        {
            c.println ("Please select what you want to do");
            c.println ("a - add");
            c.println ("b - find level");
            c.println ("c - find sibling");
            String userChoice = c.readString ();

            if (userChoice.equals ("a"))
            {
                c.print ("Please enter the value you wish to add: ");
                int userValue = c.readInt ();
                tree.add(userValue);
            }
            else if (userChoice.equals ("b"))
            {
                c.print ("Please enter the node whose sibling you want to find: ");
                int userValue = c.readInt ();
                if (tree.root == null)
                {
                    c.println("No silbings, the tree is empty!");
                }
                else
                {
                    Node sibling = tree.sibling (userValue);
                    c.println();
                    c.println ("Sibling: " + sibling.data);
                }
            }
            else if (userChoice.equals ("c"))
            {
                c.print ("Please enter the value of the node whose level you want to find: ");
                int userValue = c.readInt ();
                if (tree.root == null)
                {
                    c.println ("N/a, the tree is empty!");
                }
                else
                {
                    c.println();
                    c.println ("Level: " + tree.level (userValue));
                }
            }
            c.println ("==========================Tree Display=================================");
            tree.printTree (c);
            c.println ("===========================Information=================================");            
            String preOrder = tree.preOrder ();
            if (preOrder.equals (""))
            {
                c.println ("PreOrder: N/A");
                c.println ("PostOrder: N/A");
                c.println ("InOrder: N/A");
            }
            else
            {
                c.println ("Pre-Order: " + preOrder);
                c.println ("Post-Order: " + tree.postOrder ());
                c.println ("In-Order: " + tree.inOrder ());
            }
            c.println ("Height: " + tree.height ());
            c.println ("Node Count: " + tree.nodes ());
            c.println ("Height Balanced: " + tree.heightBalanced ());
            c.println ("Perfectly Balanced: " + tree.perfectlyBalanced ());
            
            c.print ("Please enter 0 to continue the program, anything else to exit: ");
            programRun = c.readInt ();
            c.clear ();
        }
    }
}

//Class Node
//Fields:   int data    -   the information held by the Node
//          Node right  -   the next node to the right of the tree
//          Node left   -   the next node to the left of the tree
class Node
{
    public int data;
    public Node left;
    public Node right;

    public Node (int info)
    {
        this.data = info;
        this.left = null;
        this.right = null;
    }


    public Node ()
    {
        this (0);
    }
}
//Class QNode
//Fields:   Node data   -   the information held by the QNode
//          QNode next  -   the next node in the list
class QNode
{
    public Node data;
    public QNode next;

    public QNode (Node info)
    {
        this.data = info;
        this.next = null;
    }


    public QNode ()
    {
        this (null);
    }
}

//Class Queue
//Fields:   QNode front -   the front of the queue
//          QNode back  -   the back of the queue
//Methods:  add     -   adds a given node to the Queue
//          remove  -   removes a node at the front and returns removed node
//          empty   -   returns true if queue is empty
class Queue
{
    public QNode front;
    public QNode back;

    public Queue ()
    {
        this.front = null;
        this.back = null;
    }


    //add
    //Purpose:      adds a node at the end of the queue
    //Parameters:   Node x   -   the node being added
    //Output:       None
    public void add (Node x)
    {
        if (this.isEmpty ())
        {
            this.front = new QNode (x);
            this.back = this.front;
        }
        else
        {
            this.back.next = new QNode (x);
            this.back = this.back.next;
        }
    }


    //remove
    //Purpose:      removes a node at the front of the queue
    //Parameters:   None
    //Output:       the node that was removed
    public Node remove ()
    {
        Node ans;

        ans = this.front.data;
        this.front = this.front.next;
        return ans;
    }


    //isEmpty
    //Purpose:      returns true if array is empty
    //Parameters:   None
    //Output:       true or false
    public boolean isEmpty ()
    {
        boolean status = false;
        if (this.front == null)
            status = true;
        return status;
    }
}

//Class BinarySearchTree
//Fields:   Node root           -   the root of the tree
//Methods:  add                 -   adds a leaf to the tree with given info
//          find                -   returns the node with the given information
//          printTree           -   prints the tree in a "normal way" through recursion
//          rprintTree          -   prints the tree
//          preOrder            -   will return the tree in preOrder through recursion
//          rpreOrder           -   will return a string in preOrder
//          postOrder           -   will return the tree in postOrder through recursion
//          rpostOrder          -   will return a string in postOrder
//          inOrder             -   will return the tree in inOrder through recursion
//          rinOrder            -   will return a string in inOrder
//          height              -   will return the height of the tree through recursion
//          rheight             -   will return the height of the tree
//          nodes               -   will return the nodes in the tree through recursion
//          rnodes              -   will return the number of nodes in the tree
//          heightBalanced      -   will return whether a tree is height balanced through recursion
//          rheightBalanced     -   will determine whether a tree is height balanced
//          prefectlyBalanced   -   will return whether the tree is perfectly balanced through recursion
//          rperfectlyBalanced  -   will determine if the tree is perfectly balanced through the nodes of the tree
//          level               -   will return the level of a given node
//          sibling             -   will find the siblings of a given node
//          breadthOrderFirst   -   will returns a string with the tree in breadth order

class BinarySearchTree
{
    protected Node root;

    public BinarySearchTree ()
    {
        root = null;
    }


    //add
    //Purpose:      adds a leaf to the tree
    //Parameters:   int x   -   the info of the leaf being added
    //Output:       true or false
    public boolean add (int info)
    {

        if (this.root == null)
            this.root = new Node (info);
        else
        {
            Node ptr = this.root;
            while (ptr != null)
            {
                if (info < ptr.data)
                    if (ptr.left != null)
                        ptr = ptr.left;
                    else
                    {
                        ptr.left = new Node (info);
                        return true;
                    }
                else if (info > ptr.data)
                    if (ptr.right != null)
                        ptr = ptr.right;
                    else
                    {
                        ptr.right = new Node (info);
                        return true;
                    }
                else if (info == ptr.data)
                {
                    return false;
                }
            }
        }
        return true;
    }


    //find
    //Purpose:      finds the node with the given information
    //Parameters:   int x   -   the info of the node that is being looked for
    //Output:       returns the node with the given information
    public Node find (int x)
    {
        Node ptr;
        ptr = this.root;
        while (ptr != null && ptr.data != x)
            if (x < ptr.data)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        return ptr;
    }


    /************************************************
    Method:printTree
    Purpose:Prints the tree in a normal way.
        (Can't handle super wide trees.)
        -  Developed by Jonathan Chan, May 1997
        -  Java version by Edmund Wong, Dec 2000
        -  Modified by Ian Halliday, June 2001
    Parameters: The Console
    Return Value: None
    *************************************************/
    public void printTree (Console c)
    {
        c.setCursor
            (rprintTree (c, root, c.getRow ()), 1);
    }


    /************************************************
    Method: rprintTree
    Purpose: See above.
    Parameters: the console, the node being printed
                and the row to print on
    Return Value: The maximum row reached by
                  printing (allowing for useful
                  cursor placement)
    *************************************************/
    protected int rprintTree (Console c, Node n, int r)
    {
        int maxRow;
        if (n != null)
        {
            maxRow = rprintTree (c, n.left, r + 2);
            c.setCursor (r, c.getColumn ());
            c.print (n.data, 3);
            maxRow = Math.max (maxRow, rprintTree (c, n.right, r + 2));
            return maxRow;
        }
        return r - 1;
    }


    //preOrder
    //Purpose:      Returns the preOrder through a recursive method
    //Parameters:   None
    //Output:       String
    public String preOrder ()
    {
        return rpreOrder (root);
    }


    //rpreOrder
    //Purpose:      will return the string in preOrder
    //Parameters:   Node n
    //Output:       String s
    protected String rpreOrder (Node n)
    {
        String s = "";
        if (n != null)
        {
            s = s + n.data + ",";
            s = s + rpreOrder (n.left);
            s = s + rpreOrder (n.right);
        }
        return s;
    }


    //postOrder
    //Purpose:      Returns the postOrder through a recursive method
    //Parameters:   None
    //Output:       String
    public String postOrder ()
    {
        return rpostOrder (root);
    }


    //rpostOrder
    //Purpose:      will return the string in postOrder
    //Parameters:   Node n
    //Output:       String s
    protected String rpostOrder (Node n)
    {
        String s = "";
        if (n != null)
        {
            s = s + rpostOrder (n.left);
            s = s + rpostOrder (n.right);
            s = s + n.data + ",";
        }
        return s;
    }


    //inOrder
    //Purpose:      Returns the inOrder through a recursive method
    //Parameters:   None
    //Output:       String
    public String inOrder ()
    {
        return rinOrder (root);
    }


    //rinOrder
    //Purpose:      will return the string in inOrder
    //Parameters:   Node n
    //Output:       String s
    protected String rinOrder (Node n)
    {
        String s = "";
        if (n != null)
        {
            s = s + rinOrder (n.left);
            s = s + n.data + ",";
            s = s + rinOrder (n.right);
        }
        return s;
    }


    //height
    //Purpose:      Returns the height through a recursive method
    //Parameters:   None
    //Output:       integer
    public int height ()
    {
        return rheight (root);
    }


    //rheight
    //Purpose:      will return the height of a tree
    //Parameters:   Node n
    //Output:       int height
    protected int rheight (Node n)
    {
        int height = 1;
        int left = 0;
        int right = 0;
        if (n != null)
        {
            if (n.left != null)
            {
                left = left + rheight (n.left);
            }
            if (n.right != null)
            {
                right = right + rheight (n.right);
            }
        }
        if (left < right)
        {
            height = height + right;
        }
        else
        {
            height = height + left;
        }
        return height;
    }


    //nodes
    //Purpose:      Returns the height through a recursive method
    //Parameters:   None
    //Output:       integer
    public int nodes ()
    {
        return rnodes (root);
    }


    //rinOrder
    //Purpose:      will return the nodes in a tree
    //Parameters:   Node n
    //Output:       int amount
    protected int rnodes (Node n)
    {
        int amount = 0;
        if (n != null)
        {
            amount = amount + 1;
            amount = amount + rnodes (n.left);
            amount = amount + rnodes (n.right);
        }
        return amount;
    }


    //heightBalanced
    //Purpose:      Returns whether a the tree is perfectly height balanced through a recursive method
    //Parameters:   None
    //Output:       True or False
    public boolean heightBalanced ()
    {
        return rheightBalanced (root);
    }


    //rheightBalanced
    //Purpose:      determines whether a tree is height balanced
    //Parameters:   Node n
    //Output:       boolean isBalanced
    protected boolean rheightBalanced (Node n)
    {
        boolean isBalanced = false;
        if (n == null)
        {
            isBalanced = true;
        }
        else
        {
            isBalanced = (rheightBalanced (n.left)) && (rheightBalanced (n.right)) && (Math.abs (rheight (n.left) - rheight (n.right)) <= 1);
        }
        return isBalanced;
    }


    //perfectlyBalanced
    //Purpose:      Returns whether a given value is perfectly balanced through a recursive method
    //Parameters:   None
    //Output:       True or False
    public boolean perfectlyBalanced ()
    {
        return rperfectlyBalanced (root);
    }


    //rperfectlyBalanced
    //Purpose:      determines whether a tree is perfectly balanced based on the amount of nodes it has
    //Parameters:   Node n
    //Output:       boolean isBalanced
    public boolean rperfectlyBalanced (Node n)
    {
        boolean isBalanced = false;
        if (n == null)
        {
            isBalanced = true;
        }
        else
        {
            isBalanced = (rperfectlyBalanced (n.left)) && (rperfectlyBalanced (n.right)) && (Math.abs (rnodes (n.left) - rnodes (n.right)) <= 1);
        }
        return isBalanced;
    }


    //level
    //Purpose:      returns the level of a given integer
    //Parameters:   int x
    //Output:       int level
    public int level (int givenLevel)
    {
        int treeLevel = 0;
        Node treeLocation;
        treeLocation = this.root;
        while (treeLocation.data != givenLevel && treeLocation != null)
            if (givenLevel < treeLocation.data)
            {
                treeLocation = treeLocation.left;
                treeLevel = treeLevel + 1;
            }
            else
            {
                treeLocation = treeLocation.right;
                treeLevel = treeLevel + 1;
            }
        return treeLevel;
    }


    //sibling
    //Purpose:      returns the node of the sibling of the given int
    //Parameters:   int x
    //Output:       Node sibling
    public Node sibling (int userNode)
    {
        boolean found = false;
        Node treeLocation;
        Node sibling;
        treeLocation = this.root;
        while ((found == false) && (treeLocation != null) && (treeLocation.data != userNode))
        {
            if (userNode < treeLocation.data)
            {
                if (treeLocation.left.data == userNode)
                {
                    found = true;
                }
                else
                {
                    treeLocation = treeLocation.left;
                }
            }
            else
            {
                if (treeLocation.right.data == userNode)
                {
                    found = true;
                }
                else
                {
                    treeLocation = treeLocation.right;
                }
            }
        }
        if (treeLocation.left.data == userNode)
        {
            sibling = treeLocation.right;
        }
        else
        {
            sibling = treeLocation.left;
        }
        return sibling;
    }


    //breadthFirstOrder
    //Purpose:      will return a string with a tree in breadth order
    //Parameters:   None
    //Output:       String order
    public String breadthFirstOrder ()
    {
        Node x;
        String tree = "";
        Queue queue = new Queue ();
        if (this.root != null)
        {
            queue.add (this.root);
            while (queue.isEmpty () != true)
            {
                x = queue.front.data;
                queue.remove ();
                tree = tree + x.data;
                if (x.left != null)
                {
                    queue.add (x.left);
                }
                if (x.right != null)
                {
                    queue.add (x.right);
                }
            }
        }
        return tree;
    }
}



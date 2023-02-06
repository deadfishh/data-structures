public class HashTable
{
    private Node[] table;
    private double loadFactor = 0.75;
    public int tableSize = 7;


    public HashTable(String input)
    {
        input = input.toLowerCase();
        makeHash(input);
    }


    // makes the hashmap
    public void makeHash(String input)
    {
        table = new Node[tableSize];
        String[] s = input.split("\\P{Alpha}+");
        // inserting the key from the array of strings
        for (int i = 0; i < s.length; i++)
        {
            insert(s[i]);
        }
    }


    // returns the node if the corresponding key is in the hashtable
    public Node search(String key)
    {
        // java hash function
        int hash = Math.abs(key.hashCode()) % tableSize;
        Node test = table[hash];
        // nothing is in the expected place
        if (test == null)
        {
            return null;
        }
        // the key is in the expected place (return it)
        if (test.data.equals(key))
        {
            return table[hash];
        }
        // otherwise start looking though the linked list
        Node n = table[hash];
        while (n.next != null)
        {
            // we found it, so we return the node
            if (n.data.equals(key))
            {
                return n;
            }
            n = n.next;
        }
        // n.next is null, but this is the desired node
        if (n.data.equals(key))
        {
            return n;
        }
        // n.next is null, and this is not the desired node
        return null;
    }


    // returns true if the key is there
    public boolean contains(String key)
    {
        Node n = search(key);
        // if search does not return null, it's here
        if (n != null)
        {
            return true;
        }
        // otherwise return false
        return false;
    }


    // inserts a key into the hashtable
    public void insert(Node node)
    {
        // index is the expected place for it
        String key = node.data;
        int index = Math.abs(key.hashCode()) % tableSize;
        // seeing if it already exists there
        // if it does, increment occurances, check the size, and return
        if (contains(key))
        {
            Node here = search(key);
            here.occ++;
        }
        // seeing if there's a spot in the expected place
        else if (table[index] == null)
        {
            table[index] = node;
        }
        // otherwise we traverse the linked list
        else
        {
            Node n = table[index];
            while (n.next != null)
            {
               n = n.next;
            }
            // the next node is null, so we put it here
            n.next = node;
        }
        checkSize();
    }

    
    // inserts a string (not a node)
    public void insert(String key)
    {
        key = key.toLowerCase();
        Node n = new Node(key);
        insert(n);
    }


    // checks to see if the array is big enough
    public void checkSize()
    {
        // it's a double so it can be divided properly
        double count = 0;
        for (int i = 0; i < tableSize; i++)
        {
            // counting all the non-null spaces
            if (table[i] != null)
            {
                count++;
            }
        }
        // if it's too big, make a new array and rehash it
        if (count / tableSize > loadFactor)
        {
            reHash();
        }
    }


    // rehashes the array when it's too small
    public void reHash()
    {
        // double the table size
        tableSize *= 2;
        Node[] old = table;
        table = new Node[tableSize];
        // inserting everything from the old hashtable
        for (int i = 0; i < old.length; i++)
        {
            Node acorn = old[i];
            // if it's null, keep going
            if (acorn == null)
            {
                continue;
            }
            // otherwise, insert the node (not the key)
            recur(acorn);
        }
    }


    // separates the nodes using recursion
    public void recur(Node n)
    {
        // base case: next is null, so we just insert this one
        if (n.next == null)
        {
            insert(n);
        }
        // move this node to the top of the stack and continue
        else
        {
            recur(n.next);
            // make next null (it's already been inserted) and then insert n
            n.next = null;
            insert(n);
        }
    }


    // removes a string (doesn't decrement occurances, removes the whole node)
    public void remove(String key)
    {
        key = key.toLowerCase();
        Node n = search(key);
        // if n is null we know it's not here
        if (n == null)
        {
            return;
        }
        // change data to 'REMOVED' so we know to not ignore it
        // won't cause any problems with the string 'removed' since it's in all caps
        n.data = "REMOVED";
    }

    public void wordCount()
    {
        System.out.println(this);
    }


    @Override
    public String toString()
    {
        String answer = "";
        // traversing through the table
        for (int i = 0; i < tableSize; i++)
        {
            // if the spot is null, keep going
            if (table[i] == null)
            {
                continue;
            }
            // add the node's toString() method
            answer += "\n" + table[i].toString();
        }
        return answer;
    }
}
import java.util.*;

public class Node implements Comparable<Node>
{
    String name;
    ArrayList<Node> edges;
    int distance;
    public Node()
    {
        name = null;
        edges = new ArrayList<Node>();
        distance = Integer.MAX_VALUE;
    }

    public Node(String name)
    {
        this.name = name;
        edges = new ArrayList<Node>();
        distance = Integer.MAX_VALUE;
    }
    
    
    // adds and edge to the list
    public void addEdge(Node n)
    {
        edges.add(n);
    }


    // removes an edge from a node
    public void removeEdge(Node n)
    {
        // traverse through the edges
        for (int i = 0; i < edges.size(); i++)
        {
            // if it equals the desired edge, remove it and exit the method
            if (edges.get(i).equals(n))
            {
                edges.remove(i);
                return;
            }
        }
    }

    
    // returns true if their names are equal, false otherwise
    public boolean equals(Node other)
    {
        return (this.name.equals(other.name));
    }


    @Override
    public String toString()
    {
        String answer = this.name;
        answer += "... ";
        for (int i = 0; i < edges.size(); i++)
        {
            answer += edges.get(i).name + ", ";
        }
        return answer.substring(0, answer.length() - 2);
    }


    @Override
    public int compareTo(Node other)
    {
        return this.name.compareTo(other.name);
    }
}
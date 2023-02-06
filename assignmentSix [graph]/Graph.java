import java.util.*;

public class Graph
{
    ArrayList<Node> nodeList;

    public Graph()
    {
        nodeList = new ArrayList<Node>();
    }


    // adds a node to the graph
    public boolean addNode(String name)
    {
        Node look = findNode(name);
        // if findNode() doesn't return null, it's already there
        if (look != null)
        {
            return false;
        }
        // otherwise add it and return true
        Node n = new Node(name);
        nodeList.add(n);
        return true;
    }


    // add multiple nodes
    public boolean addNodes(String[] nodes)
    {
        // for each String in nodes, add it to the graph
        for (String s : nodes)
        {
            addNode(s);
        }
        return true;
    }


    // add an edge between two nodes (undirected)
    public boolean addEdge(String from, String to)
    {
        Node fromNode = findNode(from);
        Node toNode = findNode(to);
        // if either of them are null, return false
        if (fromNode == null || toNode == null)
        {
            return false;
        }
        // otherwise add it going in both directions
        fromNode.addEdge(toNode);
        toNode.addEdge(fromNode);
        return true;
    }


    // add multiple edges between nodes
    public boolean addEdges(String from, String[] tolist)
    {
        // for each String in tolist, add it both ways
        for (String s : tolist)
        {
            addEdge(from, s);
        }
        return true;
    }


    // remove a node by name
    public boolean removeNode(String name)
    {
        Node n = findNode(name);
        // if you can't find it, return false
        if (n == null)
        {
            return false;
        }
        // otherewise remove it
        nodeList.remove(n);
        // search for it within the other nodes' edges and remove it there too
        for (int i = 0; i < nodeList.size(); i++)
        {
            nodeList.get(i).removeEdge(n);
        }
        return true;
    }


    // remve multiple nodes
    public boolean removeNodes(String[] nodes)
    {
        // remove each node in the array
        for (String s : nodes)
        {
            removeNode(s);
        }
        return true;
    }


    // print the graph
    public void printGraph()
    {
        // traverse through the graph
        for (Node n : nodeList)
        {
            System.out.println(n);
        }
    }


    // look for a node
    public Node findNode(String name)
    {
        // traverse through the graph
        for (int i = 0; i < nodeList.size(); i++)
        {
            Node temp = nodeList.get(i);
            // if you find it, return that node
            if (temp.name.equals(name))
            {
                return temp;
            }
        }
        // otherwise return null
        return null;
    }


    // depth-first search algorithm
    public String[] DFS(String from, String to)
    {
        // create a stack to store the nodes to visit
        Stack<Node> stack = new Stack<Node>();
        Node fromNode = findNode(from);
        Node toNode = findNode(to);
        // if one of the nodes isn't there, there's no connection
        if (fromNode == null || toNode == null)
        {
            return null;
        }
        stack.push(fromNode);
        // create an arraylist for the visited nodes
        ArrayList<Node> visited = new ArrayList<Node>();
        // keep looping while the stack is not empty
        while (!stack.isEmpty())
        {
            // pop the last vertex from the stack
            Node vertex = stack.pop();
            // skip this vertex if it has already been visited
            if (visited.contains(vertex))
            {
                continue;
            }
            // add the current vertex to the visited arraylist
            visited.add(vertex);
            // if we've found our desination, we can stop
            if (vertex.equals(toNode))
            {
                break;
            }
            // get the neighbors of the current vertex
            ArrayList<Node> neighbors = vertex.edges;
            // add the neighbors to the stack
            stack.addAll(neighbors);
        }
        // if the ending node was never visited, we return null
        if (!visited.contains(toNode))
        {
            return null;
        }
        // turn the answer into an array of strings
        String[] answer = listToString(visited);
        return answer;
    }


    // breadth-first search algorithm
    public String[] BFS(String from, String to, String neighborOrder)
    {
        Node fromNode = findNode(from);
        Node toNode = new Node(to);
        // if one of the nodes isn't there, there's no connection
        if (fromNode == null || toNode == null)
        {
            return null;
        }
        // create a queue to store the vertices to visit
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(fromNode);
        // create an arraylist to store the visited vertices
        ArrayList<Node> visited = new ArrayList<Node>();
        // keep looping while the queue is not empty
        while (!queue.isEmpty())
        {
            Node vertex = queue.remove();
            // skip this vertex if it has already been visited
            if (visited.contains(vertex))
            {
                continue;
            }
            // add the current vertex to the visited set
            visited.add(vertex);
            // if we've found the destination, we can leave the loop
            if (vertex.equals(toNode))
            {
                break;
            }
            // get the neighbors of the current vertex
            ArrayList<Node> neighbors = vertex.edges;

            if (neighborOrder.equals("alphabetical"))
            {
                Collections.sort(neighbors);
            }
            else if (neighborOrder.equals("reverse"))
            {
                Collections.sort(neighbors, Collections.reverseOrder());
            }
            else
            {
                return null;
            }
            // add the neighbors to the queue
            queue.addAll(neighbors);
        }
        // if the ending node was never visited, we return null
        if (queue.isEmpty() && !visited.contains(toNode))
        {
            return null;
        }
        // turn the arraylist into an array of strings
        String[] answer = listToString(visited);
        return answer;
    }


    // turns an arraylist of nodes into an array of strings
    public String[] listToString(ArrayList<Node> list)
    {
        // if there's no list, return null
        if (list == null)
        {
            return null;
        }
        String[] answer = new String[list.size()];
        // traverse through the arraylist
        for (int i = 0; i < list.size(); i++)
        {
            answer[i] = list.get(i).name;
        }
        return answer;
    }


    // shortest algorithm
    public String[] shortestPath(String from, String to)
    {
        ArrayList<Node> answer = getShortestPath(from, to);
        return listToString(answer);
    }


    // shortest path helper method (does all the work)
    public ArrayList<Node> getShortestPath(String from, String to)
    {
        // create a queue to hold the nodes that we need to visit
        Queue<Node> queue = new LinkedList<Node>();
        // find the source and destination nodes
        Node source = findNode(from);
        Node dest = findNode(to);
        // if one of them isn't there, return null
        if (BFS(from, to, "alphabetical") == null)
        {
            return null;
        }
        // enqueue the source node in the queue.
        queue.add(source);
        // set the distance of the source node to 0, since it is the starting point
        source.distance = 0;
        // while there are still nodes in the queue, continue searching
        while (!queue.isEmpty())
        {
            Node current = queue.remove();
            // if we have reached the destination node, then we can stop searching
            if (current == dest)
            {
                break;
            }
            // otherwise, visit each of the current node's neighbors
            for (Node edge : current.edges)
            {
                // if the neighbor node has not been visited, then enqueue it in the queue
                if (edge.distance == Integer.MAX_VALUE)
                {
                    // set the distance of the neighbor node to be one more than the current node's distance
                    edge.distance = current.distance + 1;
                    queue.add(edge);
                }
            }
        }
        // create a list to hold the nodes on the shortest path
        ArrayList<Node> path = new ArrayList<Node>();
        // if the destination node has a distance, then we have found a shortest path
        if (dest.distance == Integer.MAX_VALUE)
        {
            return null;
        }
        // start at the destination node, and work backwards to find the path
        Node look = dest;
        while (look != source)
        {
            // add the current node to the beginning of the list
            path.add(0, look);
            // find the next node in the path by looking for a neighbor node with a distance that is one less than current
            for (Node edge : look.edges)
            {
                if (edge.distance == look.distance - 1)
                {
                    look = edge;
                    break;
                }
            }
        }
        path.add(0, source);
        fixDistances();
        return path;
    }


    // returning the second shortest path
    public String[] secondShortestPath(String from, String to)
    {
        ArrayList<Node> answer = new ArrayList<Node>();
        ArrayList<Node> firstShortest = getShortestPath(from, to);
        // if there's no shortest path, they aren't connected
        if (firstShortest == null)
        {
            return null;
        }
        // traversing through the first shortest path, looking at all the edges
        for (int i = 0; i < firstShortest.size() - 1; i++)
        {
            Node temp = firstShortest.get(i);
            Node edge = firstShortest.get(i + 1);
            // remove the edge going both ways
            temp.removeEdge(edge);
            edge.removeEdge(temp);
            // now caluclating the shortest path with this one edge missing
            ArrayList<Node> secondShortest = getShortestPath(from, to);
            // if it's smaller than the current second shortest path, this becomes the new shortest path
            if (secondShortest != null && (secondShortest.size() >= firstShortest.size()|| secondShortest.size() < answer.size()))
            {
                answer = secondShortest;
            }
            // make the distances normal (max value) and reconnect the edges
            fixDistances();
            temp.addEdge(edge);
            edge.addEdge(temp);
        }
        return listToString(answer);
    }


    // make all the distances max value, how they should be
    public void fixDistances()
    {
        // traversing through the graph
        for (Node n : nodeList)
        {
            n.distance = Integer.MAX_VALUE;
        }
    }


    // printing an arraylist
    public void printAL(ArrayList<Node> list)
    {
        // if there's no list, return
        if (list == null)
        {
            return;
        }
        // otherwise, print each thing in the list
        for (Node n : list)
        {
            System.out.println(n.name);
        }
    }
}

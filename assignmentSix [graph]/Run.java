public class Run
{
    public static void main(String[] args)
    {
        Graph g = new Graph();
        String[] nodes = {"ashwin", "rachel", "eva", "puch", "natalie", "alex", "will meyer", "vince", "elizabeth"};
        g.addNodes(nodes);
        g.addEdge("ashwin", "rachel");
        g.addEdge("alex", "eva");
        g.addEdge("rachel", "puch");
        g.addEdge("ashwin", "natalie");
        g.addEdge("will meyer", "elizabeth");
        g.addEdge("will meyer", "eva");
        g.addEdge("ashwin", "eva");
        g.addEdge("vince", "elizabeth");
        g.addEdge("puch", "natalie");
        g.addEdge("alex", "natalie");
        String personA = "eva";
        String personB = "rachel";
        System.out.println("\nshortest path");
        toString(g.shortestPath(personA, personB));
        System.out.println("second shortest path\n");
        toString(g.secondShortestPath(personA, personB));
    }


    // printing an array of strings
    public static void toString(String[] arr)
    {
        // if it's null, return
        if (arr == null)
        {
            return;
        }
        // otherwise print each element
        for (String s : arr)
        {
            System.out.println(s);
        }
    }
}

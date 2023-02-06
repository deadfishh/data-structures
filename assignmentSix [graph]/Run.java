package weights;

public class WeightedRun
{
    public static void main(String[] args)
    {
        Graph g = new Graph();
        String[] people = {"chicago", "los angeles", "cleveland", "little rock", "st jefferson", "springfield", "las vegas", "boseman", "boston"};
        g.addNodes(people);
        g.addEdge("chicago", "boston");
        g.addEdge("boston", "chicago");
        g.addEdge("little rock", "los angeles");
        g.addEdge("cleveland", "los angeles");
        g.addEdge("chicago", "los angeles");
        g.addEdge("st jefferson", "los angeles");
        g.addEdge("los angeles", "cleveland");
        g.addEdge("las vegas", "springfield");
        g.addEdge("springfield", "las vegas");
        g.addEdge("boseman", "st jefferson");
        g.addEdge("boston", "st jefferson");
        g.getShortestPath("chicago", "st jefferson");
        g.printGraph();
    }
}

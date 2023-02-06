public class Node
{
    public String data;
    public int occ;
    public Node next;

    public Node(String data)
    {
        this.data = data;
        this.occ = 1;
    }
    
    @Override
    public String toString()
    {
        // base case #1: we stop here (and the data says removed which we don't print)
        if (this.next == null && this.data.equals("REMOVED"))
        {
            return "";
        }
        // base case #2; we stop here and print the data
        else if (this.next == null)
        {
            return data + "=" + occ;
        }
        // if it says removed, we skip it
        else if (this.data.equals("REMOVED"))
        {
            return next.toString();
        }
        return data + "=" + occ + ", " + next.toString();
    }
}

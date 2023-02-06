package assignmentTwo;

public class MyLinkedList
{
    public Node head;
    public int size;

    public MyLinkedList()
    {
        this.head = null;
        this.size = 0;
    }


    // add data to the front of the list
    public void add(int data)
    {
        Node n = new Node(data);
        n.next = head;
        head = n;
    }


    // reverses the linked list in O(n) with constant space
    public void reverse()
    {
        // establishes variables and sets head.next to null
        Node n = head;
        Node nextNode = n.next;
        n.next = null;
        Node previous = n;
        n = nextNode;

        // making the pointer point to the previous node for each subsequent node
        while(n.next != null)
        {
            nextNode = n.next;
            n.next = previous;
            previous = n;
            head = n;
            n = nextNode;
        }

        // making the head the last node (it's now backwards)
        n.next = previous;
        head = n;
    }


    @Override
    public String toString()
    {
        String answer = "";
        Node n = head;
        while (n.next != null)
        {
            answer += n.data + " ";
            n = n.next;
        }
        answer += n.data;
        return answer;
    }
}
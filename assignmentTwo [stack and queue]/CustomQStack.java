package assignmentTwo;
import java.util.Queue;
import java.util.LinkedList;

public class CustomQStack
{
    Queue<Integer> mainQ;   
    int size; 
   
    public CustomQStack()
    {
        mainQ = new LinkedList<Integer>();
        size = 0;
    }


    // adds an item to the top of the stack
    public void push(int data)
    {
        mainQ.add(data);
        // making each item behind 'data' in the queue
        for (int i = 0; i < size; i++)
        {
            int temp = mainQ.poll();
            mainQ.add(temp);
        }
        size++;
    }


    // returns and removes the top of the stack
    public int pop()
    {
        size--;
        return mainQ.poll();
    }


    // returns true if stack is empty
    public boolean empty()
    {
        return mainQ.isEmpty();
    }


    // returns but does not remove the top of the stack
    public int peek()
    {
        return mainQ.peek();
    }


    @Override
    public String toString()
    {
        String answer = "";
        for (int data : mainQ)
        {
            answer += data + " ";
        }
        return answer;
    }
}
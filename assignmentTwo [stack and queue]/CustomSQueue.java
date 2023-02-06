package assignmentTwo;
import java.util.Stack;

public class CustomSQueue
{
    Stack<Integer> mainStack;
    Stack<Integer> helper;
    int size;

    public CustomSQueue()
    {
        mainStack = new Stack<Integer>();
        helper = new Stack<Integer>();
        size = 0;
    }

    
    // adds data to the bottom of the stack
    public void add(int data)
    {
        toHelper();
        mainStack.push(data);
        toMain();
        size++;
    }


    // returns but does not remove the bottom of the stack
    public int peek()
    {
        toHelper();
        int temp = helper.peek();
        toMain();
        return temp;
    }


    // returns and removes the bottom of the stack
    public int poll()
    {
        toHelper();
        int temp = helper.pop();
        size--;
        toMain();
        return temp;
    }


    // puts data from the main stack to the helper stack
    private void toHelper()
    {
        if (mainStack.isEmpty())
        {
            return;
        }
        for (int i = 0; i < size; i++)
        {
            helper.push(mainStack.pop());
        }
    }


    // puts data from the helper stack to the main stack
    private void toMain()
    {
        if (helper.isEmpty())
        {
            return;
        }
        for (int i = 0; i < size; i++)
        {
            mainStack.push(helper.pop());
        }
    }


    @Override
    public String toString()
    {
        String answer = "";
        toHelper();
        for (int data : helper)
        {
            answer += data + " ";
        }
        toMain();
        return answer;
    }

}
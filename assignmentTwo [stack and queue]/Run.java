package assignmentTwo;


public class Run
{
     // initializing qstack, squeue, and the linked list
     private static MyLinkedList list = new MyLinkedList();
     private static CustomQStack staque = new CustomQStack();
     private static CustomSQueue cue = new CustomSQueue();

    public static void main(String[] args)
    {

        // adding numbers 0 - 9 to each one
        for (int i = 0; i < 10; i++)
        {
            staque.push(i);
            cue.add(i);
            list.add(i);
        }

        testList();
        testStaque();
        testCue();
    }


    // testing the list's reverse method
    public static void testList()
    {
        System.out.println("list " + list);
        list.reverse();
        System.out.println("list " + list);
    }


    // testing the stack's methods
    public static void testStaque()
    {
        System.out.println(staque);
        staque.push(3);
        staque.push(17);
        if (staque.empty())
            staque.push(50);
        else
            staque.push(42);
        if (staque.pop() == 42)
        {
            System.out.println("the meaning of the life is 42!");
        }
        System.out.println(staque.peek());
        System.out.println("stack " + staque);
    }


    // testing the queue's methods
    public static void testCue()
    {
        System.out.println("queue " + cue);
        cue.add(24);
        cue.add(19);
        cue.add(10);
        System.out.println(cue.peek());
        for (int i = 0; i < 4; i++)
        {
            System.out.println(cue.poll());
        }
        System.out.println("queue " + cue);
        
    }
}

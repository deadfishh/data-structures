package assignmentOne;

public class FibBag
{
    // instance variables
    int[] numbers;
    int size;

    
    // fills the bag with the first n fib numbers
    public FibBag(int n)
    {
        if (n <= 0)
        {
            return;
        }
        numbers = setBag(n);
        size = n;
    }


    // makes the bag with the first n terms of the fib sequence
    public int[] setBag(int n)
    {
        int[] answer = new int[n];
        answer[0] = 0;
        // no adding is necessary and n = 1
        if (n == 1 ) 
        {
            return answer;
        }
        answer[1] = 1;
        // no adding is necessary and n = 2
        if (n == 2)
        {
            return answer;
        }
        // adding becomes neccessary
        int previous1 = 1;
        int previous2 = 1;
        int recent = 0;
        answer[2] = 1;
        for (int i = 3; i < n; i++) 
        {
            recent = previous1 + previous2;
            answer[i] = recent;
            previous1 = previous2;
            previous2 = recent;
        }
        return answer;
    }


    // returns the numbers of unique items in the bag
    public int numItems()
    {
        int count = 0;
        String tried = "";
        for (int i = 0; i < size; i++)
        {
            String temp = numbers[i] + " ";
            // this is not a unique item
            if (tried.indexOf(temp) != -1)
            {
                continue;
            }
            // this is a unique item; count and add it to the string
            else
            {
                count++;
                tried += temp + " ";
            }
        }
        return count;
    }


    // returns true if the bag contains the item, false otherwise
    public boolean ifContains(int item)
    {
        for (int i = 0; i < size; i++)
        {
            if (numbers[i] == (item))
            {
                return true;
            }
        }
        return false;
    }


    // returns a random item in the bag without removal
    public int grab()
    {
        int random = (int)(Math.random() * size);
        return numbers[random];
    }


    // adds an item to the end of the bag and increments the size
    public void add(int item)
    {
        int[] answer = new int[size + 1];
        for (int i = 0; i < size; i++)
        {
            answer[i] = numbers[i];
        }
        answer[size] = item;
        numbers = answer;
        size++;
    }


    // remove and item and decrement the size
    public void remove(int item)
    {
        boolean found = false;
        int index = 0;
        for (int i = 0; i < size; i++)
        {
            if (numbers[i] == item)
            {
                found = true;
                index = i;
            }
        }
        // the int item is not in the array
        if (!found)
        {
            return;
        }
        // it is in the array
        int[] answer = new int[size - 1];
        for (int i = 0; i < size; i++)
        {
            // up until the removed item everything is the same
            if (i < index)
            {
                answer[i] = numbers[i];
            }
            // don't add the removed item to the new array 
            else if (i == index)
            {
                continue;
            }
            // everything is now off by one going forwards
            else if (i > index)
            {
                answer[i - 1] = numbers[i];
            }
        }
        size--;
        numbers = answer;
    }

 
    @Override
    public String toString()
    {
        String answer = "";
        for (int i = 0; i < size; i++)
        {
            answer += numbers[i] + " ";
        }
        return answer;
    }
}

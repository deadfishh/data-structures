package assignmentOne;

public class Fib
{
    public static void main(String[] args)
    {
        int n = 20;
        System.out.println(iterativeFib(n));
    }


    // big o = O(n)
    public static int iterativeFib(int n)
    {
        // no zeroeth or negative term, AND the first term is equal to zero
        if (n <= 1)
        {
            return 0;
        }
        // second term is one (nothing to add it with)
        if (n == 2) 
        {
            return 1;
        }
        // if we have more than two terms, we need to start adding them up
        int previous1 = 0;
        int previous2 = 1;
        int recent = 0;
        for (int i = 2; i < n; i++) 
        {
            recent = previous1 + previous2;
            previous1 = previous2;
            previous2 = recent;
        }
        return recent;
    }


    // big o = O(2^n)
    public static int recursiveFib(int n)
    {
        // there is no zeroeth or negative term
        if (n <= 0)
        {
            return 0;
        }
        // base case
        if (n <= 3)
        {
            return 1;
        }
        // recursion
        return recursiveFib(n - 1) + recursiveFib(n - 2);
    }
}

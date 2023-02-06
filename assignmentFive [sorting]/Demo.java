public class Demo
{
    public static void main(String[] args)
    {
        testyMethod(10);
        testyMethod(20);
        testyMethod(50);
        testyMethod(100);
        testyMethod(200);
        testyMethod(500);
        testyMethod(1000);
        testyMethod(2000);
        testyMethod(5000);
    }
    

    // timer method
    public static void timer(Sort s, int[] arr)
    {
        long start = System.nanoTime();
        s.insertionSort(arr);
        //Arrays.sort(arr);
        long end = System.nanoTime();
        System.out.println((end - start) / Math.pow(10, 6) ); // prints the total time elapsed in milliseconds
    }


    // prints the timer for random, sorted, and unsorted arrays of 'num' size
    public static void testyMethod(int num)
    {
        System.out.println("\narray size: " + num);
        Sort s = new Sort();
        // random
        int[] random = s.generateRandomArray(num);
        System.out.print("random: ");
        timer(s, random);
        // sorted
        int[] sorted = sorted(num);
        System.out.print("sorted: ");
        timer(s, sorted);
        // reverse
        int[] reverse = reverse(num);
        System.out.print("reverse: ");
        timer(s, reverse);
    }


    // creates a reverse-sorted array
    private static int[] reverse(int num)
    {
        int[] answer = new int[num];
        for (int i = 0; i < num; i++)
        {
            answer[i] = i;
        }
        return answer;
    }


    // creates a sorted array
    private static int[] sorted(int num)
    {
        int[] answer = new int[num];
        for (int i = num - 1; i >= 0; i--)
        {
            answer[i] = i;
        }
        return answer;
    }
}


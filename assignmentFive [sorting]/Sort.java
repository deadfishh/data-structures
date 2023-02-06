import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;

public class Sort
{

    // performs an insertion sort
    public void insertionSort(int[] arr)
    {
        // traversing through the array
        for (int i = 1; i < arr.length; i++)
        {
            // if the current element is already sorted, we continue
            if (arr[i] < arr[i - 1])
            {
                continue;
            }
            // otherwise keep swapping until the number is in its place
            int num = i;
            while (num > 0 && arr[num] > arr[num - 1])
            {
                swap(arr, num, num - 1);
                num--;
            }
        }
    }


    // performs a bubble sort
    public void bubbleSort(int[] arr)
    {
        // traverse through the array
        for (int i = 0; i < arr.length - 1; i++)
        {
            // traverse through it a second time
            for (int j = 0; j < arr.length - 1; j++)
            {
                // if the elements next to each other are out of order, swap them
                if (arr[j] < arr[j + 1])
                {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    // performs a shell sort
    public void shellSort(int[] arr)
    {
        // traverse through the array, with the k value splitting in half each time
        for (int k = arr.length / 2; k >= 1; k /= 2)
        {
            // comparing each element and it's counterpart that's k indexes away
            for (int i = 0; i < arr.length - k; i++)
            {
                // if it's out of order, switch them
                if (arr[i] < arr[i + k])
                {
                    swap(arr, i, i + k);
                    int num = i - k;
                    // and keep switching this value until it's in the right place
                    while (num >= 0 && arr[num] < arr[num + k])
                    {
                        swap(arr, num, num + k);
                        // decrement by k each time
                        num -= k;
                    }
                }
            }
        }
    }


    // performs quicksort
    public void quickSort(int[] arr)
    {
        myQuickSort(arr, 0, arr.length - 1);
    }


    // recursive quicksort
    private void myQuickSort(int[] arr, int low, int high)
    {
        // the 'array size' is zero
        if (low < high)
        {
            int pivot = partition(arr, low, high);
            myQuickSort(arr, low, pivot - 1);
            myQuickSort(arr, pivot + 1, high);
        }   
    }


    // partitions the quick sort
    private int partition(int[] arr, int low, int high)
    {
        // rightmost element is the pivot
        int pivot = arr[high];
        // greater element
        int i = low - 1;
        // comparing all of the elements with the pivot
        for (int j = low; j < high; j++)
        {
            // swap anything greater than the pivot with the pivot
            if (arr[j] >= pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        // swap the pivot with the greater element
        swap(arr, i + 1, high);
        // return the partition index
        return i+1;
    }
    

    // performs merge sort
    public void mergeSort(int[] arr)
    {
        int[] temp = new int[arr.length];
        myMergeSort(arr, temp, 0, arr.length - 1);
    }


    // recursive merge sort
    private void myMergeSort(int[] arr, int[] temp, int start, int end)
    {
        // the 'array size' is zero
        if (start >= end)
        {
            return;
        }
        // splitting
        int middle = (start + end)/2;
        myMergeSort(arr, temp, start, middle); // split the right array
        myMergeSort(arr, temp, middle + 1, end); // split the left array
        merge(arr, temp, start, middle, middle+1, end); // merge
    }


    // merging the left and right array
    private void merge(int[] arr, int[] temp, int leftStart, int leftEnd, int rightStart, int rightEnd)
    {
        int i = leftStart; // left index
        int j = rightStart; // right index
        int k = leftStart; // main array index
        // merging the two arrays
        while(i <= leftEnd && j <= rightEnd)
        {
            // pick from the left array
            if (arr[i] >= arr[j])
            {
                temp[k] = arr[i];
                i++; // increment left array placeholder
            }
            // pick from the right array
            else
            {
                temp[k] = arr[j];
                j++; // increment right array placeholder
            }
            k++; // we always increment the main array placeholder
        }
        // copy the rest of the left array
        while (i <= leftEnd) 
        { 
            temp[k] = arr[i];
            i++; 
            k++; 
        }
        while (j <= rightEnd)
        {
            temp[k] = arr[j];
            j++;
            k++;
        }
        // copy the rest of the right array
        for (i = leftStart; i <= rightEnd; i++)
        {
            arr[i] = temp[i];
        }
    }


    // performs upgraded quick sort
    public void upgradedQuickSort(int[] input, int d, int k)
    {
        myUQS(input, 0, input.length - 1, d, 1, k);
    }


    // recursive upgraded quick sort
    private void myUQS(int[] arr, int low, int high, int d, int depth, int k)
    {
        // the 'array size' is zero
        if (low >= high)
        {
            return;
        }
        // if depth is less than d, continue with upgrade quick sort
        else if (depth < d)
        {
            int pivot = partition(arr, low, high);
            myUQS(arr, low, pivot - 1, d, ++depth, k);
            myUQS(arr, pivot + 1, high, d, ++depth, k);
        }
        // otherwise, do the insertion sort
        else if (low < high)
        {
            int[] temp = new int[arr.length];
            myUMS(arr, temp, 0, arr.length - 1, k);
        }
    }


    // recursive upgraded merge sort
    private void myUMS(int[] arr, int[] temp, int start, int end, int k)
    {
        // the 'array size' is zero
        if (start >= end)
        {
            return;
        }
        // the 'array size' is greater than k, so we continue
        if (end - start > k)
        {
            int middle = (start + end)/2;
            myUMS(arr, temp, middle + 1, end, k);
            merge(arr, temp, start, middle, middle+1, end);
            myUMS(arr, temp, start, middle, k);
        }
        // otherwise perform insertion sort
        else
        {
            insertionSort(arr);
        }
    }


    // swaps two indexes in an array
    private void swap(int[] arr, int index1, int index2)
    {
        int placeholder = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = placeholder;
    }


    // returns a random array of size n
    // ints range from 0 - n
    public int[] generateRandomArray(int n)
    {
        int[] answer = new int[n];
        for (int i = 0; i < answer.length; i++)
        {
            // gets a random number and puts it in the array
            int rand = (int) (Math.random() * n);
            answer[i] = rand;
        }
        return answer;
    }


    // reads the .txt file and does the command expected
    public void readCommands(String filepath)
    {
        try(Scanner scanner = new Scanner(new File(filepath)))
        {
            // reading each line of the scanner
            while(scanner.hasNext())
            {                
                String line = scanner.nextLine();
                String[] commands = line.split(": ");
                java.lang.reflect.Method method;
                System.out.print(commands[0] + ": ");
                switch(commands[0])
                {
                    // method that takes int[], int, int
                    case "upgradedQuickSort":
                    {
                        method = Sort.class.getMethod(commands[0], int[].class, int.class, int.class);
                        String[] nums = commands[1].split(", ");
                        int result1 = Integer.parseInt(nums[0]);
                        int result2 = Integer.parseInt(nums[1]);
                        int[] arr = makeArray(nums[2]);
                        method.invoke(this,  arr, result1, result2);
                        System.out.println(toString(arr));
                        break;
                    }
                    // method that just takes an int
                    case "generateRandomArray":
                    {
                        method = Sort.class.getMethod(commands[0], int.class);
                        int result = Integer.parseInt(commands[1]);
                        method.invoke(this,  result);
                        break;
                    }
                    // method that takes int[] (it's the rest of them)
                    default:
                    {
                        method = Sort.class.getMethod(commands[0],  int[].class);
                        int[] result = makeArray(commands[1]);
                        method.invoke(this, result);
                        System.out.println(toString(result));
                    }
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException  | NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e)
        {
            System.out.println("it seems that there was an error!");
            System.out.println(e);
        }
    }


    // turns a string of numbers into an int[] array and returns it
    private int[] makeArray(String array)
    {
        array = array.substring(1, array.length() - 1);
        String[] answer = array.split(",");
        int[] result = new int[answer.length];
        for (int i = 0; i < answer.length; i++)
        {
            result[i] = Integer.parseInt(answer[i]);
        }
        return result;
    }


    // prints the array
    public String toString(int[] arr)
    {
        String answer = "";
        for (int i = 0; i < arr.length; i++)
        {
            answer += arr[i] + " ";
        }
        return answer;
    }
}

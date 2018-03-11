package sjsu.moon.cs146.project2;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Quicksort
{
	private long count;
	
	Quicksort()
	{
		count = 0;
	}
	public void reset()
	{
		count = 0;
	}
	public int[] populate(int size)
	{
		int array[] = new int[size];
		for(int i=0; i<array.length;i++)
		{
			array[i] = randomValue();
		}
		return array;
	}
	/*
	public static void main(String[] args) 
	{
	
		final int maxSize = 7;
		Quicksort s = new Quicksort();
		
		int array[] = new int[maxSize];
		int array2[] = new int[maxSize];
		int array3[] = new int[maxSize];
		fill(array);
	    array2 = array.clone();
	    array3 = array.clone();

		System.out.println("Last pivot:");
		System.out.println(Arrays.toString(array));
		System.out.println();
		s.qs1(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
		System.out.println();
		System.out.println("Count : "+s.getPartCount());
		System.out.println();
		
		s.setCount(0);
     	System.out.println("Randomly selected median pivot:");
     	System.out.println(Arrays.toString(array2));
		System.out.println();
		s.qs2(array2, 0, array2.length-1);
		System.out.println(Arrays.toString(array2));
		System.out.println();
		System.out.println("Count : "+s.getPartCount());
		System.out.println();
	
	}
	*/


	public long getPartCount()
	{
		return count;
	}
	
	public static int randomValue()
	{
		int ran = ThreadLocalRandom.current().nextInt(0, 2147483640+1);
		return ran;
	}
	
	public int[] qs1(int r_array[], int start, int end)
	{
		if(start < end)
		{
			int pivot = partition(r_array, start, end);
			qs1(r_array, start, pivot-1);
			qs1(r_array, pivot+1, end);
		}
		
		return r_array;
	}
	
	public int partition(int r_array[], int start, int end)
	{
		int pivot = r_array[end];
		int i = (start - 1);
		for(int j = start; j<end; j++)
		{
			if(r_array[j] <= pivot)
			{
				i++;
				swap(r_array, i, j);
			}
			count++;
		}
		swap(r_array, i+1, end);
		return i+1;
	}
	
	public static void swap(int r_array[], int p, int q)
	{
		int temp = r_array[p];
		r_array[p] = r_array[q];
		r_array[q] = temp;
	}
	
	public int randomPartition(int r_array[], int start, int end)
	{
		Random rand = new Random();
		int pivot = start + rand.nextInt((end - start));
		swap(r_array, end, pivot);

	    return partition(r_array, start, end);
	}
	
	public int[] qs2(int r_array[], int start, int end)
	{
		if(start < end)
		{
			if(end-start>0)
			{
				int pivot = select(r_array, start, end, (end-start+1)/2);
				swap(r_array, pivot, end);
				int q = partition(r_array, start, end);
				qs2(r_array, start, q-1);
				qs2(r_array, q+1, end);	
			}
			else
			{
				r_array = insertionSort(r_array, start, end);
			}
		}
		return r_array;
	}
	public int select(int r_array[], int start, int end, int i)
	{

		if(start == end) // base case
			return start;
		int pivot = randomPartition(r_array, start, end);
		int p_order = pivot-start +1;
		if(i==p_order)
			return pivot;
		else if(i<p_order)
			return select(r_array, start, pivot-1, i);
		else
		{
			return select(r_array, pivot+1, end, i-p_order);
		}		
	}
	
	public int[] insertionSort(int r_array[], int start, int end)
	{
		for (int i=start+1; i<=end; ++i)
        {
            int key = r_array[i];
            int j = i-1;
            while (j>=start && r_array[j] > key)
            {
                r_array[j+1] = r_array[j];
                j = j-1;
            }
            r_array[j+1] = key;
        }
		return r_array;
	}
}
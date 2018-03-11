package sjsu.moon.cs146.project2;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * The main JUnit Test class to test each method in Quicksort
 * 
 *
 */
public class QuicksortTest
{

   private Quicksort QS;

   @Before
   public void setUp() throws Exception
   {
      QS = new Quicksort();
      QS.reset();
   } // setUp()



   /* Method to test the Sorting of an empty List
    */
   @Test
   public void testEmpty() {
             int[] array1 = new int[0];
             int[]  array2= new int[0]; //correct sorted array
             
             array1 = QS.qs1(array1, 0, array1.length - 1);
             assertArrayEquals(array1,array2);
             array2 = QS.qs2(array2, 0, array2.length - 1);
             assertArrayEquals(array1,array2);
   }   

  
   /* Method to test the Sorting of an already sorted list:
    */
   @Test
   public void testSorted() {
        int[] array1 = new int[20];
        int[] array2 = new int[20];
 	int[] array3 = new int[20];
              
	 for (int i = 0; i < 10; i++) {
             array1[i] = i*2;
             array2[i] = i*2;
	     array3[i] = i*2;

        }
	// sort using Java's inbuilt sorting method
        Arrays.sort(array3);
         
        // run QS1()
        array1 = QS.qs1(array1, 0, array1.length - 1);
        assertArrayEquals(array1,array3);
        
        // run QS2()
        array1 = QS.qs2(array2, 0, array2.length - 1);
        assertArrayEquals(array1,array3);
        
   }
 
  
   /* Method to test the Sorting of a reverse sorted list:
    */
   @Test
   public void testReverseSorted() {
        int[] array1 = new int[10];
        int[] array2 = new int[10];
        
        int[] array3 = new int[10];
        
        for (int i = 0; i < 10; i++) {
             array1[i] = (100-i);
             array2[i] = (100-i);
             array3[i] = (100-i);
        }
        //sort array3 
        Arrays.sort(array3); 
   
        // run QS1()
        array1 = QS.qs1(array1, 0, array1.length - 1);
        assertArrayEquals(array1,array3);
        
        // run QS2()
        array1 = QS.qs2(array2, 0, array2.length - 1);
        assertArrayEquals(array1,array3);
        
   }
   
   /*
    * Method to test the select method 
    */
    @Test
    public void testSelect() {
      int[] array1 = new int[100];
       
      for (int i = 0; i < 100; i++) {
            array1[i] = i;
      }
      // median is 49
      int median=QS.select(array1,0, array1.length-1, array1.length/2);
      System.out.println("median:"+ QS.select(array1,0, array1.length-1, array1.length/2));
      assertEquals(median, 49);
       
    }
 
 
   /*
    * Method to test the randomness to the tests:
    */
    @Test
     public void testRandom() {
       int[] array1 = new int[10];
       
       for (int i = 0; i < 10; i++) {
            array1[i] = (int) Math.random()*10;
       } 
 
       //copy arrays
       int[] array2 = Arrays.copyOf(array1, array1.length);  
       int[] array3 = Arrays.copyOf(array1, array1.length); // correct sorted array 
       Arrays.sort(array3);
 
       // run QS1()
       array1 = QS.qs1(array1, 0, array1.length - 1);
       assertArrayEquals(array1,array3);
       
       // run QS2()
       array1 = QS.qs2(array2, 0, array2.length - 1);
       assertArrayEquals(array1,array3);
       
     }

   /* Method to test the timing of QS1
    *
    */
   @Test
   public void testQS1Timing()
   {
      // create an array and a sorted backup
      int[] array1 = QS.populate(10000);
      int[] array2 = QS.populate(1000000);
      int[] array3 = QS.populate(10000000);
      
      
      long start = System.currentTimeMillis();
      array1 = QS.qs1(array1, 0, array1.length - 1);
      long end = System.currentTimeMillis();
      long elapsed = end - start;
      System.out.println("QS1 time to sort 10000 elements in ms:"+ elapsed);

      start = System.currentTimeMillis();
      array2 = QS.qs1(array2, 0, array2.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 1000000 elements in ms:"+ elapsed);
      
      start = System.currentTimeMillis();
      array3 = QS.qs1(array3, 0, array3.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 100000000 elements in ms:"+ elapsed);
   }



   /* Method to test the timing of QS2
    *
    */
   @Test
   public void testQS2Timing()
   {
      // create an array and a sorted backup
      int[] array1 = QS.populate(10000);
      int[] array2 = QS.populate(1000000);
      int[] array3 = QS.populate(10000000);
      
      
      long start = System.currentTimeMillis();
      array1 = QS.qs2(array1, 0, array1.length - 1);
      long end = System.currentTimeMillis();
      long elapsed = end - start;
      System.out.println("QS2 time to sort 10000 elements in ms:"+ elapsed);

      start = System.currentTimeMillis();
      array2 = QS.qs2(array2, 0, array2.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 1000000 elements in ms:"+ elapsed);
      
      start = System.currentTimeMillis();
      array3 = QS.qs2(array3, 0, array3.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 100000000 elements in ms:"+ elapsed);
   } 
   
   /* Method to test the timing and counts of both QS 
   *
   */
  @Test
   public void testBothQSwithTimingandCount()
   {
      // create an array and a sorted backup
      int[] array1 = QS.populate(10000);
      int[] array12 = new int [10000];
      array12 = array1.clone();
      int[] array2 = QS.populate(100000);
      int[] array22 = new int [100000];
      array22 = array2.clone();
      int[] array3 = QS.populate(1000000);
      int[] array32 = new int [1000000];
      array32 = array3.clone();
      int[] array4 = QS.populate(10000000);
      int[] array42 = new int [10000000];
      array42 = array4.clone();
      int[] array5 = QS.populate(100000000);
      int[] array52 = new int [100000000];
      array52 = array5.clone();
      
      System.out.println();
      long start = System.currentTimeMillis();
      array1 = QS.qs1(array1, 0, array1.length - 1);
      long end = System.currentTimeMillis();
      long elapsed = end - start;
      System.out.println("QS1 time to sort 10000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();

      start = System.currentTimeMillis();
      array12 = QS.qs2(array12, 0, array12.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 10000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();
      
      System.out.println();
      start = System.currentTimeMillis();
      array2 = QS.qs1(array2, 0, array2.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 100000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();

      start = System.currentTimeMillis();
      array22 = QS.qs2(array22, 0, array22.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 100000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();
      
      System.out.println();
      start = System.currentTimeMillis();
      array3 = QS.qs1(array3, 0, array3.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 1000000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();

      start = System.currentTimeMillis();
      array32 = QS.qs2(array32, 0, array32.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 1000000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();
      
      System.out.println();
      start = System.currentTimeMillis();
      array4 = QS.qs1(array4, 0, array4.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 10000000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();

      start = System.currentTimeMillis();
      array42 = QS.qs2(array42, 0, array42.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 10000000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();
      
      System.out.println();
      start = System.currentTimeMillis();
      array5 = QS.qs1(array5, 0, array5.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 100000000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();

      start = System.currentTimeMillis();
      array52 = QS.qs2(array52, 0, array52.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 100000000 elements in ms:"+ elapsed);
      System.out.println("Count: "+ QS.getPartCount());
      QS.reset();
     
   }

   
   /*
    * Method to test the number of comparisons in sorting an already sorted array of 10 numbers.
    * Number of comparisons should be 45
    */
   @Test
   public void testgetPartCount()
   {
      int[] array1 = new int[10];
      
    //  int[] result1 = new int[10];
      
      for (int i = 0; i < 10; i++) {
           array1[i] = i*20;
      }
      
      array1 = QS.qs1(array1, 0, array1.length - 1);
      System.out.println("comparisons in already sorted:"+ QS.getPartCount());
      long compare=QS.getPartCount();
      assertEquals(compare, 45);
   }




   /*
    * Method to test the number of comparisons in reverse sorted array of 10 numbers.
    * Number of comparisons should be 45.
    */	
   @Test
   public void testgetPartCountA()
   {
      int[] array1 = new int[10];
      
      for (int i = 0; i < 10; i++) {
           array1[i] = (100-i);
      }
      
      array1 = QS.qs1(array1, 0, array1.length - 1);
      System.out.println("comparisons in reverse sorted:"+ QS.getPartCount());
      long compare=QS.getPartCount();
      assertEquals(compare, 45);
         
   } // getPartCount()  
   
   /* InsertionSortTest
    */
   @Test
   public void insertionSorted() {
        int[] array1 = new int[20];
        int[] array2 = new int[20];
              
	 for (int i = 0; i < 10; i++) {
             array1[i] = i*2;
             array2[i] = i*2;
        }
	// sort using Java's inbuilt sorting method
        Arrays.sort(array1);
         
        // run insertionSort()
        array2 = QS.insertionSort(array2, 0, array1.length - 1);
        assertArrayEquals(array1,array2);
        
   }
  
}
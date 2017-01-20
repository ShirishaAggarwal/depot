package objects;

import java.util.Arrays;

public class AmicablePair {
	private static int MAX = 100000000;	
	// Array to keep factor sum of numbers till MAX 
	private static int[] arr = new int[MAX+1];
	
	public static void main(String[] args) {
		// As 1 is factor of very number, pre-populate 
		// factor sum array with 1, as we start finding 
		// amicable pairs from 2, we are good keeping 1 at
		// index 0, 1 as well.
		Arrays.fill(arr, 1);
		long startTime = System.nanoTime();
		// Traverse till square root of MAX
		// Using logic similar to sieve of eratosthenes to
		// populate factor sum array
		int limit = (int)Math.sqrt(MAX);
		for (int i = 2; i <= limit; ++i) {
			int j = i+i;
			int count = 2;
			while (j <= MAX){
				// As we already add count (j/i) to the factor sum when adding i,
				// we don't add when i is equal to or greater than count
				if (i < count) {
					// Adding i and count (j/i) to the factor sum of j
					arr[j] += i;
					arr[j] += count;
				}
				j += i;
				count++;
			} 
		}
		
		int count = 0;
		System.out.println("The following are amicable numbers");
		for (int i = 2; i <= MAX; ++i) {
			int num = arr[i];
			if (num <= MAX && num > i) {
				if (arr[num] == i) {
					System.out.println(count+": "+i+" and "+num);
					count++;
				}
			}
		}
		
		long endTime = System.nanoTime();
		double duration = timeInSec(endTime,startTime) ;
		System.out.println("Run time " + duration + " : secs");		
	}
	
	/*
	* endTime and startTime are in nano seconds
	*/
	private static double timeInSec(long endTime, long startTime) {
		long duration = (endTime - startTime);
		if (duration > 0) {
			double dm = (duration/1000000.0); //Milliseconds
			double d = dm/1000.0; //seconds
			return d ;
		}
		return 0.0 ;
	}
}

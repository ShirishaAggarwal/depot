package objects;

import java.util.Random;

/**
 * File Name: BarAttender.java 
 * Simulates Bar attender puzzle
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2014
 */

class BarAttender{
  private int min; //Work in the range of min to max
  private int max;
  
  BarAttender(int min, int max){
    //WRITE CODE
	  this.min = min;
	  this.max = max;
  }

  private int guess(int g, boolean show) {
    //WRITE CODE	
	int mid = 0;
	int count = 0;
	int l = this.min;
	int r = this.max;
	
	if (show)
	{
		System.out.println("Number to be guessed " + g);
		System.out.println("----------------------------");			 
	}
	
	while (l <= r){		
		count++;
		mid = (l+r)/2;
		if(show)
		{
			System.out.println(count + ": l= " + l + "  r= " + r + " m= " + mid);
		}
		if(mid == g)
			return count;
		else if(mid < g)
			l = mid+1;
		else
			r = mid -1;		
	}	
	return count;
  }
  private static void showValues(int l,int r,int mid,int g)
  {
	  System.out.println("Number to be guessed " + g);
	  System.out.println("----------------------------");
	  
  }
  private static void test(int s, int b, int m, boolean useRandom,boolean show) {
    //CANNOT CHANGE CODE BELOW. MUST USE AS IS
    Random r = new Random() ;
    int min = 9999 ;
    int max = 0 ; 
    BarAttender h = new BarAttender(s,b);
    for (int i = 0; i < m; ++i) {
      int v = i + s ;
      if (useRandom) {
        v = RandomInt.getRandomInt(r,s,b); //This gives number between 1 to 1000000-1
      }
      int n = h.guess(v,show) ;
      if (n > max) {
        max = n;
      }
      if (n < min) {
        min = n ;
      }
    } 
    System.out.print("For numbers  between " + s + " to " +  b +  " ");
    if (useRandom) {
       System.out.print("(random numbers)") ;
    }else {
      System.out.print("(not random numbers)") ;
    }
    System.out.println(" the min guess is " + min + " max guess is " + max) ;
  }

  private static void testBench() { 
    //CANNOT CHANGE CODE BELOW. MUST USE AS IS
    test(1,10,10,false,true) ;
    test(1,1000000,1000000,false,false);
    test(1,1000000,1000,true,false);
  }

  public static void main(String[] args) {
    //CANNOT CHANGE CODE BELOW. MUST USE AS IS
    System.out.println("BarAttender.java");
    testBench();
    System.out.println("DONE");
  }
}

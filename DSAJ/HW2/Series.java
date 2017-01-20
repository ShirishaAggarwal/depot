package objects;

import java.math.BigInteger;

/**
 * File Name: Series.java 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

class Series {
  private static final IntUtil u = new IntUtil();
  
  
  private static void testLog(int start, int end, int incr) {
    //WRITE CODE
	  for(int i=start; i<end;)
	  {
		  printLogValues(i);
		  i=i+incr;
	  }
  }
  
  private static void printLogValues (long n){
	  double log10 = Math.log10(n);
	  double log2 = (Math.log(n)/Math.log(2));
	  double loge = Math.log(n);
	  String displaylog10 = String.format("%.6g%n", log10);
	  String displaylog2 = String.format("%.7g%n", log2);
	  String displayloge = String.format("%.7g%n", loge);
	  System.out.println("    "+ n + "      " + displaylog10.trim() + "  " + displaylog2.trim() + "  " + displayloge.trim());
  }
  
  private static void testLog() {
    System.out.println("    n  base10     base2     base e") ;
    testLog(1,10,1) ;
    testLog(10,100,10) ; 
    testLog(100,1000,100) ;
    testLog(1000,10001,9000) ;
  }
  
  /*
   * 1 + 1/2 + 1/4 + 1/8 + 1/16 +... + = 2
   */
  private static void seriesConvergesAbsolutely() { 
    //WRITE CODE HERE for n = 100
	  double i,sum = 0;	  
	  
	  for(i=0;i<=100;i++)
	  {		 
		 sum = sum + (1/(Math.pow(2, i)));
	  }
	  System.out.println("\nOutput of the function, seriesConvergesAbsolutely : " + sum);	  	 
  }
  
  /*
   * 1 + 1/2 + 1/3 + 1/4 + 1/5 + .. = 
   */
  private static void harmonicSeries() {
    //WRITE CODE HERE for n = 100
	  double sum=0;
	  
	  for (int i=1;i<=100;i++)
	  {
		  sum= sum + (1.0/i);
	  }
	  System.out.println("\n\nOutput of harmonicSeries :" + sum);
	  double logn = Math.log(100)  + 0.57721566;
	  
	  System.out.println("Sum : " + logn);
  }
  
  private static void chess() {
    //WRITE CODE
	  System.out.println("\n\nPrinting the Number of rice on each square of a chess board");
	  	  
      BigInteger b,sum;      
      b = new BigInteger("1");
      sum = new BigInteger("0");
	        
	  for (int i=0;i<64;i++)
	  {		 
		if(i!=0)						
			b = b.multiply(new BigInteger("2"));		
		sum = sum.add(b);
		System.out.println("On square " + (i+1) + " = " + b);		
	  }
	  System.out.println("Final Sum    = " + sum);
  }
  
  private static void testbed() {
    testLog();
    seriesConvergesAbsolutely();
    harmonicSeries() ;
    chess() ;
  }
  
  public static void main(String[] args) {
    System.out.println("Series.java");
    testbed();
    System.out.println("Done");
  }
}

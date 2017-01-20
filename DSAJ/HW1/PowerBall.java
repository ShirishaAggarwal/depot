package objects;

import java.util.Arrays;

/**
 * File Name: PowerBall.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: IntUtil.java RandomInt.java PowerBall.java
 */

class PowerBall {
  /*
   * ALL PRIVATE DATA BELOW
   * CANNOT ADD ANY MORE MEMBER
   */
  private int[] winningNumber ;
  private int[] ticketNumber ;
  private long cash ;
  static private boolean display = true ;
  static final long jackpot = 100000000;
  static private IntUtil u = new IntUtil();
  
  private void printNumbers() {
    if (display) {
      System.out.println("winningNumber ") ;
      u.pLn(winningNumber);
      System.out.println("ticketNumber  ") ;
      u.pLn(ticketNumber);
    }
  }
  
  public long cash() {
    return cash ;
  }
  
  private void check() {
     //WRITE CODE HERE
     //YOU CAN WRITE AS MANY PRIVATE ROUTINES AS YOU NEED	
	 //IntUtil intUtil = new IntUtil();
	 /* Getting the powerball  number for winning number and the ticket number*/
	 int winningPowerBall = winningNumber[winningNumber.length-1];
	 int ticketPowerBall = ticketNumber[ticketNumber.length-1];		
	 
	 int[] whiteballs = {0,0,0,0,0};
	 int[] playerballs = {0,0,0,0,0};
	 boolean invalid = false;
	 
	 //printNumbers();
	 /* Splitting and populating the array excluding the power ball number */
	 for (int i=0;i<winningNumber.length -1;i++)
	 {
		 whiteballs[i] = winningNumber[i]; 
	 }
	 for (int i=0;i<ticketNumber.length -1;i++)
	 {
		 if(ticketNumber[i] > 99){
			 invalid = true;
			 break;
		 }
		 playerballs[i] = ticketNumber[i]; 
	 }
	 
	 if(!invalid)
	 {		 
		 /*Sorting the arrays*/
		 Arrays.sort(whiteballs);
		 Arrays.sort(playerballs);	 
		 int count=0;
		 int i=0,j=0;
		 
		 while(i<whiteballs.length && j<playerballs.length)
		 {
			/* Checking if the numbers match, and increment the count as well as moving forward for next step*/
			 if(whiteballs[i] == playerballs[j])
			 {
				 count++;
				 i++;
				 j++;
			 }
			 /* As the list is a sorted list, by doing the below checks we are making sure that we are not missing any of the numbers in the array*/
			 else if(whiteballs[i] < playerballs[j])
			 {
				 i++;
			 }
			 else
			 {
				 j++;
			 }		 
		 }			 
	     /* Calling this private method to calculate the winning amount as per the matching numbers*/
		 cash = calculateWinningAmount(count,winningPowerBall,ticketPowerBall);
//		 if (cash != 0)
//		 {
//			 u.pLn("winningNumber", winningNumber);
//			 u.pLn("ticketNumber", ticketNumber);
//			 System.out.println("Cash won : " + cash);
//		 }
	 }
	 else
	 {		 		 
		 cash = 0;
	 }
  }
  
  private long calculateWinningAmount(int noOfMatchingBalls,int winningPBNum,int ticketPBNum)
  {
      long prizeAmount = 0;
	  if(noOfMatchingBalls == 5 && winningPBNum == ticketPBNum)
      {
		  prizeAmount = jackpot;
      }
	  else if (noOfMatchingBalls == 5)
	  {
		  prizeAmount = 1000000;
	  }
	  else if (noOfMatchingBalls == 4 && winningPBNum == ticketPBNum)
	  {
		  prizeAmount = 500000;
	  }
	  else if (noOfMatchingBalls == 4)
	  {
		  prizeAmount = 100;
	  }
	  else if (noOfMatchingBalls == 3 && winningPBNum == ticketPBNum)
	  {
		  prizeAmount = 100;
	  }
	  else if (noOfMatchingBalls == 3)
	  {
		  prizeAmount = 7;
	  }
	  else if (noOfMatchingBalls == 2 && winningPBNum == ticketPBNum)
	  {
		  prizeAmount = 7;
	  }
	  else if (noOfMatchingBalls == 1 && winningPBNum == ticketPBNum)
	  {
		  prizeAmount = 4;
	  }
	  else if (winningPBNum == ticketPBNum)
	  {
		  prizeAmount = 4;
	  }
	  return prizeAmount;
  }
    
  PowerBall(int[] w, int [] t) {
    winningNumber = w ;
    ticketNumber = t ;
    cash = 0 ;
    check() ;
  }
  
  private static void test1() {
    //CANNOT CHANGE BELOW
    int[] w = {4,8,19,27,24,10} ;
    {
      int [] n= {4,8,19,27,24,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {24,27,19,8,4,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {24,27,19,8,4,5} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14,5} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,124,19,119,18,14} ;
      PowerBall x = new PowerBall(w,n);
    }  
  }
  
  private static void testRandom() {
  //CANNOT CHANGE BELOW
    System.out.println("----------testRandom()  starts-------------") ;
    display = false ;
    int[] w = {4,8,19,27,24,10} ;
    int max = 1000000 ;    
    long c = 0 ;
    System.out.println("Buying " + max + " tickets of worth " + max * 2 +"$") ;
    for (int i = 0; i < max; ++i) {    
      int [] n = u.generateRandomNumber(6,true,1,60);
      PowerBall x = new PowerBall(w,n); 
      if (x.cash() == jackpot) {
        System.out.println("Won Jackpot") ;
      }
      c = c + x.cash();
    }
    long p = (c -(max*2)) ;
    System.out.println("Out of " + max + " times you win " + c + "$") ;
    if (p > 0) {
      System.out.println("Profit = " + p) ;
    }else {
      System.out.println("Loss = " + p) ;
    }
    System.out.println("----------testRandom()  ends-------------") ;
  }
  
  private static void testBench() {
  //CANNOT CHANGE BELOW
    test1() ;
    testRandom() ;
  }
  
  public static void main(String[] args) {
  //CANNOT CHANGE BELOW
    System.out.println("PowerBall.java");
    testBench();
    System.out.println("Done");
  }
}

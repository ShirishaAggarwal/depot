package objects;

import java.util.Arrays;

/**
 * File Name: BigUnsignedNumber.java 
 * Infinite capacity Unsigned Number
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: BigUnsignedNumber.java CharArray.java Cstring IntUtil.java
 */

class BigUnsignedNumber {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  //Cannot add any more data fields
  
  //WRITE ALL FUNCTIONS SO THAT ALL TESTS WILL PASS
  
  public BigUnsignedNumber()
  {
	  d = new Cstring();
  }
  
  //Constructor to accept integer inputs
  public BigUnsignedNumber(int n)
  {
	  String s = n+"";	  
	  d = new Cstring(s);
  }
  
  //Constructor to accept char inputs
  public BigUnsignedNumber(char c)
  {
	  d = new Cstring(c);
  }
  
  //Constructor to accept string inputs
  public BigUnsignedNumber(String str)
  {
	  d = new Cstring(str);
  }
  
  //Constructor to accept Cstring inputs
  public BigUnsignedNumber(Cstring cstr)
  {
	  d = cstr.clone();
  }
  
  //Overriding clone method of object class here
  @Override
  public BigUnsignedNumber clone()
  {	  	  
	  //Using clone method of Cstring class to implement clone in this class
	  BigUnsignedNumber b = new BigUnsignedNumber(d);
	  return b;
  }
  
  //Implementing pLn of this class using Cstring pLn
  public void pLn(String str)
  {
	 d.pLn(str);
  }
  
  // Method which returns the value of the object as string
  public Cstring getValueCString()
  {
	  return d;	  
  }
  
  public Cstring toCString() 
  {
	  return d;
  }
  
  public BigUnsignedNumber add(BigUnsignedNumber b)
  {
	  Cstring firstCstring = d;
	  Cstring secondCstring = b.toCString();
	  Cstring resultCstring = new Cstring();
	  
	  //Deciding the length of resultant sum array
	  int n1 = firstCstring.length();
	  int n2 = secondCstring.length();
	  int max = Math.max(n1, n2);
	  
	  int carry = 0;
	  int index1 = n1-1;
	  int index2 = n2-1;
	  int v1,v2,k = 0;
	  
	  for(int i=max-1; i>=0; i--)
	  {		  
		  if(index1 >= 0 && index2 >= 0)
		  {
			  v1 = firstCstring.charAt(index1)-'0';
			  v2 = secondCstring.charAt(index2) - '0';
			  resultCstring.append(((v1+v2+carry)%10)+"");
			  carry = (v1+v2+carry)/10;
			  index1--;
			  index2--;
		  }
		  else if(index1 >= 0)
		  {
			  v1 = firstCstring.charAt(index1)-'0'; 
			  resultCstring.append(((v1+carry)%10)+"");
			  carry = (v1+carry)/10;
			  index1--;
		  }
		  else if(index2 >= 0)
		  {
			  v2 = secondCstring.charAt(index2)-'0'; 
			  resultCstring.append(((v2+carry)%10)+"");
			  carry = (v2+carry)/10;
			  index2--;
		  }
		  k++;
	  }
	  if (carry != 0) {
		  resultCstring.append(carry+"");
	  }
	  resultCstring.reverse();
	  return (new BigUnsignedNumber(resultCstring));
  }
  
  public boolean isEqual(BigUnsignedNumber g) {	
	  	return g.toCString().isEqual(d);
  }
  
  public BigUnsignedNumber mult(BigUnsignedNumber g) {
	  	// If either the current number or the number to be multiplied 
	  	// is 0, the result is also 0. 
	  	if (isZero(this) || isZero(g)) {
	  		return new BigUnsignedNumber('0');
	  	}
	  
	  	Cstring firstCstring = toCString();
	    Cstring secondCstring = g.toCString();
	    Cstring resultCstring = new Cstring();
	    int length1 = firstCstring.length();
	    int length2 = secondCstring.length();
	    // Allocating array of size good enough to keep product
	    int[] tempArr = new int[length1+length2+1];
	    Arrays.fill(tempArr, 0);
	    int startIndex = 0;
	    int currentIndex = -1;
	    for (int i = length1-1; i >= 0; --i) {
	      currentIndex = startIndex;
	      int carry = 0;
	      for (int j = length2-1; j >= 0; --j) {
	        int n1 = firstCstring.charAt(i)-'0';
	        int n2 = secondCstring.charAt(j)-'0';
	        int prod = (n1*n2+carry)%10;
	        tempArr[currentIndex++] += prod;
	        carry = (n1*n2+carry)/10;
	      }
	      if (carry != 0) {
	        tempArr[currentIndex++] += carry;
	      }
	      startIndex++;
	    }
	    int carry = 0;
	    int maxIndex = currentIndex;
	    currentIndex = 0;
	    //Current populated elements are till index maxIndex-1
	    while(currentIndex < maxIndex) {
	      int temp = tempArr[currentIndex]+carry;
	      tempArr[currentIndex++] = temp%10;
	      carry = temp/10;
	    }
	    if (carry != 0) {
	       tempArr[currentIndex++] += carry;
	    }
	    maxIndex = currentIndex;
	    currentIndex = 0;
	    while (currentIndex < maxIndex) 
	    {
	    	resultCstring.append(tempArr[maxIndex-1-currentIndex]+"");
	    	currentIndex++;
	    }
	    return new BigUnsignedNumber(resultCstring);	
  }
  
  private boolean isZero(BigUnsignedNumber g) {
	  Cstring cstr = g.toCString();
	  for (int i = 0; i < cstr.length(); ++i) {
		  if (cstr.charAt(i) != '0') {
			  return false;
		  }
	  }
	  return true;
  }
	
  public boolean isEqual(String string) 
  {	
		return d.isEqual(new Cstring(string));
  }
	
  public boolean isEqual(int i) 
  {	
		return d.isEqual(new Cstring(i+""));
  }
	
  public static BigUnsignedNumber factorial(int i) 
  {	
	  	BigUnsignedNumber result = new BigUnsignedNumber('1');
		for (int j = 2; j <= i; ++j) 
		{
			result = result.mult(new BigUnsignedNumber(new Cstring(j+"")));
		}
		return result;
  }
	
  public int size() {
	  int size = 0;
	  if (d != null) 
	  {
		  size = d.length();
	  }
	  return size;
  } 
	
  //CANNOT CHANGE ANYTHING BELOW 
  
  
  private static void test1() {
    BigUnsignedNumber b = new BigUnsignedNumber(10);
    b.pLn("test1 10");
  }
  
  private static void testBench() {
    System.out.println("------------test1---------------------");
    test1();
  }
  
  public static void main(String[] args) {
    System.out.println("BigUnsignedNumber.java");
    testBench();
    System.out.println("Done");
  }
}

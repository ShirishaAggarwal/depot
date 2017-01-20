/**
 * File Name: Cstring.java 
 * Implements C String
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: CharArray.java Cstring
 */
package objects;
class Cstring {
  private CharArray d; //Infinte array of char
  static IntUtil u = new IntUtil();
  //Cannot add any more data fields
  
  //WRITE ALL FUNCTIONS SO THAT ALL TESTS WILL PASS
  //
  public Cstring(){
	  d = new CharArray();
  }
  
  public Cstring(char c)
  {
	d = new CharArray();
	d.set(0, c);
	d.set(1, '\0');
  }
  
  public Cstring(String str)
  {
	d = new CharArray();
	int len = str.length();
	int pos = 0;
	while(len != pos)
	{
		d.set(pos,str.charAt(pos));
		pos++;
	}
	d.set(len, '\0');
  }
  
  public CharArray toCharArray() {
	  return d;
  }
    
  public void append(Cstring cstr)
  {
	  int size1 = d.size();
	  CharArray e = cstr.toCharArray();
	  int size2 = e.size();
	  int pos = 0;
	  while (pos != size2) 
	  {
		  d.set(size1+pos, e.get(pos));
		  pos++;
	  }
	  d.set(size1+size2, '\0');
  }
  
  public int length()
  {
	  return d.size();
  }
  
  public void append(String str) 
  {
	  Cstring cstr = new Cstring(str);
	  append(cstr);
  }
  
  //implementing isEqual method from two cstrings
  public boolean isEqual(Cstring cstr)
  {	  
	  CharArray e = cstr.toCharArray();
	  int size1 = d.size();
	  int size2 = e.size();
	  if (size1 != size2) 
	  {
		  return false;
	  }
	  else 
	  {
		int i = 0;
		int j = 0;
		while (i < size1 && j < size2) 
		{
			char ch1 = d.get(i);
			char ch2 = e.get(j);
			if (ch1 != ch2)
				return false;
			i++;
			j++;
		}
		return true;
	  }
  }
    
  public void pLn(String string) 
  {
	if(d!=null)
	{
		d.pLn(string);
	}
  }
  
  private boolean isEmpty() {
	 return d == null || d.size() == 0; 
  }
  
  @Override
  public Cstring clone()
  {	  
	  //clone function
	  Cstring cloneString = null;
	  if (!this.isEmpty()) {
		 cloneString = new Cstring();
		 cloneString.append(this);
	  }
	  return cloneString;
  }
  
  public String toString()
  {
	  //Internal method added
	  String retStr = new String();
	  for(int i=0; i<d.size(); i++)
	  {
		  retStr += d.get(i);
	  }
	  return retStr;
  }
  
  public Cstring add(Cstring cstr)
  {
	Cstring resCstring = clone();
	resCstring.append(cstr);
	return resCstring;
  }
  
  public Cstring add(String str)
  {
	Cstring resCstring = clone();
	resCstring.append(str);
	return resCstring;
  }
  
  public void reverse()
  {
	  if (d!=null)
	  {
		  d.reverse();
	  }
  }
  
  public char charAt(int pos) {
	  return d.get(pos);
  }
  
  //CANNOT CHANGE ANYTHING BELOW 
  private static void testBasic() {
    Cstring a = new Cstring('b') ;
    a.pLn("a = ") ;
    Cstring b = new Cstring('7') ;
    b.pLn("b = ") ;
    Cstring c = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    c.pLn("c = ") ;
    //Renamed the below as my compiler thinks that i'm overriding the clone method of Object class
    //Cstring d = c.clone() ;
    Cstring d = c.clone();
    d.pLn("d = ") ;
    Cstring e = new Cstring("A quick brown fox junped over a lazy dog") ;
    e.pLn("e = ") ;
    Cstring f = new Cstring("Gateman sees name garageman sees nametag") ;
    f.pLn("f =  ") ;
    f.reverse() ;
    f.pLn("f' = ") ;
  }
  
  private static void testAdd() {
    Cstring a = new Cstring("UCSC") ;
    Cstring b = new Cstring("Extension") ;
    Cstring c = a.add(b) ;
    a.pLn("a = ") ;
    b.pLn("b = ") ;
    c.pLn("c = ") ;
    Cstring d = c.add("USA") ;
    d.pLn("d = ") ;
    a.append(b) ;
    a.pLn("a+b = ") ;
    a.append("World") ;
    a.pLn("a+b+World = ") ;
  }
  
  

private static void testEqual() {
    Cstring a = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    a.pLn("a = ") ;
    Cstring b = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    b.pLn("b = ") ;
    u.myassert(a.isEqual(b)) ;
    Cstring c = new Cstring("12345678901234567890123456789012345678901234567890123456789") ;
    c.pLn("c = ") ;
    u.myassert(a.isEqual(c) == false) ;
  }
  
  private static void testBench() {
    System.out.println("-----------Basic----------------");
    testBasic() ;
    System.out.println("-----------Addition----------------");
    testAdd() ;
    System.out.println("-----------Equal----------------");
    testEqual() ;
  }
  
  public static void main(String[] args) {
    System.out.println("Cstring.java");
    testBench();
    System.out.println("Done");
  }
}
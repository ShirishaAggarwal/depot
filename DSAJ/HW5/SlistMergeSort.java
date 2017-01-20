package objects;
/**
 * File Name: SlistMergeSort.java 
 * Sort int slist using Merge Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistMergeSort.java
 */

class SlistMergeSort extends SlistSort{
 
  @Override
  protected void sort(boolean ascend) {		
	  node firstN = this.a.first;	  
	  if (firstN == null || firstN.next == null)
		   return;
	  node n = firstN;	  	  
	  while (n.next != null) {
		  n = n.next;		  //
	  }
	  this.a.first = sort(ascend, firstN, n);
  }
  
  private node sort(boolean ascend, node start, node end) {
	  //WRITE CODE HERE
	  if (start == null || start.next == null)
		  return start; //only one element
	  node n1 = start;
	  node n2 = start.next;
	  while(n2 != null && n2.next != null) {
		  n2 = n2.next.next;
		  n1 = n1.next;
	  }
	  node temp = n1.next;
	  n1.next = null;
	  //getting the two sorted lists to merge
	  node temp1 = sort(ascend, start, n1);
	  node temp2 = sort(ascend, temp, end);
	  return merge(ascend, temp1, temp2);
  }
  
  private node merge(boolean ascend, node start1, node start2) {
	  node n1 = start1;
	  node n2 = start2;
	  node t = null;
	  node first = null;
	  
	  while(n1 != null && n2 != null) {
		if ((n1.d < n2.d && ascend)
				|| (n1.d > n2.d && !ascend) 
				|| (n1.d == n2.d && ascend)){//sorting in ascending order
			numCompare += 3;//as we have 3 compares above
			if (t != null) {
				t.next = n1;
			}
			else {
				first = n1;
			}
			t = n1;
			n1 = n1.next;
			t.next = null;
		}
		else {//sorting in descending order
			numCompare += 3;
			if (t != null) {
				t.next = n2;
			}
			else {
				first = n2;
			}
			t = n2;
			n2 = n2.next;
			t.next = null;
		}
	  }
	  if (n1 != null) {
		  if (t != null) {
			  t.next = n1;
		  }
		  else {
			  first = n1;
		  }
	  }
	  else {
		  if (t != null) {
			  t.next = n2;
		  }
		  else {
			  first = n2;
		  }
	  }
	  return first;
  }
  
  public static void main(String[] args) {
    System.out.println("SlistMergeSort.java");
    SlistMergeSort u = new SlistMergeSort() ;
    u.testBench();
  }
}



package objects;
/**
 * File Name: SlistQuickSort 
 * Sort int slist using Quick Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistQuickSort.java
 */

class SlistQuickSort extends SlistSort{
 
  @Override
  protected void sort(boolean ascend) { 
	  //WRITE CODE HERE
	  if (!isAlreadySorted(ascend))//checking if the list is already sorted, if the list is already sorted we are not calling sort method
		  a.first = sort(ascend, a.first);
  }
  
  private boolean isAlreadySorted(boolean ascend) {
	  if (ascend) { //checking if the list is sorted in ascending order
		 node cur = a.first;
		 if (cur == null)
			 return true;
		 while (cur.next != null) {
			 node next = cur.next;
			 numCompare += 3; //as we are doing 3 compares below
			 if (cur.d > next.d 
					 || (cur.d == next.d && cur.t > next.t)) {//t is to ensure stable sort
				 return false;
			 }
		 	cur = next;
		 }
	  	return true;
	  }
	  else {//checking for descending order
		  node cur = a.first;
			 if (cur == null)
				 return true;
			 while (cur.next != null) {
				 node next = cur.next;
				 numCompare += 3;//as we have 3 compares below
				 if (cur.d < next.d 
						 || (cur.d == next.d && cur.t < next.t)) {
					 return false;
				 }
			 	cur = next;
			 }
		  	return true;
	  } 
  }
  
  private node sort(boolean ascend, node list) {
	  node pivot = list;
	  node list1 = null;
	  node last1 = null;
	  node list2 = null;
	  node last2 = null;
	  
	  //System.out.println(list);
	  if (pivot == null || pivot.next == null)
		  return pivot;
	  
	  node cur = pivot.next;
	  while (cur != null) {
		  if ((cur.d < pivot.d && ascend)
				  || (cur.d > pivot.d && !ascend)//comparing with pivot element & ensuring the stable sort
				  || (cur.d == pivot.d && cur.t < pivot.t && ascend)
				  || (cur.d == pivot.d && cur.t > pivot.t && !ascend)){
			  numCompare += 6;// as we are doing 6 compares above
			  if (last1 == null) {
				  list1 = cur;
			  }
			  else {
				  last1.next = cur;
			  }
			  last1 = cur;
			  cur = cur.next;
		  } 
		  else {
			  numCompare += 6;
			  if (last2 == null) 
				  list2 = cur;
			  else
				  last2.next = cur;
			  last2 = cur;
			  cur = cur.next;
		  }
	  }
	  if (last1 != null) 
		  last1.next = null;
	  if (last2 != null)
		  last2.next = null;
	  //getting the 2 partitions, node 1 and node 2 
	  node node1 = sort(ascend, list1);
	  node node2 = sort(ascend, list2);	
	  node result = node1;
	
	  while(node1 != null && node1.next != null)
		  node1 = node1.next;
	  if (node1 != null) {
		  node1.next = pivot;
	  }
	  else {
		result = pivot;  
	  }
	  pivot.next = node2;
	  return result;
  }

  public static void main(String[] args) {
    System.out.println("SlistQuickSort.java");
    SlistQuickSort u = new SlistQuickSort() ;
    u.testBench();
  }

}
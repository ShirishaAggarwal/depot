package objects;

public class HauffmanNode implements Comparable<HauffmanNode>{
	    public String str; 	
	    public int freq;
	    public HauffmanNode left;
	    public HauffmanNode right;
	    public int id;
	    
	    public HauffmanNode() {
	    	str = "";
	    	freq = 0;
	    	left = null;
	    	right = null;
	    }
	    
	    public HauffmanNode(String str) {
	    	this.str = str;
	    	freq = 0;
	    	left = null;
	    	right = null;
	    }

	    public HauffmanNode(char ch, int noOfOccurences) {
	      str = ch+"";
	      freq = noOfOccurences;
	      left = null;
	      right = null;
	    }
	    
	    @Override
		public int compareTo(HauffmanNode other)
		{
	    	return Integer.compare(this.freq, other.freq);
		}
}

package objects;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class Hauffman {
	
	String str = "";
	boolean show = false;
	String dotfilename = "";		
	HauffmanBTree hauffmanTree = null;	
	
	HashMap<Character,Integer> occurencesMap = new HashMap<>();
	PriorityQueue<HauffmanNode> pq = new PriorityQueue<HauffmanNode>();
	HashMap<String, String> encodeMap = new HashMap<String, String>();
	String encodedString = null;
	
	public Hauffman(String s, boolean show, String dotfilename)
	{
		this.str = s;
		this.show = show;
		this.dotfilename = dotfilename;
		
		if (s == null || s.isEmpty()) {
			return;
		}
	
		System.out.println("\n ============= " + s + " +++++++++++++++++++");
		occurencesMap = populateMap();//populating the hash map with the occurrence of each character				
		Set<Character> set = occurencesMap.keySet();
		for (Character c : set) {
			System.out.println(c + " occurs " +  occurencesMap.get(c) + " times");
			pq.add(new HauffmanNode(c, occurencesMap.get(c)));
		}		
				
		//Make a Binary Tree from the queue
		hauffmanTree = new HauffmanBTree(pq);
		
		hauffmanTree.assignIDs();
		
		//make sure it is printed in order of frequency
		hauffmanTree.printBTree();	
		
		//printing into the dot file
		hauffmanTree.printToFile(str, dotfilename);
	}	
	
	public String decode()
	{
		//traverse the tree and get a string map of 0's and 1's for each character
		System.out.println("============Code for each character in " + str + "===========================");
		encodeMap = hauffmanTree.getEncodeMap();
		
		int l = str.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l; ++i) {			
			sb.append(encodeMap.get(str.charAt(i)+""));
		}
		encodedString = sb.toString();
		System.out.println("=============== Original String ===========");
		System.out.println(str);
		System.out.println("=============== Decoded String ============");
		System.out.println(encodedString);
		return encodedString;
	}
	
	public String encode()
	{
		String recoveredString = hauffmanTree.getString(encodedString);
		System.out.println("======= Recovered String ============");
		System.out.println(recoveredString);
		return recoveredString;
	}
	
	private HashMap<Character,Integer> populateMap()
	{
		int len = str.length();
		for(int i=0;i<len;i++)
		{
			char c = str.charAt(i);
			if(!occurencesMap.containsKey(c))
			{
				occurencesMap.put(c, 1);
			}
			else
			{
				occurencesMap.put(c, occurencesMap.get(c)+1);
			}
		}
		return occurencesMap;
	}
		
}

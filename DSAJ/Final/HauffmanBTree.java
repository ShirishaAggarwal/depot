package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HauffmanBTree {

	public HauffmanNode root = null;
	
	public HauffmanBTree(PriorityQueue<HauffmanNode> pq) {
		while (!pq.isEmpty()) {
			HauffmanNode node1 = pq.poll();
			HauffmanNode node2 = null;
			if (!pq.isEmpty()) {
				node2 = pq.poll();
			}
			else {
				root = node1;
				break;
			}			
			HauffmanNode newNode = new HauffmanNode();
			newNode.left = node1;
			newNode.right = node2;
			newNode.str = node1.str+((node2 != null) ? node2.str : "");
			newNode.freq = node1.freq+((node2 != null) ? node2.freq : 0);			
			pq.add(newNode);
		}
	}
	
	//assigning id's to use in dot file
	public void assignIDs()
	{
		int count = 1;
	
		if (root != null) {
			Queue<HauffmanNode> q = new LinkedList<HauffmanNode>();
			q.add(root);
			while (!q.isEmpty()) {				
				HauffmanNode node = q.poll();
				node.id = count;
				if (isLeafNode(node)) {
					//Do nothing
					;
				}
				// One kid parent is not possible, hence skipping it
				else {
					q.add(node.left);
					q.add(node.right);
				}
				count++;
			}
		}
	}
	
	public void printBTree()
	{		
		System.out.println("==== Tree is build in this order =============");
		//using level order traversal to print the tree
		if (root != null) {
			Queue<HauffmanNode> q = new LinkedList<HauffmanNode>();
			q.add(root);
			while (!q.isEmpty()) {				
				HauffmanNode node = q.poll();
				if (isLeafNode(node)) {					
					System.out.println("Leaf node " + node.id + " Character is " + node.str + " Weight is " + node.freq);
				}
				// One kid parent is not possible, hence skipping it
				else {
					q.add(node.left);
					q.add(node.right);
					String left = "";
					String right = "";
					if(isLeafNode(node.left))					
						left = node.left.str;
					if(isLeafNode(node.right))
						right = node.right.str;
					System.out.println("Internal node " + node.id + " : Left " + left + "(" + node.left.freq + "), Right " + right + "(" + node.right.freq + ") Weight = " + node.freq);
				}
			}
		}
	}
	
	public void printToFile(String originalString, String dotFileName) {
		PrintWriter out = null;
		try {
			String dotFilePath = "C:\\Users\\Shirisha\\workspace\\ucsc-dsaj\\DSAJ\\DOTFiles\\"+dotFileName;
			File dotFile = new File(dotFilePath);
			out = new PrintWriter (dotFile);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			return;
		}
		
		//start writing into dot file
	    out.println("digraph g {");
	    out.println("label = \"" + originalString + "\"");
		if (root != null) {
			Queue<HauffmanNode> q = new LinkedList<HauffmanNode>();
			q.add(root);
			while (!q.isEmpty()) {				
				HauffmanNode node = q.poll();
				if (isLeafNode(node)) {					
					if (root == node) {
						String str = null;						
						if (node.str.equals(" "))
							str = "\""+node.id+"\\n"+node.freq+"\\nblank\"";
						else
							str = "\""+node.id+"\\n"+node.freq+"\\n"+node.str+"\"";	
								
						out.println(str);
					}
				}
				// one kid parent is not possible, hence skipping it
				else {
					q.add(node.left);
					q.add(node.right);					
					String str1 = "\""+node.id+"\\n"+node.freq+"\"->\""+node.left.id+"\\n"+node.left.freq;
					String str2 = "\""+node.id+"\\n"+node.freq+"\"->\""+node.right.id+"\\n"+node.right.freq;
					if(isLeafNode(node.left)) {						
						if (node.left.str.equals(" ")) {
							str1 += "\\nblank";
						}
						else {
							str1 += "\\n"+node.left.str;
						}
					}
					if(isLeafNode(node.right)) {						
						if (node.right.str.equals(" ")) {
							str1 += "\\nblank";
						}
						else {
							str2 += "\\n"+node.right.str;
						}
					}
					str1 += "\"[color=red]";
					str2 += "\"[color=blue]";
					out.println(str1);
					out.println(str2);
				}
			}
		}
		out.println("}");
		out.close();
	}
	
	public HashMap<String, String> getEncodeMap() {
		HashMap<String, String> encodeMap = new HashMap<String, String>();
		if (root != null) {
			// Will happen in case of single character string
			if (isLeafNode(root)) {
				encodeMap.put(root.str, "0");
				return encodeMap;
			}
			
			encodeMap.put(root.str, "");
			Queue<HauffmanNode> q = new LinkedList<HauffmanNode>();
			q.add(root);
			while (!q.isEmpty()) {
				HauffmanNode node = q.poll();
				if (isLeafNode(node)) {
					System.out.println(node.str + " has Code " + encodeMap.get(node.str));
					continue;
				}
				// one kid parent is not possible, hence skipping it
				else {
					encodeMap.put(node.left.str, encodeMap.get(node.str)+"0");
					q.add(node.left);
					encodeMap.put(node.right.str, encodeMap.get(node.str)+"1");
					q.add(node.right);
				}
			}
		}
		return encodeMap;
	}
	
	public String getString(String encodedString) {		
		StringBuffer sb = new StringBuffer();
		int l = encodedString.length();
		int i = 0;
		HauffmanNode cur = root;
		while(i < l) {
			char ch  = encodedString.charAt(i);
			if (ch == '0') {
				// will happen in case of single character string
				if (isLeafNode(cur)) {
					sb.append(cur.str);
					i++;
					continue;
				}
				
				cur = cur.left;
				if (isLeafNode(cur)) {
					sb.append(cur.str);
					cur = root;
				}
			}
			else if (ch == '1') {
				cur = cur.right;
				if (isLeafNode(cur)) {
					sb.append(cur.str);
					cur = root;
				}
			}
			i++;
		}		
		return sb.toString();
	}
	
	public boolean isLeafNode(HauffmanNode node)
	{
		if(node.right == null && node.left == null)
			return true;
		else
			return false;
	}
	
	public boolean hasOneKid(HauffmanNode node)
	{
		if((node.left == null && node.right != null) || (node.right == null && node.left != null))
			return true;
		else
			return false;
	}
	
	public HauffmanNode getOneKidNode(HauffmanNode node)
	{
		if(node.left == null && node.right != null)
			return node.right;
		else if(node.right == null && node.left != null)
			return node.left;
		else
			return null;
	}
	
	public boolean isLeftKid(HauffmanNode parent, HauffmanNode kid) 
	{
		return parent.left == kid;
	}
}

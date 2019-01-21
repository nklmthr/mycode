package com.nklmthr.practice.test;



public class Test {

	public static void main(String[] args) {
//		Test t = new Test();
//		t.isBalanced(null);
		
		boolean check = Boolean.valueOf("Hello ");
		System.out.println(check);
		
		

	}
	


	public boolean isBalanced(Node n)
	{
	    Node root = new Node();
	    Node l1 = new Node ();
	    Node r1 = new Node();
	    root.left = l1;
	    root.right = r1;
	    boolean check = getHeight(root, 0, 0);
		System.out.println(check);
	      return true;
	    
	}
	private boolean getHeight(Node n, int known_height, int current_count)
	{
	    if(n == null)
	        return true ;
	    
	    
	    boolean result = getHeight(n.left, known_height, current_count++);
	    if(known_height == 0)
	        known_height = current_count;
	        
	      if(current_count != known_height || result  == false )
	          return false;
	          
	    result  = getHeight(n.right, known_height, current_count++);
	      if(current_count != known_height || result  == false )
	          return false;    
	          
	     return true;
	        
	}
	/**
	 * 
	 */
	private void createNewßEntry() {
		
	}

}
class Node{
	Node left;
	Node right;
}


package ExpressionTree;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class simpleExpressionTree {
	private Node root;
	
	
	
	/**
	 * Creates a new ExpressionTree
	 */
	public simpleExpressionTree() {
		this.root = null;
	}
	
	
	/**
	 * Checks if the ExpressionTree is empty or not
	 * @return true if empty else false
	 */
	public boolean isEmpty() {
		return root == null;
	}
	

	/**
	 * Checks if the Object is a operator or not
	 * If x is a Node it's converted to char 
	 * @param x the object to check
	 * @return true if operator else false
	 */
	private boolean isOperator(Object x) {
		char e;
		if(x instanceof Node) {
			e = ((Node)x).data;
		} else {
			e = (char)x;
		}
		
		return e == '/' ||  e == '-' ||  e == '+' ||  e == '*';
	}
	
	/**
	 * Created a expression tree after general rules for a expression binary tree
	 * 
	 * @param s string with expression
	 * 
	 */
	public Node createTreeV2(List ys) {
//		List<char[]> ys = Arrays.asList(xs);
		System.out.print("YS" + ys);
		
		Node temp = new Node((char)ys.get(0));
		
		if(!isOperator(temp)) {
			
			if(root == null) {
				root = temp;
				return temp;
			}
			ys.remove(0);
			temp.left = createTreeV2(ys);
			
			temp.right = createTreeV2(ys);
			}
		root = temp;
		return temp;
		
		
	}
	
	
	public int calculateTree() {
		if(root == null) {
			return 0;
		} else {
			return calculateTree(root);	
		}
	}
	
	private int calculateTree(Node e) {
		switch(e.data) {
		case '-' : return calculateTree(e.left) - calculateTree(e.right);
		case '+' : return calculateTree(e.left) + calculateTree(e.right);
		case '/' : return calculateTree(e.left) / calculateTree(e.right);
		case '*' : return calculateTree(e.left) * calculateTree(e.right);
		default : return e.toInt();
		}
	}
	
	
	/**
	 * Created a expression tree after general rules for a expression binary tree
	 * 
	 * @param xs is the elements to add.
	 * 
	 */
	public void createTree(char xs[]) {
		Stack<Node> list = new Stack<Node>();  // 
		Node t;
		Node t1;
		Node t2;
		
		for(char s : xs) {
			if(!isOperator(s)) {
				t = new Node(s);
				list.push(t);
			} else {
				t = new Node(s);
				
				t1 = list.pop();      
                t2 = list.pop();
 
              
                t.right = t1;
                t.left = t2;
                
                
                list.push(t);
			}
		}
		t = list.peek();
		list.pop();

		root  = t;
	}
	
	
	/**
	 * Prints the binary tree in inorder
	 * 
	 * @param e is the root the print will have as starting point
	 * 
	 */
	
	private void printInOrder(Node e) {
		if(e != null) {
			printInOrder(e.left);
			System.out.print(e.data + " ");
			printInOrder(e.right);
		}
		
	}
	/**
	 * Prints the binary tree in inorder
	 * 
	 */
	public void printInOrder() {
		printInOrder(root);
	}
	


	private static class Node {
		char data;
		Node left;
		Node right;

		/**
		 * Creates a new Node
		 * @param x the char which is set as the Node's data
		 */
		private Node(char x) {
			data = x;
			left = null;
			right = null;
		}
		
		/**
		 * convert data to int
		 */
		public int toInt() {
			int temp = Character.getNumericValue(data);
			return temp;
		}
	}


	public static void main(String args[]) {
		 
		simpleExpressionTree et = new simpleExpressionTree();
        String postfix = "*+84-7/5";
        
        List test = new ArrayList();
        test.add('*');
        test.add('+');
        test.add('8');
        test.add('4');
        test.add('-');
        test.add('7');
        test.add('/');
        test.add('5');
  
        
        
//        char[] charArray = postfix.toCharArray();
//        et.createTreeV2(charArray);
        et.createTreeV2(test);
        et.printInOrder();
        System.out.println();
       // System.out.println(et.calculateTree());
 
    }

}
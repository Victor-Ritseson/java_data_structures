package TreeMap;

import java.util.Comparator;

public class SimpleTreeMap<K, V> {
	private TreeNode<K,V> root;
	private int size;
	private Comparator<V> comp;
	
	public SimpleTreeMap() {
		size = 0;
		root = null;
		comp = ((a,b) -> ((Comparable<V>) a).compareTo(b));
	}
	
	public SimpleTreeMap( Comparator<V> comp) {
		size = 0;
		root = null;
		this.comp = comp;
	}
	
	public V put(K key, V value) {
		TreeNode<K,V> addItem = new TreeNode(key, value);
		if(root == null) {
			root = addItem;
			size++;
			return addItem.value;
		} else {
			return recPut(addItem, root);
		}
	}
	
	private V recPut(TreeNode<K,V> newItem, TreeNode<K,V> n) {
		int cc = comp.compare(n.value, newItem.value);
		if( cc == 0) {
			V oldValue = n.value;
			n = newItem;
			return oldValue;
		} else {
			
			if(cc < 0) {
				if(n.left == null) {
					n.left = newItem;
					size ++; 
				} else {
					return recPut(newItem, n.left);
				}
	
			}
			
			if(cc > 0) {
				if(n.right == null) {
					n.right = newItem;
					size ++; 
				} else {
					return recPut(newItem, n.right);
				}
	
			}
		}
		return newItem.value;
	}
	
	public int size() {
		return size;
	}
	
	
	public void clear() {
		root = null;
	}
	

	

	
	
	
	

	
	
	private static class TreeNode<K,V>{
		private K key;
		private V value;
		private TreeNode<K,V> left;
		private TreeNode<K,V> right;
		
		private TreeNode(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
		
	}
}



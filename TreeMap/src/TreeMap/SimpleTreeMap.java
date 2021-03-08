package TreeMap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


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
	
	public void putAll(SimpleTreeMap<K,V> add) {
		if(root == null) {
			root = add.root;
			size = add.size;
		} else {
			Set<MyEntry<K,V>> temp = add.entrySet();
			for(MyEntry<K,V> s : temp) {
				put(s.getKey(),s.getValue());
			}	
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
	

	public boolean containsKey(Object searchKey) {
		if(searchKey == null) {
			throw new NullPointerException();
		} else {
			Set<K> keys = keySet();
			return keys.contains((K) searchKey);	
		}
	}
	
	public Set<K> keySet(){
		Set<K> temp = new TreeSet<K>();
		if(root == null) {
			return temp;
		} else {
			return recKeySet(root, temp);
		}
	}
	
	private Set<K> recKeySet(TreeNode<K,V> n, Set<K> list){
		if(n != null) {
			list.add(n.key);
			if(n.left != null) {
				recKeySet(n.left, list);
			}
			
			if(n.right != null) {
				recKeySet(n.right, list);
			}
		}
		return list;
	}
	
	
	public Set<MyEntry<K,V>> entrySet(){
		Set<MyEntry<K,V>> temp = new HashSet<MyEntry<K,V>>();
		if(root == null) {
			return temp;
		} else {
			return entrySet(root, temp);
		}
	}
	
	public Set<MyEntry<K,V>> entrySet(TreeNode<K,V> n, Set<MyEntry<K,V>> list){
		if(n != null) {
			MyEntry<K,V> entry = new MyEntry(n.key,n.value);
			list.add(entry);
			
			if(n.left != null) {
				list = entrySet(n.left, list);
			}
			if(n.right != null) {
				list = entrySet(n.right, list);
			}
		}
		return list;
	}
	
	
	
	public K firstKey() {
		if(root == null) {
			return null;
		} else {
			return root.key;
		}
	}
	
	public V firstValue() {
		if(root == null) {
			return null;
		} else {
			return root.value;
		}
	}
	
	public V get(Object key) {
		if(root == null) {
			return null;
		} else {
			return recGet(root, (K)key);
		}
	}
	
	private V recGet(TreeNode<K,V> n, K searchKey) {
		if(root != null) {
			if(n.key.equals(searchKey)) {
				return n.value;
			} else {
				if(n.left != null) {
					return recGet(n.left,searchKey);
				} else if(n.right != null) {
					return recGet(n.right, searchKey);
				}	
			}
		}
		
		return null;
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



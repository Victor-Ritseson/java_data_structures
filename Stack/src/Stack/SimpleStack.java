package Stack;

public class SimpleStack<E> {
	Node<E> first;
	int size;
	
	
	public SimpleStack(){
		first = null;
		size = 0;
	}
	
	public E peek() {
		if(first == null) {
			return null;
		} else {
			return first.element;
		}
	}
	
	public E pop() {
		if(first == null) {
			return null;
		} else {
			E temp = first.element;
			first = first.next;
			return temp;
		}
	}
	
	
	public E push(E item) {
		Node<E> newItem = new Node<E>(item);
		if(first == null) {
			first = newItem;
		} else {
			Node<E> temp = first;
			
			while(temp != null) {
				if(temp.next == null) {
					temp.next = newItem;
					break;
				} 
				temp = temp.next;
			}
		}
		size++;
		return newItem.element;
	}
	
	
	public int size() {
		return size;
	}
	
	public int search(Object o) {
		if(first == null) {
			return -1;
		}
		
		Node<E> temp = first;
		
		int i = 1;
		while(temp != null) {
			if(temp.element.equals((E)o)) {
				return i;
			}
			temp = temp.next;
			i++;
		}
		
		
		return -1;
	}
	

	
	
	private static class Node<E> {
		E element;
		Node<E> next;

		/**
		 * Creates a new Node
		 * @param x the char which is set as the Node's data
		 */
		private Node(E x) {
			element = x;
			next = null;
		}
}
}

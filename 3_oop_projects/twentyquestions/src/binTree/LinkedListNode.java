package binTree;

/**
 * LinkedListNode.java
 * 
 * Creates a node
 * 
 * @author adelyn.yeoh
 *
 * @param <T>
 * 
 *            CS201 - Assignment 5
 */

public class LinkedListNode<T> {

	// T stands for "Type"
	// Initialize variables
	T data;
	LinkedListNode<T> next; // Pointer

	/**
	 * Constructor
	 * 
	 * @param data
	 *            T
	 */
	public LinkedListNode(T data) {
		// When creating a new node, the pointer is automatically set to null
		next = null;
		this.data = data;
	}

	/**
	 * Get the data stored at this node.
	 * 
	 * @return data T
	 **/
	public T getData() {
		return data;
	}

	/**
	 * Set the data stored at this node.
	 * 
	 * @param data
	 *            T
	 **/
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get (pointer to) next node.
	 * 
	 * @return next LinkedListNode<T>
	 **/
	public LinkedListNode<T> getNext() {
		return next;
	}

	/**
	 * Set the next pointer to passed node.
	 * @param node LinkedListNode<T>
	 **/
	public void setNext(LinkedListNode<T> node) {
		this.next = node;
	}

	/**
	 * Returns a String representation of this node.
	 * 
	 * @return data.toString() String
	 **/
	public String toString() {

		return data.toString();
	}

}

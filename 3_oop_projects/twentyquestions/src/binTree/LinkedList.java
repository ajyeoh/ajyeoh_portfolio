package binTree;

/**
 * LinkedList.java
 * 
 * Creates a Linked list object
 * 
 * @author adelyn.yeoh
 *
 * @param <T>
 * 
 *            CS201 - Assignment 5
 */

public class LinkedList<T> {

	// Instance variables
	private LinkedListNode<T> currentNode;
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	private int size;

	/**
	 * Constructor - invoked when creating a new Linked list
	 */
	public LinkedList() {

		// List is empty when first created
		head = null;
		size = 0;
	}

	/**
	 * Constructor creates a new LinkedList with a head created
	 * 
	 * @param data
	 *            T
	 */
	public LinkedList(T data) {

		head = new LinkedListNode<T>(data);
		size = 1;
	}

	/**
	 * Get data stored in head node of list.
	 * 
	 * @return head.getData() T
	 **/
	public T getFirst() {
		return head.getData();
	}

	/**
	 * Get the head node of the list.
	 * 
	 * @return head T
	 * 
	 **/
	public LinkedListNode<T> getFirstNode() {
		return head;
	}

	/**
	 * Get data stored in tail node of list.
	 * 
	 * @return tail.getData T
	 **/
	public T getLast() {
		// Set the tail node
		getLastNode();

		// Return tail data
		return tail.getData();

	}

	/**
	 * Get the tail node of the list.
	 * 
	 * @return tail LinkedListNode<T>
	 **/
	public LinkedListNode<T> getLastNode() {
		// Start with the head and look through the entire list to find the tail
		currentNode = head;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		// Store the tail value
		tail = currentNode;
		return tail;
	}

	/**
	 * Insert a new node with data at the head of the list.
	 * 
	 * @param data
	 *            T
	 * 
	 **/
	public void insertFirst(T data) {
		// Temporary variable to store the old head value
		LinkedListNode<T> temp;
		temp = head;
		// Create new data in head node
		head = new LinkedListNode<T>(data);
		// Push old values behind head node
		head.next = temp;
		// Increase list size
		size = size + 1;

	}

	/**
	 * Insert a new node with data after currentNode
	 * 
	 * @param currentNode
	 *            LinkedListNode<T>
	 * @param data
	 *            T
	 **/
	public void insertAfter(LinkedListNode<T> currentNode, T data) {
		// Temporary variable to store the old currentNode.next data
		LinkedListNode<T> temp;
		temp = currentNode.next;
		// Create new node with data after currentNode
		currentNode.next = new LinkedListNode<T>(data);
		// Link the rest of the list to the newly created node
		currentNode.next.next = temp;
		// Increase the list size
		size = size + 1;
	}

	/**
	 * Insert a new node with data at the tail of the list.
	 * 
	 * @param data
	 *            T
	 **/
	public void insertLast(T data) {
		// Find the tail node
		currentNode = getLastNode();
		// Create a new node with data after the tail node
		currentNode.next = new LinkedListNode<T>(data);
		// Update the tail node to the newly created node
		tail = currentNode.next;
		// Increase the list size
		size = size + 1;
	}

	/**
	 * Remove head node
	 **/
	public void deleteFirst() {
		// Set the head to the next node
		head = head.next;
		// Decrease the list size
		size = size - 1;

	}

	/**
	 * Remove node at current point
	 * 
	 * @param currentNode
	 *            LinkedListNode<T>
	 */
	public void deleteAtPoint(LinkedListNode<T> currentNode) {
		currentNode.setData(currentNode.getNext().getData());
		deleteNext(currentNode);

	}

	/**
	 * Remove tail node
	 **/
	public void deleteLast() {
		// Method is split into two types: lists that are larger than 3 or
		// smaller than 3. If we have lists that are larger than 3, it is
		// redundant to find the tail by getLastNode() and then try to find
		// currentNode.next.next != null. However, the limitation of this
		// approach is that there exist lists that are shorter than 3, so
		// calling currentNode.next.next will yield null, and will not actually
		// delete last node.

		if (size() > 3) {
			// For lists longer than 3, start with the head and look through the
			// entire list nodes that are two steps away from currentNode yield
			// null value
			currentNode = head;
			while (currentNode.next.next != null) {
				currentNode = currentNode.next;
			}

		} else {
			// For shorter lists, just find the tail and look for the case when
			// currentNode.next is not equal to tail
			getLastNode();
			currentNode = head;
			while (currentNode.next != tail) {
				currentNode = currentNode.next;
			}
		}
		// Update the tail node
		currentNode.next = null;
		tail = currentNode;

		// Decrease the list size
		size = size - 1;
	}

	/**
	 * Remove node following currentNode If no node exists (i.e., currentNode is
	 * the tail), do nothing
	 * 
	 * @param currentNode
	 *            LinkedListNode<T>
	 **/
	public void deleteNext(LinkedListNode<T> currentNode) {
		// A tail node has a pointer that is null, as long as the pointer is not
		// null, delete the next node by setting it to the node after
		if (currentNode.next != null) {
			currentNode.next = currentNode.next.next;
			// Decrease the list size
			size = size - 1;
		}
	}

	/**
	 * Return the number of nodes in this list.
	 * 
	 * @return size int
	 **/
	public int size() {
		return size;
	}

	/**
	 * Check if list is empty.
	 * 
	 * @return true if list contains no items.
	 **/
	public boolean isEmpty() {
		// Checks if size is 0
		return size == 0;
	}

	/**
	 * Return a String representation of the list.
	 * 
	 * @return listOfString String
	 **/
	public String toString() {
		// Initialize listOfString
		String listOfString = "";
		// Start with the head node and save the String representation of
		// the nodes
		currentNode = head;
		while (currentNode != null) {
			listOfString = listOfString + " " + currentNode.toString();
			currentNode = currentNode.next;
		}

		System.out.println("list of string: " + listOfString);
		return listOfString;
	}

	/**
	 * Get head
	 * 
	 * @return head LinkedListNode<T>
	 */
	public LinkedListNode<T> getHead() {
		return head;
	}

	/**
	 * Set head data
	 * 
	 * @param head
	 *            LinkedListNode<T>
	 */
	public void setHead(LinkedListNode<T> head) {
		this.head = head;
	}

	/**
	 * Get tail
	 * 
	 * @return tail LinkedListNode<T>
	 */
	public LinkedListNode<T> getTail() {
		return tail;
	}

	/**
	 * Set tail
	 * 
	 * @param tail
	 *            LinkedListNode<T>
	 */
	public void setTail(LinkedListNode<T> tail) {
		this.tail = tail;
	}

	/**
	 * Get size of LinkedList
	 * 
	 * @return size int
	 */
	public int getSize() {
		return size;
	}

}

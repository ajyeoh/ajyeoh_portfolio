package binTree;

/**
 * DefaultBinaryTree.java
 * 
 * Class that implements BinaryTree
 * 
 * @author adelyn.yeoh
 *
 *         CS201 - Assignment 7
 */
public class DefaultBinaryTree<T> implements BinaryTree<T> {

	// Root - a node
	BinaryTreeNode<T> root;
	BinaryTreeNode<T> child;

	/**
	 * Constructor that does not create a root
	 */
	public DefaultBinaryTree() {
		root = null;
	}

	/**
	 * Constructor that creates a root
	 * 
	 * @param headData
	 *            T
	 */
	public DefaultBinaryTree(T headData) {
		root = new DefaultBinaryTreeNode<T>(headData);
	}

	/**
	 * Get root
	 * 
	 * @return root BinaryTreeNode<T>
	 */
	@Override
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Set root
	 * 
	 * @param root
	 *            BinaryTreeNode<T>
	 */
	@Override
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	/**
	 * Check if tree is empty
	 */
	@Override
	public boolean empty() {

		// Tree is empty if root is null
		if (root == null) {
			// Return true
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inOrder List.
	 **/
	@Override
	public LinkedList<T> inorderTraversal() {

		// Kick start the recursion by starting at the root of the tree, and
		// return the linkedList created
		return inorderTraversal(new LinkedList<T>(), root);

	}

	/**
	 * Recursive method for the in-order traversal
	 * 
	 * @param someList
	 *            LinkedList<T>
	 * @param curNode
	 *            BinaryTreeNode<T>
	 * @return someList LinkedList<T> - the list with the values added to the
	 *         list
	 */
	private LinkedList<T> inorderTraversal(LinkedList<T> someList,
			BinaryTreeNode<T> curNode) {

		// Base case: if curNode is null
		if (curNode == null) {
			return someList;
		} // Base case: if curNode is leaf
		else if (curNode.isLeaf()) {

			// Add data to the front of the list
			someList.insertFirst(curNode.getData());
			return someList;
		} else {
			// Add right child to the front of the list
						
			inorderTraversal(someList, curNode.getRightChild());
			// Add root to front of the list
			someList.insertFirst(curNode.getData());
			// Add left child to front of list
			inorderTraversal(someList, curNode.getLeftChild());

		}

		// Return list
		return someList;
	}

	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * (root > left subtree > right subtree)
	 * 
	 * @return preorder LinkedList<T>
	 **/
	@Override
	public LinkedList<T> preorderTraversal() {

		// Kick start the recursion by starting at the root of the tree, and
		// return the linkedList created
		return preorderTraversal(new LinkedList<T>(), root);
	}

	/**
	 * Recursive method for the pre-order traversal method
	 * 
	 * @param someList
	 *            LinkedList<T>
	 * @param curNode
	 *            BinaryTreeNode<T>
	 * @return someList LinkedList<T> the list with the values added to the list
	 */
	private LinkedList<T> preorderTraversal(LinkedList<T> someList,
			BinaryTreeNode<T> curNode) {
		// Base case: if curNode is null
		if (curNode == null) {
			return someList;
		} // Base case: if curNode is leaf
		else if (curNode.isLeaf()) {
			// Add data to the front of the list
			someList.insertFirst(curNode.getData());
			// Return the list
			return someList;
		} else {
			// We want the order: root > left subtree > right subtree
			// Add right child to front of list
			preorderTraversal(someList, curNode.getRightChild());

			// Add left child to end of list
			preorderTraversal(someList, curNode.getLeftChild());

			// Add root to front of list
			someList.insertFirst(curNode.getData());
		}

		// return list
		return someList;
	}

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 **/
	@Override
	public LinkedList<T> postorderTraversal() {

		// Kick start the recursion by starting at the root of the tree, and
		// return the linkedList created
		return postorderTraversal(new LinkedList<T>(), root);
	}

	/**
	 * Recursive method for the postorder traversal method
	 * 
	 * @param someList
	 *            LinkedList<T>
	 * @param curNode
	 *            BinaryTreeNode<T>
	 * @return someList LinkedList<T> the list with the values added to the list
	 */
	private LinkedList<T> postorderTraversal(LinkedList<T> someList,
			BinaryTreeNode<T> curNode) {

		// Base case: if curNode is null
		if (curNode == null) {
			return someList;
		} // Base case: if curNode is leaf
		else if (curNode.isLeaf()) {
			// Add data to the front of the list
			someList.insertFirst(curNode.getData());
			// Return list
			return someList;
		} else {
			// We want the order: left subtree > right subtree > root

			// Add root to front of the list
			someList.insertFirst(curNode.getData());

			// Add right child data to front of list
			postorderTraversal(someList, curNode.getRightChild());

			// Add left child data to front of list
			postorderTraversal(someList, curNode.getLeftChild());

		}

		// Return list
		return someList;
	}

	/**
	 * Method to turn the inorder LinkedList into a string
	 * 
	 * @return String of LinkedList that went through inorder traversal
	 */
	@Override
	public String inorderString() {
		return inorderTraversal().toString();
	}

	/**
	 * Method to turn the preorder LinkedList into a string
	 * 
	 * @return String of LinkedList that went through preorder traversal
	 */
	@Override
	public String preorderString() {
		return preorderTraversal().toString();
	}

	/**
	 * Method to turn the postorder LinkedList into a string
	 * 
	 * @return String of LinkedList that went through postorder traversal
	 */
	@Override
	public String postorderString() {
		return postorderTraversal().toString();
	}

}

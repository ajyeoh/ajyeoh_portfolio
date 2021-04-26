package binTree;

/**
 * BinaryTree.java
 * CS 201
 * Heather Pon-Barry
 **/

/**
 * BinaryTree is the interface for a basic binary tree. Actual implementation of
 * a binary tree is Assignment 9.
 * 
 * @author Audrey Lee
 **/
public interface BinaryTree<T> {

	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 **/
	public BinaryTreeNode<T> getRoot();

	/**
	 * Set the root node for this tree.
	 * 
	 * @param root
	 *            BinaryTreeNode<T>
	 **/
	public void setRoot(BinaryTreeNode<T> root);

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 **/
	public boolean empty();

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 **/
	public LinkedList<T> inorderTraversal();

	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * @return preorder List.
	 **/
	public LinkedList<T> preorderTraversal();

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 **/
	public LinkedList<T> postorderTraversal();

	/**
	 * Print the tree using inorder traversal.
	 * 
	 * @return inorder String
	 **/
	public String inorderString();

	/**
	 * Print the tree using preorder traversal.
	 * 
	 * @return preorder String
	 **/
	public String preorderString();

	/**
	 * Print the tree using postorder traversal.
	 * 
	 * @return postorder String
	 **/
	public String postorderString();
}

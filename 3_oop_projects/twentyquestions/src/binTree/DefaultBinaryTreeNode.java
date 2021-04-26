package binTree;

/**
 * DefaultBinaryTreeNode.java
 * 
 * Added an ability to modify data of a particular node.
 * 
 * @author adelyn.yeoh
 *
 * @param <T>
 *            generic type
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {

	// Data variable of type T
	T data;

	// Left child
	BinaryTreeNode<T> leftChild;

	// Right child
	BinaryTreeNode<T> rightChild;

	/**
	 * Constructor: creates new node with data. Set left and right child to
	 * null.
	 * 
	 * @param data
	 *            T - data that is held by the node
	 */
	public DefaultBinaryTreeNode(T data) {
		// Set data to this node
		this.data = data;

		// Set Left and Right Children to null
		setLeftChild(null);
		setRightChild(null);

	}

	/**
	 * Return data of the node
	 * 
	 * @return data T
	 */
	@Override
	public T getData() {
		return data;
	}

	/**
	 * Allow modification of data of the node
	 * 
	 * @param data
	 *            T
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Return leftChild node
	 * 
	 * @return leftChild BinaryTreeNode<T>
	 */
	@Override
	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * Return rightChild node
	 * 
	 * @return rightChild BinaryTreeNode<T>
	 */
	@Override
	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	/**
	 * Set leftChild node
	 * 
	 * @param left
	 *            BinaryTreeNode<T>
	 */
	@Override
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.leftChild = left;
	}

	/**
	 * Set rightChild node
	 * 
	 * @param right
	 *            BinaryTreeNode<T>
	 */
	@Override
	public void setRightChild(BinaryTreeNode<T> right) {
		this.rightChild = right;

	}

	/**
	 * Method to check if node is a leaf (whether or not the leaf has children).
	 * Return true if leftChild and rightChild are both null. False, otherwise.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isLeaf() {

		// Check if leftChild and rightChild are both null
		if (leftChild == null && rightChild == null) {
			return true;
		} else {
			return false;
		}
	}

}

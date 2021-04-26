import java.io.File;
import java.util.StringTokenizer;

import rw.LotrTreeReader;
import rw.LotrTreeWriter;
import binTree.BinaryTree;
import binTree.BinaryTreeNode;
import binTree.DefaultBinaryTreeNode;

/**
 * LotrModel.java
 * 
 * Methods when user answers yes or answers no. Reads, writes binary tree.
 * 
 * @author adelyn.yeoh
 * 
 *         CS 201 - FINAL PROJECT
 *
 */
public class LotrModel {

	// Save info about tree
	private BinaryTree<String> gameTree;
	private BinaryTreeNode<String> curNode;

	// Game string is data from tree
	private String gameString;
	private String formattingString = "\n \n \n ";

	// Keep track of if game is won [for the purpose of buttons]
	private boolean gameWon;

	/**
	 * Constructor reads XML file and creates tree. Gets root data/first
	 * question.
	 */
	public LotrModel() {
		// Reads XML File
		gameTree = LotrTreeReader.readExpr("CharacterTree.xml");

		// Start at root
		curNode = gameTree.getRoot();

		// Get the root which is the first question
		gameString = formattingString + curNode.getData();

		gameWon = true;
	}

	/**
	 * When user answers yes
	 * 
	 * @return gameString String
	 */
	public String answerYes() {

		try {
			curNode = curNode.getLeftChild();

		} // Catch error If user keeps trying to answer yes when there is
			// nothing left to respond to. Do nothing
		catch (NullPointerException e) {
		}

		// When all answers have been exhausted
		if (curNode == null) {
			// If win game use this string
			if (gameWon == true) {
				gameString = formattingString + "This mirror is powerful.";
			} // if Galadriel lost game
			else {
				gameString = formattingString + "Let's try again";
			}
		} // When an answer has been reached
		else if (curNode.isLeaf()) {
			// Update gameString
			gameString = formattingString + "Did you think of "
					+ curNode.getData() + "?";
		} else {
			// Otherwise get next node
			gameString = formattingString + curNode.getData();
		}

		return gameString;
	}

	/**
	 * When user answers no
	 * 
	 * @return gameString String
	 */
	public String answerNo() {

		// First check: if curNode is null. Reach null if keeps hitting 'No'
		// button
		if (curNode == null) {
			return formattingString + "Start new game";
		} else if (!curNode.isLeaf()) {
			curNode = curNode.getRightChild();
			// Base Case: if curNode is leaf, get data.
			if (curNode.isLeaf()) {
				gameString = formattingString + "Did you think of "
						+ curNode.getData() + "?";
			} else {
				// Note that we don't have to care if a parent has a null child
				// because the way this tree is set up is that parents will
				// ALWAYS have two children
				gameString = formattingString + curNode.getData();
			}
		} else {
			// begin end sequence
			gameString = "Strange.";
			gameWon = false;
		}

		return gameString;
	}

	/**
	 * Method to clean responses taken from the user
	 * 
	 * @param info
	 *            String[]
	 * @return info String[]
	 */
	private String[] cleanResponse(String[] info) {

		// Checks if last character in string is a question mark
		int index = 1;
		while (info[1].charAt(info[1].length() - index) != '?'
				|| index == info[1].length() + 1) {
			index++;
		}

		// If no question mark found, add question mark to the string
		if (index == info[1].length() + 1) {
			info[1] = info[1] + "?";
		}

		// Ensures that last entry is a single word
		StringTokenizer checkString = new StringTokenizer(info[2]);
		info[2] = checkString.nextToken();

		return info;

	}

	/**
	 * Save character info
	 * 
	 * @param newCharacterInfo
	 *            String[]
	 * @return boolean to see if data has been saved
	 */
	private boolean saveInfo(String[] newCharacterInfo) {

		// Save a temporary node
		BinaryTreeNode<String> tempNode = new DefaultBinaryTreeNode<String>(
				curNode.getData());

		// Save character info
		BinaryTreeNode<String> characterNode = new DefaultBinaryTreeNode<String>(
				newCharacterInfo[0]);

		// If the answer to given question is yes
		if (newCharacterInfo[2].equalsIgnoreCase("Yes")) {
			// set curNode to the question given
			curNode.setData(newCharacterInfo[1]);

			// Added character, set to left child
			curNode.setLeftChild(characterNode);

			// Old answer set to right child
			curNode.setRightChild(tempNode);

			return true; // info saved
		} else if (newCharacterInfo[2].equalsIgnoreCase("No")) {

			// set curNode to the question given
			curNode.setData(newCharacterInfo[1]);

			// Added character, set to right child
			curNode.setRightChild(characterNode);

			// Old node set to left child
			curNode.setLeftChild(tempNode);

			return true; // info saved
		} else {
			System.out.println("invalid user response");

			return false;
		}
	}

	/**
	 * Save tree info to XML file
	 * 
	 * @param newCharInfo
	 *            String[]
	 */
	public void saveTree(String[] newCharInfo) {

		// Only save to XML file when program can save info to tree
		if (saveInfo(cleanResponse(newCharInfo)) == true) {

			// Write to XML file
			LotrTreeWriter.writeExpr(gameTree, new File("CharacterTree.xml"));
		}

	}

	/**
	 * Get gameString - data at each node
	 * 
	 * @return gameString String
	 */
	public String getGameString() {
		return gameString;
	}

	/**
	 * Get curNode
	 * 
	 * @return curNode BinaryTreeNode<String>
	 */
	public BinaryTreeNode<String> getCurNode() {
		return curNode;
	}

	/**
	 * Set curNode
	 * 
	 * @param curNode
	 *            BinaryTreeNode<String
	 */
	public void setCurNode(BinaryTreeNode<String> curNode) {
		this.curNode = curNode;
	}

	/**
	 * Get gameTree
	 * 
	 * @return gameTree BinaryTree<String>
	 */
	public BinaryTree<String> getGameTree() {
		return gameTree;
	}

	/**
	 * Set gameTree
	 * @param gameTree BinaryTree<String>
	 */
	public void setGameTree(BinaryTree<String> gameTree) {
		this.gameTree = gameTree;
	}

	/**
	 * isGameWon() ?
	 * @return gameWon boolean
	 */
	public boolean isGameWon() {
		return gameWon;
	}

	
}

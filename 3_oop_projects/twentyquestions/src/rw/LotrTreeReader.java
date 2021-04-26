package rw;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import binTree.BinaryTree;
import binTree.BinaryTreeNode;
import binTree.DefaultBinaryTree;
import binTree.DefaultBinaryTreeNode;

/**
 * LotrTreeReader.java
 * 
 * Modified code from sample code.
 * 
 * @author adelyn.yeoh
 *
 *         Final Project
 */
public class LotrTreeReader {
	/**
	 * Parses XML file.
	 * 
	 * @param file
	 *            String
	 * @return expression BinTree corresponding to file.
	 **/
	public static BinaryTree<String> readExpr(String file) {
		return readExpression(new File(file));
	}

	/**
	 * Parses XML file
	 * 
	 * @param file
	 *            File
	 * @return expression BinaryTree corresponding to file.
	 **/
	public static BinaryTree<String> readExpression(File file) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);

			return parseExprTree(document);

		} catch (SAXException sxe) {
			// Error generated during parsing)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();
		} catch (IOException ioe) {
			// I/O error
			ioe.printStackTrace();
		}

		return null;
	}

	/**
	 * Parses XML Document.
	 * 
	 * @return parsed BinTree.
	 **/
	private static BinaryTree<String> parseExprTree(Document document) {
		BinaryTree<String> tree = new DefaultBinaryTree<String>();

		// parse root
		Element root = (Element) document.getDocumentElement();

		tree.setRoot(parseExprNode(root));

		return tree;
	}

	/**
	 * Parses expr element.
	 * 
	 * @return BinTreeNode represented by element.
	 **/
	private static BinaryTreeNode<String> parseExprNode(Element element) {
		// base case: answer
		if (element.getAttribute("type").equals("answer")) {
			// must have exactly one non-text child
			// get children
			NodeList children = element.getChildNodes();

			for (int i = 0; i < children.getLength(); i++) {
				// if not text node
				if (children.item(i) instanceof Element) {
					Element answer = (Element) children.item(i);

					// get attribute by name
					return new DefaultBinaryTreeNode<String>(
							answer.getAttribute("value"));
				}
			}

			// error if gets to here
			return null;
		}
		// recursive case
		else {
			// get children
			NodeList children = element.getChildNodes();

			// iterate through, looking for operator and two operands
			// NOTE: operand order does not matter because operators are
			// commutative
			String commutativeOp = "";
			BinaryTreeNode<String> question1 = null;
			BinaryTreeNode<String> question2 = null;
			Element currentElt;

			for (int i = 0; i < children.getLength(); i++) {
				// if not text node
				if (children.item(i) instanceof Element) {
					currentElt = (Element) children.item(i);

					// test tag name
					// if operator
					if (currentElt.getTagName().equals("qn"))
						commutativeOp = currentElt.getAttribute("value");
					// otherwise, should be expression
					else if (currentElt.getTagName().equals("expr")) {
						// store in operand1, if nothing there yet
						if (question1 == null)
							question1 = parseExprNode(currentElt);
						// otherwise, put in operand2
						else
							question2 = parseExprNode(currentElt);
					}
				}
			}

			// create node
			BinaryTreeNode<String> exprNode = new DefaultBinaryTreeNode<String>(
					commutativeOp);
			// set left and right children; arbitrary order
			exprNode.setLeftChild(question1);
			exprNode.setRightChild(question2);

			return exprNode;
		}
	}
}

/**
 * TetrisGameTextController.java
 * 
 * @author adelyn.yeoh
 * CS 201 - TETRIS
 */

import java.util.Scanner;

public class TetrisGameTextController {

	private static int DOWN = 0;
	private static int RIGHT = 1;
	private static int LEFT = 2;
	private static int CW = 3;
	private static int CCW = 4;

	private static int moveType;
	private static String userInput;
	private static String welcome = "Welcome to Tetris!";
	private static String instructions1 = "Type down, right, left, CW, CCW to move pieces.";
	private static String instructions2 = "Type quit to end game.";

	private static String[] ending = { "END OF GAME.",
			"THANK YOU FOR PLAYING.", " YOUR SCORE IS: " };

	private static TetrisGrid model;
	private static TetrisGameTextView view;

	/**
	 * Constructor
	 * 
	 * @param userInput
	 *            String
	 */
	public TetrisGameTextController(String userInput) {
		model = new TetrisGrid();

		view = new TetrisGameTextView();

		TetrisGrid.addNewPiece();

	}

	public TetrisGrid getTetrisGrid() {
		return model;
	}

	/**
	 * Sequence of moves
	 * 
	 * @param userInput
	 *            String.
	 */
	public static void sequenceMove(String userInput) {

		if (userInput.equals("quit")) {
			System.out.println(ending[0]);
			System.out.println(ending[1]);
			System.out.println(ending[2] + TetrisGrid.getGameScore());

		} else {
			TetrisGameTextView.printGrid();

			if (TetrisGrid.validMove(0) == true) {
				readInput(userInput);
				attemptMove(moveType);
				refreshDisplay();
			}
		}

	}

	/**
	 * Method to execute move
	 * 
	 * @param moveType
	 *            int
	 */
	public static void attemptMove(int moveType) {

		TetrisGrid.tryMove(moveType);

	}

	/**
	 * when a piece cannot move down anymore
	 */
	public void endRound() {
		model.endOfRound();
	}

	/**
	 * Prints the grid from view
	 */
	public static void refreshDisplay() {

		TetrisGameTextView.printGrid();

	}

	/**
	 * method to read user input
	 * 
	 * @param userInput
	 *            String
	 */
	public static void readInput(String userInput) {

		if (userInput.equals("down")) {
			moveType = DOWN;
		} else if (userInput.equals("right")) {
			moveType = RIGHT;
		} else if (userInput.equals("left")) {
			moveType = LEFT;
		} else if (userInput.equals("cw")) {
			moveType = CW;
		} else if (userInput.equals("CW")) {
			moveType = CW;
		} else if (userInput.equals("ccw")) {
			moveType = CCW;
		} else if (userInput.equals("CCW")) {
			moveType = CCW;
		} else if (userInput.equals("quit")) {
			// System.out.println("END OF GAME");
		} else {
			System.out.println("try again");
		}

	}

	/**
	 * Personal method to test what happens. Free to modify.
	 */
	public static void testerThing() {
		System.out.println("START");

		TetrisGrid.addNewPiece();
		refreshDisplay();

		System.out.println("try to move");

		attemptMove(0);

		System.out.println("After move");
		refreshDisplay();
		System.out.println(TetrisGrid.getCurrentLocation()[0]);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            an array of Strings.
	 */
	public static void main(String[] args) {
		System.out.println(welcome);
		System.out.println(instructions1);
		System.out.println(instructions2);
		System.out.println("Lines cleared: " + TetrisGrid.getGameScore());
		System.out.println("Number of tetrises" + TetrisGrid.getNumTetrises());

		TetrisGrid.addNewPiece();

		for (int l = 0; l < args.length; l++) {
			try {
				sequenceMove(args[l]);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		// TetrisGrid.printBoard();

	}
}

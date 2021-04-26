import java.util.Arrays;

/**
 * TetrisGrid.java
 * 
 * This is the model class. TetrisGrid sets up the game play area. Sets the
 * rules for movement and creates new Tetris pieces.
 * 
 * @author adelyn.yeoh CS201 - Tetris
 */
public class TetrisGrid {

	// this is the model class

	private static int gameScore;
	private static int numTetrises;

	// make tetris grid
	private static final int NUM_ROWS = 18;
	private static final int NUM_COL = 7;
	private static boolean[][] tetrisGrid = new boolean[NUM_ROWS][NUM_COL];

	// make a new piece
	private static TetrisPieces newSetPiece;
	private static TetrisPieces nextSetPiece;
	private static int[] currentLocation = new int[2];
	private static int currentRot;

	// keep track of pieces added
	private static boolean addedPiece = true;
	
	// need to declare array size
	private static int[] checkFilledRows = new int[18];
	private static int numberOfFilledRows;

	/**
	 * Constructor
	 */
	public TetrisGrid() {
		nextSetPiece = randomNewPiece();

	}

	// ***************** MAKING TETRIS GRID *****************//
	/**
	 * Method to make the board and set all values to false
	 */
	public static void setupBoard() {
		// initially set all boolean values to false
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int k = 0; k < NUM_COL; k++) {
				tetrisGrid[i][k] = false;
			}
		}
		numTetrises = 1;
	}

	public static boolean[][] getTetrisGrid() {
		return tetrisGrid;
	}

	public static int getNumRows() {
		return NUM_ROWS;
	}

	public static int getNumCol() {
		return NUM_COL;
	}

	// ***************** GAME SCORE *****************//
	public static int getGameScore() {
		return gameScore;
	}

	public static void setGameScore(int score){
		gameScore = score;
	}
	public static int updateGameScore(int rowsCleared) {
		return gameScore = gameScore + rowsCleared;
	}

	// ***************** NUM OF PIECES *****************//
	public static int getNumTetrises() {
		return numTetrises;
	}

	public static void setNumTetrises(int numTetrises) {
		TetrisGrid.numTetrises = numTetrises;
	}

	// ***************** MAKE NEW PIECE *****************//
	/**
	 * Method to randomize new piece and set current rotation to 0
	 * 
	 * @return someNewSetPiece of type TetrisPiece
	 */
	private static TetrisPieces randomNewPiece() {
		// random number generator
		int generateNumber = (int) Math.floor(Math.random() * 7);

		switch (generateNumber) {
		case 0:
			nextSetPiece = new TetrisSquare();
			break;
		case 1:
			nextSetPiece = new TetrisL1();
			break;
		case 2:
			nextSetPiece = new TetrisL2();
			break;
		case 3:
			nextSetPiece = new TetrisStick();
			break;
		case 4:
			nextSetPiece = new TetrisTee();
			break;
		case 5:
			nextSetPiece = new TetrisSss();
			break;
		case 6:
			nextSetPiece = new TetrisZzz();
			break;
		default:
			nextSetPiece = null;
			break;
		}

		System.out.println("nextSetPiece is: " + generateNumber);

		// set the current rotation to 0
		nextSetPiece.setCurrentRot(0);

		return nextSetPiece;
	}

	/**
	 * Method to add new piece to the board
	 */
	public static void addNewPiece() {

		newSetPiece = nextSetPiece;
		newSetPiece.setCurrentRot(0);

		currentRot = newSetPiece.getCurrentRot();
		randomNewPiece();

		// set location
		currentLocation[0] = 0;
		currentLocation[1] = 3;

		// make sure move is valid
		if (validMove(0) == true) {
			System.out.println("PIECE IS ADDED");
			addedPiece = true;
		} else {
			System.out.println("PIECE IS NOT ADDED");
			addedPiece = false;
		}
		numTetrises = numTetrises + 1;

	
	}

	/**
	 * An array of integers that handles the update on all types of moves (left,
	 * right, down, CW, CCW)
	 * 
	 * @param moveType
	 *            an integer
	 * @param currentRot
	 *            an integer
	 * @return an array of integers that hold location coords and currentRot
	 *         value
	 */
	private static int[] moveTypeCoord(int moveType, int currentRot) {

		int[] coordAndRot = new int[3];

		switch (moveType) {
		// move_Down
		case 0:
			coordAndRot[0] = 1;
			coordAndRot[1] = 0;
			coordAndRot[2] = currentRot;
			break;
		// move_Right
		case 1:
			coordAndRot[0] = 0;
			coordAndRot[1] = 1;
			coordAndRot[2] = currentRot;
			break;
		// move_Left
		case 2:
			coordAndRot[0] = 0;
			coordAndRot[1] = -1;
			coordAndRot[2] = currentRot;
			break;
		// rotCW()
		case 3:
			coordAndRot[0] = 0;
			coordAndRot[1] = 0;
			coordAndRot[2] = (((currentRot + 1) % 4) + 4) % 4;
			break;
		// rotCCW()
		case 4:
			coordAndRot[0] = 0;
			coordAndRot[1] = 0;
			coordAndRot[2] = (((currentRot - 1) % 4) + 4) % 4;
			break;
		}
		return coordAndRot;
	}

	/**
	 * Method to check if newSetPiece is able to move into new target location,
	 * depending on moveType.
	 * 
	 * @param moveType
	 *            an integer associated to a move 0 - move down, 1 - move right,
	 *            2 - move left
	 * @return boolean that returns True if piece is able to move
	 */
	public static boolean validMove(int moveType) {

		boolean isValid;

		boolean staysOnBoard = staysOnBoard(moveType, currentRot, newSetPiece);
		

		boolean noCollide = noCollide(moveType, currentRot, newSetPiece);
		// System.out.println("nocollide? " + noCollide);

		if (staysOnBoard == true && noCollide == true) {

			isValid = true;
		} else {
			isValid = false;
		}

		return isValid;
	}

	// ***************** OBSERVATIONS *****************//

	/**
	 * Method to check zero row
	 * 
	 * @return isFilled boolean
	 */
	public boolean isLost() {
		boolean isFilled = false;

		if (tetrisGrid[10][3] == true) {
			// System.out.println(" caught isfilled");
			isFilled = true;
		}

		System.out.println("isfilled: " +isFilled);
		return isFilled;
	}

	/**
	 * Method to check number of filled rows and return an int array that
	 * carries the index which has the filled rows
	 * 
	 * @return checkFilledRows an array of integers
	 */
	private static int[] checkFilledRows() {
		numberOfFilledRows = 0;
		boolean isFilled = false;

		for (int i = 0; i < NUM_ROWS; i++) {
			for (int k = 0; k < NUM_COL; k++) {
				if (tetrisGrid[i][k] == true) {
					isFilled = true;
				} else {
					isFilled = false;
					break;
				}
			}
			if (isFilled == true) {
				numberOfFilledRows = numberOfFilledRows + 1;
				checkFilledRows[numberOfFilledRows - 1] = i;
			}
		}
		return checkFilledRows;
	}

	/**
	 * Method to see if piece stays on board
	 * 
	 * @param moveType
	 *            an integer
	 * @param currentRot
	 *            an integer
	 * @param newSetPiece
	 *            Tetrispieces
	 * @return boolean value. Returns true if piece stays on board.
	 */
	private static boolean staysOnBoard(int moveType, int currentRot,
			TetrisPieces newSetPiece) {

		// Get target location
		int[] coordinates = moveTypeCoord(moveType, currentRot);
		int targetRowNum = currentLocation[0] + coordinates[0];
		int targetColNum = currentLocation[1] + coordinates[1];
		int oldRot = currentRot;
		int targetRot = coordinates[2];

		
		boolean[][] testGrid = newSetPiece.setPiece[targetRot];
		
		// Run through conditions.
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (testGrid[row][col] == true) {
					if ((targetRowNum + row) > (NUM_ROWS - 1)) {
						
						return false;
					}

					if ((targetColNum + col) > (NUM_COL - 1)) {
					
						return false;
					}
				}
			}
			// System.out.println( " ");
		}
	
		return true;
	}

	/**
	 * Method to see if collision will happen if moveType is made. Used also to
	 * see if piece can hit the floor of board.
	 * 
	 * @param moveType
	 *            an integer
	 * @param currentRot
	 *            an integer
	 * @param newSetPiece
	 *            Tetrispieces
	 * @return boolean value. Returns true if no collision/piece is able to move
	 *         on grid.
	 * 
	 */
	private static boolean noCollide(int moveType, int currentRot,
			TetrisPieces newSetPiece) {

		boolean isPieceFilled;
		boolean isGridFilled;

		// STEP 1: FIND TARGET LOCATION, DEPENDING ON MOVETYPE
		int[] updateCoordAndRot = moveTypeCoord(moveType, currentRot);
		int targetRowNum = currentLocation[0] + updateCoordAndRot[0];
		int targetColNum = currentLocation[1] + updateCoordAndRot[1];

		// check conditions
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {

				isPieceFilled = newSetPiece.pieceGrid[row][col];
				try {
					isGridFilled = tetrisGrid[targetRowNum + row][targetColNum
							+ col];
				} catch (IndexOutOfBoundsException e) {
					isGridFilled = true;
				}

				if (isPieceFilled && isGridFilled) {

					return false;

				}

			}
		}

		return true;
	}

	// ***************** UPDATES *****************//
	/**
	 * Method to clear row rowNumber and update to false
	 * 
	 * @param rowNumber
	 *            an integer [row index]
	 */
	private static void clearLine(int rowNumber) {
		for (int i = 0; i < NUM_COL; i++) {
			tetrisGrid[rowNumber][i] = false;
		}
	}

	/**
	 * Method to shift rows downwards
	 * 
	 * @param rowNumber
	 *            an integer [row index]
	 */
	private static void shiftDown(int rowNumber) {

		for (int k = rowNumber; k > 0; k--) {
			for (int i = 0; i < NUM_COL; i++) {
				tetrisGrid[k][i] = tetrisGrid[k - 1][i];
			}
		}
	}

	/**
	 * Method to execute deletion of filled rows
	 */
	private static void executeDeletion() {
		// check which rows are filled
		checkFilledRows();

		// run the deletion
		for (int i = numberOfFilledRows - 1; i >= 0; i--) {

			// i know this looks weird but this is a correction
			// to a calculation error i made
			// let's just call this a corrector value
			int correctorVal = (numberOfFilledRows - 1) - i;

			clearLine(checkFilledRows[i] + correctorVal);
			shiftDown(checkFilledRows[i] + correctorVal);
			clearLine(0);
		}

		// update score
		updateGameScore(numberOfFilledRows);

	}

	// ***************** END OF ROUND *****************//

	/**
	 * What happens at the end of the round? Print piece into the board, then
	 * executeDeletion, then add new piece, if possible.
	 */
	public static void endOfRound() {

		printInBoard();

		executeDeletion();

		// printBoard();

		addNewPiece();
	}

	// ***************** MOVEMENT OF PIECES *****************//

	/**
	 * Sort of a game loop. Used predominantly by controller while waiting for
	 * user input
	 * 
	 * @param moveType
	 *            an integer
	 */
	public static void tryMove(int moveType) {

		// if move is valid, execute move. else, end of round.
		if (validMove(moveType) == true) {
			executeMove(moveType);
			testEndRound();
		}

	}

	public static void testEndRound() {
		if (validMove(0) == false) {
			// TetrisGameTextView.printGrid();
			System.out.println("isvalidmoveDOWN? " + validMove(0));
			endOfRound();
			// System.out.println("MOVE NOT VALID");
		}

	}

	/**
	 * Method to update currentLocation and rotation value
	 * 
	 * @param moveType
	 *            an integer
	 */
	private static void executeMove(int moveType) {
		// gets information from method moveTypeCoord
		int[] coordinates = moveTypeCoord(moveType, currentRot);

		// sets values
		currentLocation[0] = currentLocation[0] + coordinates[0];
		currentLocation[1] = currentLocation[1] + coordinates[1];
		currentRot = coordinates[2];

		newSetPiece.setup();

	}

	/**
	 * Method to imprint piece into tetrisGrid
	 */
	private static void printInBoard() {
		// find target location
		int targetRow = currentLocation[0];
		int targetCol = currentLocation[1];

		// System.out.println("target coord (y, x) " + targetRow + " " +
		// targetCol);

		// printing info into board
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (newSetPiece.pieceGrid[row][col] == true) {
					tetrisGrid[targetRow + row][targetCol + col] = newSetPiece.pieceGrid[row][col];
				}
			}
		}
	}
	
	

	public static int getNumberOfFilledRows() {
		return numberOfFilledRows;
	}

	public static void setNumberOfFilledRows(int numberOfFilledRows) {
		TetrisGrid.numberOfFilledRows = numberOfFilledRows;
	}

	public static int[] getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int[] currentLocation) {
		this.currentLocation = currentLocation;
	}

	public static TetrisPieces getNewSetPiece() {
		return newSetPiece;
	}

	public static void setNewSetPiece(TetrisPieces newSetPiece) {
		TetrisGrid.newSetPiece = newSetPiece;
	}

	public static TetrisPieces getNextSetPiece() {
		return nextSetPiece;
	}

	public static void setNextSetPiece(TetrisPieces nextSetPiece) {
		TetrisGrid.nextSetPiece = nextSetPiece;
	}

	public static int getCurrentRot() {
		return currentRot;
	}

	public static void setCurrentRot(int currentRot) {
		TetrisGrid.currentRot = currentRot;
	}
	
	

	public static boolean isAddedPiece() {
		return addedPiece;
	}

	public static void setAddedPiece(boolean addedPiece) {
		TetrisGrid.addedPiece = addedPiece;
	}

	/**
	 * Personal method to just continually check what the board state is like.
	 * Prints true and false values. Not used in actual game.
	 */
	public static void printBoard() {
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COL; col++) {
				System.out.print(tetrisGrid[row][col] + " ");
			}
			System.out.println("");
		}
	}
}

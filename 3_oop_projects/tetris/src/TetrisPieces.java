/**
 * TetrisPieces.java
 * 
 * @author adelyn.yeoh CS 201 - Tetris
 */
public abstract class TetrisPieces {

	// THIS IS AN ABSTRACT CLASS

	// the type of piece that we are dealing with
	protected boolean[][][] setPiece;

	// current rotation. initial value is zero
	protected int currentRot;

	// pieceGrid is the 4x4 boolean array that
	// describes the shape of the tetris piece
	// that will be used in the View/Grid
	protected boolean[][] pieceGrid;

	/**
	 * Constructor
	 * 
	 * @param setPiece
	 *            boolean[][][]
	 */
	public TetrisPieces(boolean[][][] setPiece) {
		// not sure if method is needed
		// setup();
		this.setPiece = setPiece;
	}

	public abstract void setup();

	/**
	 * Check value in boolean grid. Return the coordinates of farthest filled
	 * {row, col}
	 * 
	 * @param whatRot
	 *            int
	 * @return indexOfFilled an array of integers of length 2
	 */
	public int[] isFilled(int whatRot) {

		// i think will need to edit pieceGrid to reference the
		// intended 4x4 grid i want to use.
		pieceGrid = setPiece[whatRot];

		int[] indexOfFilled = { -5, -5 };
		for (int x = 3; x >= 0; x--) {
			for (int y = 3; y >= 0; y--) {
				if (pieceGrid[y][x] == true) {
					indexOfFilled[1] = x;
					break;
				}
			}
			if (indexOfFilled[1] >= 0) {
				break;
			}
		}

		for (int i = 3; i >= 0; i--) {
			for (int j = 3; j >= 0; j--) {
				if (pieceGrid[i][j] == true) {
					indexOfFilled[0] = i;
					break;
				}
			}
			if (indexOfFilled[0] >= 0) {
				break;
			}
		}
		return indexOfFilled; // check if need to add or subtract stuff!!!
	}

	/****************** METHODS CONTROLING ROTATION OF PIECES ***********************/

	/**
	 * returns current rotation
	 * 
	 * @return currentRot an integer
	 */
	public int getCurrentRot() {
		return currentRot;
	}

	/**
	 * Sets current rotation
	 * 
	 * @param currentRot
	 *            an integer
	 */
	public void setCurrentRot(int currentRot) {
		this.currentRot = currentRot;
	}

	/**
	 * get setpiece
	 * 
	 * @return setPiece of type TetrisPieces
	 */
	public boolean[][][] getSetPiece() {
		return setPiece;
	}

	/**
	 * Set setpiece
	 * 
	 * @param setPiece
	 *            tetrispieces
	 */
	public void setSetPiece(boolean[][][] setPiece) {
		this.setPiece = setPiece;
	}

	/**
	 * get piece grid
	 * 
	 * @return pieceGrid boolean[][]
	 */
	public boolean[][] getPieceGrid() {
		return pieceGrid;
	}

	/**
	 * set the piece grid
	 * 
	 * @param pieceGrid
	 *            boolean[][]
	 */
	public void setPieceGrid(boolean[][] pieceGrid) {
		this.pieceGrid = pieceGrid;
	}

}

import java.util.Arrays;

/**
 * TetrisGameTextView.java
 * 
 * Displays the TetrisGrid
 * 
 * @author adelyn.yeoh CS201 - TETRIS
 */

public class TetrisGameTextView {

	private static int numRows;
	private static int numCols;
	private static boolean[][] grid;

	/**
	 * Constructor - does nothing
	 */
	public TetrisGameTextView() {

	}

	/**
	 * get num rows
	 */
	private static void getNumRows() {
		numRows = TetrisGrid.getNumRows();
	}

	/** 
	 * get num cols
	 */
	private static void getNumCols() {
		numCols = TetrisGrid.getNumCol();
	}

	/**
	 * Method to print grid in the command line.
	 * Creates an "overlay board" by first looking at current TetrisGrid, and overlays the "piece".
	 * Prints X when true value is read in the overlay board.
	 */
	public static void printGrid() {

		// creating of the new board 
		boolean[][] derivedGrid = new boolean[TetrisGrid.getNumRows()][TetrisGrid
				.getNumCol()];

		for (int row = 0; row < TetrisGrid.getNumRows(); row++) {
			for (int col = 0; col < TetrisGrid.getNumCol(); col++) {
				derivedGrid[row][col] = TetrisGrid.getTetrisGrid()[row][col];
			}
		}

		// Arrays.copyOf(TetrisGrid.getTetrisGrid(), TetrisGrid.getNumRows());

		// finding the location of the tetris piece
		int targetRow = TetrisGrid.getCurrentLocation()[0];
		int targetCol = TetrisGrid.getCurrentLocation()[1];

		// making the overlay between the board and the piece
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				try {
					derivedGrid[targetRow + row][targetCol + col] = (TetrisGrid
							.getTetrisGrid()[targetRow + row][targetCol + col] || TetrisGrid
							.getNewSetPiece().pieceGrid[row][col]);
				} catch (IndexOutOfBoundsException e) {

				}
			}
		}
		
		// Actual printing out of board
		System.out.println("---------------");
		System.out.println(" TETRIS BOARD ");
		System.out.println(" Lines cleared: " + TetrisGrid.getGameScore());
		System.out.println("---------------");
		for (int row = 0; row < TetrisGrid.getNumRows(); row++) {
			for (int col = 0; col < TetrisGrid.getNumCol(); col++) {
				if (derivedGrid[row][col] == true) {
					System.out.print(" " + "X");
				} else {
					System.out.print(" " + " ");
				}
			}
			System.out.println();
		}
		System.out.println("---------------");

	}

}

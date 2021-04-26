/**
 * TetrisGameGUIView.java
 * @author adelyn.yeoh CS201 - TETRIS
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class TetrisGameGUIView extends JComponent {

	private TetrisGrid grid;
	private static Color TET_PIECE_COLOR_1 = Color.PINK;
	private static Color TET_BG = Color.gray;
	private static boolean[][] derivedGrid;
	private static int blockSize;

	private static int[] gridCoordinates = { 30, 5 };

	/**
	 * Sets the grid to this grid
	 * 
	 * @param grid
	 *            a TetrisGrid
	 */
	public TetrisGameGUIView(TetrisGrid grid) {

		super();
		this.grid = grid;
	}

	/**
	 * Prints the board outline
	 * 
	 * @param g
	 *            Graphics
	 * @param blockSize
	 *            an integer
	 */
	private static void paintBoardOutline(Graphics g, int blockSize) {

		// get the dimensions from NumCol and NumRows from TetrisGrid
		int numCol = TetrisGrid.getNumCol();
		int numRows = TetrisGrid.getNumRows();

		// draws a black rectangle
		g.setColor(Color.black);
		g.drawRect(gridCoordinates[1], gridCoordinates[0], blockSize * numCol,
				blockSize * numRows);
		// g.setColor(TET_BG);
		// g.fillRect(gridCoordinates[1]+1, gridCoordinates[0]+1,
		// blockSize*numCol-1, blockSize*numRows-1);

	}

	/**
	 * Method to override paint method
	 */
	public void paint(Graphics g) {
		// get the block size
		blockSize = computeBlockSize();

		// prints board and game area
		paintBoardOutline(g, blockSize);
		paintCanvas(g);

	}

	/**
	 * Method to paint individual blocks
	 * 
	 * @param g
	 *            Graphics
	 * @param row
	 *            int
	 * @param col
	 *            int
	 * @param blockSize
	 *            int
	 * @param someColor
	 *            Color
	 */

	private static void paintBlock(Graphics g, int row, int col, int blockSize,
			Color someColor) {
		// coordinates are now (row*BS + gridCoordinates[0], col*BS +
		// gridCoordinates[1])
		g.setColor(someColor);
		g.fillRect((col * blockSize) + gridCoordinates[1], (row * blockSize)
				+ gridCoordinates[0], blockSize, blockSize);
		g.setColor(TET_BG);
		g.drawRect((col * blockSize) + gridCoordinates[1], (row * blockSize)
				+ gridCoordinates[0], blockSize, blockSize);

	}

	/**
	 * method to paint game area
	 * 
	 * @param g
	 *            Graphics
	 */
	public void paintCanvas(Graphics g) {
		mergedGrid();

		for (int row = 0; row < derivedGrid.length; row++) {
			for (int col = 0; col < derivedGrid[row].length; col++) {
				if (derivedGrid[row][col] == true) {
					// paint color PINK
					paintBlock(g, row, col, blockSize, TET_PIECE_COLOR_1);
				} else {
					// paint color BG
					paintBlock(g, row, col, blockSize, TET_BG);

				}
			}
		}
	}

	public TetrisGrid getGrid() {
		return grid;
	}

	public void setGrid(TetrisGrid grid) {
		this.grid = grid;
	}

	/**
	 * Creates an overlay of the grid and the currentpiece
	 * 
	 * @return derived grid boolean[][]
	 */
	private static boolean[][] mergedGrid() {
		// creating of the new board
		derivedGrid = new boolean[TetrisGrid.getNumRows()][TetrisGrid
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
		return derivedGrid;
	}

	/**
	 * Method to compute the block size, given the applet size
	 * 
	 * @return size, an integer
	 */
	public int computeBlockSize() {
		int size = 0;
		int width = (getWidth() - 5) / TetrisGrid.getNumCol();
		int height = (getHeight() - 30) / TetrisGrid.getNumRows();

		if (width < height) {
			size = width;
		} else {
			size = height;
		}

		return size;
	}
}

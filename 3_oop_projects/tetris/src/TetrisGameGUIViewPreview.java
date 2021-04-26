import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class TetrisGameGUIViewPreview extends JComponent {

	private static Color TET_PIECE_COLOR_1 = Color.PINK;
	private static Color TET_BG = Color.gray;
	private static int blockSize;
	private TetrisGrid grid;
	private TetrisPieces nextSetPiece;

	public TetrisGameGUIViewPreview(TetrisGrid grid) {
		super();
		this.grid = grid;

	}

	public void paint(Graphics g) {
		blockSize = computeBlockSize();
		paintPiece(g);
	}

	private static void paintNextPiece(Graphics g, int row, int col,
			Color someColor) {

		g.setColor(someColor);
		g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
		g.setColor(TET_BG);
		g.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);
	}

	private void paintPiece(Graphics g) {

	//	g.setColor(Color.black);
	//	g.drawRect(0, 0, blockSize * 4, blockSize * 4);

		TetrisPieces displayPiece = TetrisGrid.getNextSetPiece();

		boolean[][] workingGrid = displayPiece.getPieceGrid();
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (workingGrid[row][col] == true) {
					paintNextPiece(g, row, col, TET_PIECE_COLOR_1);
				} else {
					// paintNextPiece(g, row, col, TET_BG);
				}
			}
		}

	}

	/**
	 * Method to compute the block size, given the applet size
	 * 
	 * @return size int
	 */
	public int computeBlockSize() {
		int size = 0;
		int width = (getWidth()/4 );
		int height = (getHeight() / 4);

		if (width < height) {
			size = width;
		} else {
			size = height;
		}

		return size;
	}
	
	
}

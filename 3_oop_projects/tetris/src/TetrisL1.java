/**
 * TetrisL1.java
 * 
 * @author adelyn.yeoh CS 201 - Tetris
 */

public class TetrisL1 extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { true, false, false, false }, { true, true, true, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { false, true, true, false }, { false, true, false, false },
					{ false, true, false, false },
					{ false, false, false, false } },
			{ { false, false, false, false }, { true, true, true, false },
					{ false, false, true, false },
					{ false, false, false, false } },
			{ { false, true, false, false }, { false, true, false, false },
					{ true, true, false, false },
					{ false, false, false, false } } };

	// constructor
	public TetrisL1() {
		super(setPiece);
		setup();
	}

	public void setup() {
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}

}

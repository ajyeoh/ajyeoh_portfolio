/**
 * TetrisL2.java
 * 
 * @author adelyn.yeoh CS 201 - TETRIS
 */
public class TetrisL2 extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { false, false, true, false }, { true, true, true, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { false, true, false, false }, { false, true, false, false },
					{ false, true, true, false },
					{ false, false, false, false } },
			{ { false, false, false, false }, { true, true, true, false },
					{ true, false, false, false },
					{ false, false, false, false } },
			{ { true, true, false, false }, { false, true, false, false },
					{ false, true, false, false },
					{ false, false, false, false } } };

	// constructor
	public TetrisL2() {
		super(setPiece);
		setup();
	}

	public void setup() {
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}
}

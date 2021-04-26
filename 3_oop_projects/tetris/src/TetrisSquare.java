/**
 * TetrisSquare.java
 * 
 * @author adelyn.yeoh CS 201 - TETRIS
 */
public class TetrisSquare extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { true, true, false, false }, { true, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { true, true, false, false }, { true, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { true, true, false, false }, { true, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { true, true, false, false }, { true, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } } };

	public TetrisSquare() {
		super(setPiece);
		setup();
	}

	public void setup() {
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}

}

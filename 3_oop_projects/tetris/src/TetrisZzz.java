/**
 * TetrisZzz.java
 * 
 * @author adelyn.yeoh CS201 - TETRIS
 */
public class TetrisZzz extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { false, true, false, false }, { true, true, false, false },
					{ true, false, false, false },
					{ false, false, false, false } },
			{ { true, true, false, false }, { false, true, true, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { false, true, false, false }, { true, true, false, false },
					{ true, false, false, false },
					{ false, false, false, false } },
			{ { true, true, false, false }, { false, true, true, false },
					{ false, false, false, false },
					{ false, false, false, false } } };

	public TetrisZzz() {
		super(setPiece);
		setup();
	}

	public void setup() {
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}

}

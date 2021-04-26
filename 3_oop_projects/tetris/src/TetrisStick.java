/**
 * TetrisStick.java
 * 
 * @author adelyn.yeoh CS201 - TETRIS
 */
public class TetrisStick extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { false, true, false, false }, { false, true, false, false },
					{ false, true, false, false },
					{ false, true, false, false } },
			{ { false, false, false, false }, { true, true, true, true },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { false, true, false, false }, { false, true, false, false },
					{ false, true, false, false },
					{ false, true, false, false } },
			{ { false, false, false, false }, { true, true, true, true },
					{ false, false, false, false },
					{ false, false, false, false } } };

	public TetrisStick() {
		super(setPiece);
		setup();
	}

	public void setup() {
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}
}

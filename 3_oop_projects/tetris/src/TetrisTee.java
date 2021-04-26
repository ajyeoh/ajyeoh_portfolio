/**
 * TetrisTee.java
 * 
 * @author adelyn.yeoh CS201 - TETRIS
 */
public class TetrisTee extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { true, true, true, false }, { false, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { false, false, true, false }, { false, true, true, false },
					{ false, false, true, false },
					{ false, false, false, false } },
			{ { false, false, false, false }, { false, true, false, false },
					{ true, true, true, false }, { false, false, false, false } },
			{ { true, false, false, false }, { true, true, false, false },
					{ true, false, false, false },
					{ false, false, false, false } } };

	public TetrisTee() {
		super(setPiece);
		setup();
	}

	public void setup() {
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}
}

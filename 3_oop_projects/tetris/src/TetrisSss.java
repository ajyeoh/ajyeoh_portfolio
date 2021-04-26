/**
 * TetrisSss.java
 * 
 * @author adelyn.yeoh CS201 - TETRIS
 */
public class TetrisSss extends TetrisPieces {

	protected static boolean[][][] setPiece = {
			{ { true, false, false, false }, { true, true, false, false },
					{ false, true, false, false },
					{ false, false, false, false } },
			{ { false, true, true, false }, { true, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } },
			{ { true, false, false, false }, { true, true, false, false },
					{ false, true, false, false },
					{ false, false, false, false } },
			{ { false, true, true, false }, { true, true, false, false },
					{ false, false, false, false },
					{ false, false, false, false } } };

	public TetrisSss() {
		super(setPiece);
		
		setup();
	}

	public void setup(){
		pieceGrid = setPiece[TetrisGrid.getCurrentRot()];
	}
}

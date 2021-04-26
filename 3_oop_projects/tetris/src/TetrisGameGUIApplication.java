import javax.swing.JFrame;

/**
 * Command line version of the GUI Tetris game.
 * @author ponbarry
 */
public class TetrisGameGUIApplication
{

	/**
	 * Start the game!
	 * @param args String[]
	 */
	public static void main( String[] args )
	{
		
		// create a new JFrame to hold a new controller instance
		JFrame tetrisFrame = new JFrame("Tetris");
		
		// set size
		tetrisFrame.setSize( 600, 850 );

		// make a new controller instance and add it
		tetrisFrame.add( new TetrisGameGUIController( ) );

		// exit normally on closing the window
		tetrisFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		tetrisFrame.setVisible( true );		
	}
}

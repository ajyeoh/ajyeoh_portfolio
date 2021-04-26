import javax.swing.JFrame;

/**
 * LotrQuestionsApplication.java
 * 
 * @author adelyn.yeoh
 *
 *         CS201 - FinalProject
 */
public class LotrQuestionsApplication {
	/**
	 * Start the game!
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		// create a new JFrame to hold a new controller instance
		JFrame gameFrame = new JFrame("Alatúlië " // means 'welcome' in quenya
				+ "to Lothlórien!");

		// set size
		gameFrame.setSize(900, 750);

		// make a new controller instance and add it
		gameFrame.add(new LotrQuestionsPanel());

		// exit normally on closing the window
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		gameFrame.setVisible(true);
	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * LotrQuestionsPanel.java
 * 
 * Controller. Holds introduction panel, game panel, music panel and makes
 * advertisements.
 * 
 * @author adelyn.yeoh
 *
 *         CS201 - FINAL PROJECT
 */
public class LotrQuestionsPanel extends JPanel {

	// Intro and Game panels
	private LotrGaladrienIntro LotrGaladrienIntroPanel;
	private LotrGamePanel lotrGamePanel;

	// holds image
	private ImageComponent galadPic;

	// Box to hold UI;
	private Box gamePlayBox;

	// Fillers
	private Box.Filler firstSideFiller = new Box.Filler(new Dimension(15, 50),
			new Dimension(20, 50), new Dimension(25, 50));
	private Box.Filler secondSideFiller = new Box.Filler(new Dimension(15, 50),
			new Dimension(20, 50), new Dimension(25, 50));

	// Fake advertisement string
	private String adString = "          Like Greens? Head over to The Shire for "
			+ "some freshly roasted greens. Swing by the farm as we meet the hardworking Took family.      ";

	private String secondAd = "WANT THE ONE RING??? CALL 123456789";

	// Fake advertisement material
	private JPanel fakeAdvertPanel;
	private JTextArea fakeAdText;

	// Timers for animating fake advertisements
	private Timer secondAdTimer;
	private Timer adTimer;

	// Pointers to help animate advertisment
	private int start = 0;
	private int length = 60;

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sets up Music Panel and Game Area
	 */
	public LotrQuestionsPanel() {
		super(new BorderLayout());

		// Add music panel
		add(new LotrMusicPanel(), BorderLayout.NORTH);

		// Add main gamePlay area to the center
		add(addGamePlayPanel(), BorderLayout.CENTER);

		// Add advertisements to the bottom of the panel
		add(genericAnnoyingAdvertisementPanel(), BorderLayout.SOUTH);

		// create new lotrGamePanel
		lotrGamePanel = new LotrGamePanel();

		// Setup advertisements
		setupAnnoyingAdvertTimer();
	}

	/**
	 * Setup main game area - image held here, and instructions, buttons added
	 * here
	 * 
	 * @return gamePlayBox Box
	 */
	private Box addGamePlayPanel() {

		gamePlayBox = new Box(BoxLayout.X_AXIS); // New Box

		galadPic = new ImageComponent("mirrorgalad.png"); // Create image

		// Create new introPanel
		LotrGaladrienIntroPanel = new LotrGaladrienIntro(this);

		gamePlayBox.add(galadPic); // add picture to box
		gamePlayBox.add(firstSideFiller); // add filler
		gamePlayBox.add(LotrGaladrienIntroPanel); // add introPanel
		gamePlayBox.add(secondSideFiller); // add filler

		return gamePlayBox;
	}

	/**
	 * Setup adPanel
	 * 
	 * @return fakeAdvertPanel JPanel
	 */
	private JPanel genericAnnoyingAdvertisementPanel() {

		fakeAdvertPanel = new JPanel(); // new panel

		// Create and format new text area
		fakeAdText = new JTextArea();
		fakeAdText.setEditable(false);
		fakeAdText.setEditable(false);
		fakeAdText.setWrapStyleWord(true);
		fakeAdText.setBackground(new Color(240, 240, 240));

		fakeAdvertPanel.add(fakeAdText); // add textArea to panel

		return fakeAdvertPanel;
	}

	/**
	 * Method to make fake advertisement text. Used for animation.
	 * 
	 * @param someString
	 *            String
	 */
	private void makeAdText(String someString) {

		String stars = "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * "
				+ "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ";

		// set text to fakeAdText area
		fakeAdText.setText("\n" + stars + "\n" + "\t ADVERTISEMENT:  "
				+ someString + "\n" + stars);
	}

	/**
	 * Helper method to animate text. Used to break string.
	 * 
	 * @param someString
	 *            String
	 * @param loIndex
	 *            int
	 * @param hiIndex
	 *            int
	 * @return cutString String
	 */
	private String cutString(String someString, int loIndex, int hiIndex) {

		// Take substring of a string
		String cutString = someString.substring(loIndex, hiIndex);

		return cutString;
	}

	/**
	 * Setup first timer. This ad scrolls.
	 */
	private void setupAnnoyingAdvertTimer() {
		// setup other advertisement animation
		secondAdOption();

		// Create timer for first ad
		adTimer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Measures string length to cut. If hiIndex is larger than
				// adString length, fix the hiIndex
				if (start + length >= adString.length() - 2) {

					// Make text
					makeAdText(cutString(adString, start, adString.length() - 1));
					start = start + 1; // update start value

					// Check when to stop this advertisement.
					if (start == adString.length() - 1) {

						start = 0; // reset start index to 0
						adTimer.stop(); // stop this timer
						secondAdTimer.start(); // start second ad
					}

				} else if (start <= adString.length() - 1) {
					// Make ad
					makeAdText(cutString(adString, start, start + length));
					start = start + 1; // update start value
				}

			}
		});

		adTimer.start(); // start timer after setup
	}

	/**
	 * Setup second timer. This ad blinks.
	 */
	private void secondAdOption() {
		// Make second adTimer
		secondAdTimer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Every increment of 5, remove text.
				if (start % 5 == 0) {
					makeAdText("");

				} // Show text otherwise
				else {
					makeAdText(secondAd);
				}
				start++; // increment start value

				// After 70 increments, switch back to the first ad
				if (start == 70) {
					start = 0;
					secondAdTimer.stop();
					adTimer.start();

				}
			}
		});

	}

	/**
	 * Method to start game
	 */
	public void startGame() {

		// Remove Galadrien's introduction
		gamePlayBox.remove(LotrGaladrienIntroPanel);
		validate();
		gamePlayBox.remove(secondSideFiller);
		validate();

		// Add game panel
		gamePlayBox.add(lotrGamePanel);
		validate();
		gamePlayBox.add(secondSideFiller);
		validate();
	}

	/**
	 * Get gamePlayBox
	 * 
	 * @return gamePlayBox Box
	 */
	public Box getGamePlayBox() {
		return gamePlayBox;
	}

	/**
	 * Get LotrGamePanel
	 * 
	 * @return lotrGamePanel LotrGamePanel
	 */
	public LotrGamePanel getLotrGamePanel() {
		return lotrGamePanel;
	}

	/**
	 * Get LotrGaladrienIntroPanel
	 * 
	 * @return LotrGaladrienIntroPanel LotrGaladrienIntro
	 */
	public LotrGaladrienIntro getLotrGaladrienIntroPanel() {
		return LotrGaladrienIntroPanel;
	}


}

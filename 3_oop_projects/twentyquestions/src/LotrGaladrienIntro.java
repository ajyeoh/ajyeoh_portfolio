import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * LotrGaladrienIntro.java
 * 
 * A small introduction to the game. The exchange between user and Galadrien is
 * a reference to a scene in the extended Lord of the Rings movie. Here is the
 * reference: https://www.youtube.com/watch?v=wZautQ0yhm4
 * 
 * @author adelyn.yeoh
 * 
 *         CS201 - FINAL PROJECT
 */
public class LotrGaladrienIntro extends JPanel implements ActionListener {

	// Buttons
	private JButton meetGalad = new JButton(
			"Meet Galadrien, Lady of Lothlórien");
	private JButton respondToGalad = new JButton("What will I see?");
	private JButton startGame = new JButton("Start");

	// Box to hold buttons and text area
	private Box galadGreetingBox = new Box(BoxLayout.PAGE_AXIS);

	// JTextArea to hold greeting
	private JTextArea galadGreeting;

	// String for Galadrien's messages
	private final String galadMsg = " \n \n\t You know who I am. "
			+ "\n\t \t . \n\t \t . \n\t \t ."
			+ " \n\t Will you look into the mirror?";
	private final String galadResponse = " \n \n \n \t Even the wisest cannot tell... "
			+ " \n\t \t . \n\t \t . \n \t For the mirror shows many things… "
			+ " \n\t \t . \n\t \t . \n \t things that were… "
			+ "things that are… and some things… \n\t \t . "
			+ "\n\t \t .  \n \t that have not yet come to pass... ";

	// String to hold animated text (used with timer)
	private String animatedText;

	// Galad's timer
	private Timer galadTimer; // timer to animate text
	private static final int TEXT_ANIMATION_RATE = 25; // appearing speed
	private int textLine; // pointer to help animate text

	private LotrQuestionsPanel lotrqnPanel; // instance of LotrQuestionsPanel
	private JPanel meetGaladPanel = new JPanel(); // holds intro UI

	private Filler fillerBox = new Box.Filler(new Dimension(60, 220),
			new Dimension(70, 260), new Dimension(80, 350)); // filler

	/**
	 * Constructor - makes a JPanel that holds Galadrien's greetings.
	 * 
	 * @param lotrqnPanel
	 *            LotrQuestionsPanel
	 */
	public LotrGaladrienIntro(LotrQuestionsPanel lotrqnPanel) {
		super(new BorderLayout());
		this.lotrqnPanel = lotrqnPanel;

		setupButtons(); // setup buttons
		add(galadGreetingBox, BorderLayout.CENTER); // add textArea to panel

		// Set pointer to 0, and animatedText string is currently empty
		textLine = 0;
		animatedText = "";

		// Create space for aesthetics
		galadGreetingBox.add(new Box.Filler(new Dimension(50, 70),
				new Dimension(50, 90), new Dimension(50, 120)));
		galadGreetingBox.add(toMeetGaladrien()); // add button
		validate();

	}

	/**
	 * Adds 'meet Galadrien' button to JPanel
	 * 
	 * @return meetGaladPanel JPanel
	 */
	private JPanel toMeetGaladrien() {
		meetGaladPanel.add(meetGalad);

		return meetGaladPanel;
	}

	/**
	 * Setup message area
	 */
	private void setupMessageArea() {
		// Create JTextArea
		galadGreeting = new JTextArea();

		// Format JTextArea
		galadGreeting.setText(cutString(galadMsg, textLine));
		galadGreeting.setEditable(false);
		galadGreeting.setWrapStyleWord(true);
		galadGreeting.setLineWrap(true);
		galadGreeting.setBackground(new Color(238, 238, 238));

		// Add JTextArea to main box that holds all the UI
		galadGreetingBox.add(galadGreeting);
		validate();

	}

	/**
	 * Add action listeners to buttons
	 */
	private void setupButtons() {
		respondToGalad.addActionListener(this);
		startGame.addActionListener(this);
		meetGalad.addActionListener(this);
	}

	/**
	 * Helper method to animate text. Take desired message (a string) and add
	 * the next index to the animatedText.
	 * 
	 * @param someString
	 * @param index
	 * @return animatedText String - updated text at time t+1
	 */
	private String cutString(String someString, int index) {

		// Add a new character to animatedText string
		animatedText = animatedText + someString.charAt(index);

		return animatedText;
	}

	/**
	 * First animated text sequence
	 */
	private void setupGaladrienIntro() {

		// Create new timer
		galadTimer = new Timer(TEXT_ANIMATION_RATE, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Check pointer
				if (textLine < galadMsg.length()) {
					// Add next character to the animatedText. Then set it to
					// the target JTextArea
					galadGreeting.setText(cutString(galadMsg, textLine));

					textLine = textLine + 1; // Update pointer
				} // If pointer is too large
				else {
					// Stop timer because we have reached end of the message
					galadTimer.stop();

					// Add button to allow user to respond to Galadriel
					galadGreetingBox.add(respondToGalad);

					// Add filler for aesthetics
					galadGreetingBox.add(fillerBox);
					validate();
				}
			}
		});

		galadTimer.start(); // start timer
	}

	/**
	 * Second animated text sequence
	 */
	private void setupGaladrienSecondIntro() {
		// Update old timer
		galadTimer = new Timer(TEXT_ANIMATION_RATE, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Check pointer
				if (textLine < galadResponse.length()) {
					// Add next character to the animatedText. Then set it to
					// the target JTextArea
					galadGreeting.setText(cutString(galadResponse, textLine));

					textLine = textLine + 1; // Update pointer
				} // If pointer is too large
				else {
					// Stop timer because we have reached end of the message
					galadTimer.stop();

					galadGreetingBox.add(startGame); // Add start game button
					galadGreetingBox.add(fillerBox); // Filler for aesthetics
					validate();
				}
			}
		});

		galadTimer.start(); // start timer
	}

	/**
	 * Method that handles action listener events
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Get source
		JButton buttonPressed = (JButton) e.getSource();

		// Button to let user start intro sequence. Button disappears after
		// clicked
		if (buttonPressed.equals(meetGalad)) {
			galadGreetingBox.remove(meetGaladPanel); // remove intro panel
			validate();

			// Add text area where intro messages are displayed
			setupMessageArea();

			// Start intro sequence
			setupGaladrienIntro();
		}// This button only appears when Galadriel has finished her
			// first introduction. This button also disappears after we have
			// responded
		else if (buttonPressed.equals(respondToGalad)) {

			// remove filler box and button
			galadGreetingBox.remove(fillerBox);
			validate();
			galadGreetingBox.remove(respondToGalad);
			validate();

			// Reset animation pointers back to original settings
			animatedText = "";
			textLine = 0;

			// Start second sequence
			setupGaladrienSecondIntro();
		} // If user wants to start game
		else if (buttonPressed.equals(startGame)) {
			lotrqnPanel.startGame();
		}

	}

}

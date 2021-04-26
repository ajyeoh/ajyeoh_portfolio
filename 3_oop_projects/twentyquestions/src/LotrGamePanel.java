import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import rw.LotrCharListReader;
import rw.LotrCharListWriter;
import rw.LotrTreeReader;

/**
 * LotrGamePanel.java
 * 
 * Where the game is viewed - specify objects that user interacts with.
 * 
 * @author adelyn.yeoh
 *
 *         CS201 - FINAL PROJECT
 */
public class LotrGamePanel extends JPanel implements ActionListener {

	private LotrModel model; // model

	/**** Required buttons and the text for the buttons ***/
	// Buttons for the game setup: user has the option of looking at a
	// restricted list, or go straight to the game itself
	private final String[] initialButtonText = { "Who is in your list?",
			"I've thought of someone already." };
	private JButton[] initialButtons = new JButton[initialButtonText.length];

	// Buttons for responding to Twenty Questions game
	private final String[] responseText = { "Yes", "No", "Enter Text",
			"New Game" };
	private final JButton[] responseButtons = new JButton[responseText.length];

	// Holds initial buttons: [who is in your list?] [thought of char]
	private Box initButtonBox = new Box(BoxLayout.X_AXIS);;

	private Box gameArea; // Main game area

	// Text area where user reads messages from the computer
	private JTextArea galadMsg;

	// Dimensions used to create empty areas by Box.Filler
	private Dimension minEmptyArea = new Dimension(10, 250);
	private Dimension prefEmptyArea = new Dimension(20, 300);
	private Dimension maxEmpArea = new Dimension(50, 350);

	// String that holds charList - used to print known char list. Instantiated
	// only when user wants to read the list
	private String charList;

	// charListPane holds charListTextArea, to allow scrolling for long
	// character lists
	private JScrollPane charListPane;
	private JTextArea charListTextArea;

	// Panel that user interacts in the game
	private JPanel responsePanel = new JPanel();

	private JPanel responseButtonPanel = new JPanel(); // Holds buttons that
														// user uses

	// Introduction text
	private static final String startGameText = " \n \n "
			+ "Welcome to the Lord of the Rings Twenty Questions game. "
			+ "\n \n To start, think of a Lord of the Rings character.";

	/** Variables used for saving info about new character from user **/
	// Text prompt for user
	private static final String[] recordInfoText = {
			"\n \n Please give me a YES or NO question that would have determined your character.",
			"\n\n  Is the answer to your question a YES or a NO?",
			"\n \n Hit 'New Game' to try the game again." };

	private int infoIndex; // Index to get text from string array above

	// Array that stores information from user input
	private String[] newCharInfo = new String[3];

	// Store preGame items in a box for easy adding and removing of buttons
	private Box preGameBox = new Box(BoxLayout.PAGE_AXIS);

	// Store buttons and textAreas that user interacts with in a box for easy
	// adding and removing
	private Box responseBox = new Box(BoxLayout.PAGE_AXIS);

	// Put "Enter Text" button and userInput text area on a panel
	private JPanel userInputAndButtonsRow = new JPanel();

	private JTextArea userInput; // JTextArea where user types response
	private JScrollPane userInputPane; // JScrollPane for userInput JTextArea

	/**
	 * Constructor creates new panel, and sets up buttons, boxes and other GUI
	 * components.
	 */
	public LotrGamePanel() {
		super(new BorderLayout());

		setupInitButtons(); // sets up initial buttons
		setupResponseBox(); // sets up box that user interacts with

		// Adds main game area to panel
		add(initialSetup(), BorderLayout.CENTER);

		// Sets pointer to 0 - helps to get array of instructions for recording
		// new character info
		infoIndex = 0;
	}

	/**
	 * Method to setup responseBox - a component that user interacts with during
	 * the actual game play [when user chooses yes or no]
	 */
	private void setupResponseBox() {

		// Sets up buttons with desired text. Adds action listeners.
		for (int i = 0; i < responseText.length; i++) {
			responseButtons[i] = new JButton(responseText[i]);
			responseButtons[i].addActionListener(this);
		}

		// Add buttons to a panel
		responseButtonPanel.add(responseButtons[3]); // New Game
		responseButtonPanel.add(responseButtons[0]); // Yes
		responseButtonPanel.add(responseButtons[1]); // No

		// Setup userInput text area
		userInput = new JTextArea();
		userInput.setRows(2);
		userInput.setPreferredSize(new Dimension(300, 60));
		userInput.setMaximumSize(new Dimension(300, 100));
		userInput.setEditable(false);
		userInput.setLineWrap(true);
		userInput.setWrapStyleWord(true);

		// Create new JScrollPane with userInput as part of the view
		userInputPane = new JScrollPane(userInput,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add pane and "Enter text" button to another panel
		userInputAndButtonsRow.add(userInputPane);
		userInputAndButtonsRow.add(responseButtons[2]);

		// Main game panel setup
		responsePanel.add(responseButtonPanel);

		// Add all the GUI components to the main Box
		responseBox.add(responsePanel);
		responseBox.add(userInputAndButtonsRow);

		// Add filler for aesthetic finish
		responseBox.add(new Box.Filler(new Dimension(50, 250), new Dimension(
				50, 300), new Dimension(50, 350)));

	}

	/**
	 * Method to setup initial buttons and text areas for use
	 */
	private void setupInitButtons() {

		for (int i = 0; i < initialButtonText.length; i++) {
			// Create new JButton and adds action listener
			initialButtons[i] = new JButton(initialButtonText[i]);
			initialButtons[i].addActionListener(this);

			// Adds button to a button Box
			initButtonBox.add(initialButtons[i]);
		}

	}

	/**
	 * Method to setup pane that displays character list
	 * 
	 * @return charListPane JScrollPane
	 */
	private JScrollPane setupCharTextPane() {
		// Create new JTextArea
		charListTextArea = new JTextArea();
		charListTextArea.setEditable(false);
		charListTextArea.setWrapStyleWord(true);
		charListTextArea.setLineWrap(true);
		charListTextArea.setBackground(new Color(238, 238, 238));

		charListTextArea.setRows(20);

		// Create JScrollPane for charList text area
		charListPane = new JScrollPane(charListTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		return charListPane;
	}

	/**
	 * Method that handles the initial setup. Buttons will be setup so that user
	 * can choose between restricted and unrestricted game.
	 * 
	 * @return gameArea Box
	 */
	private Box initialSetup() {
		// Create new box to hold all the
		gameArea = new Box(BoxLayout.PAGE_AXIS);

		// Create new JTextArea to hold computer/Galadriel's responses
		galadMsg = new JTextArea();
		galadMsg.setEditable(false);
		galadMsg.setWrapStyleWord(true);
		galadMsg.setLineWrap(true);
		galadMsg.setBackground(new Color(238, 238, 238));

		// Message area is always present throughout the game
		gameArea.add(galadMsg);

		// Add preGameBox. PreGameBox allows user to choose between a restricted
		// and an unrestricted game.
		gameArea.add(setupPreGameItems());

		return gameArea;
	}

	/**
	 * Method that sets up preGameBox, which is a Box that contains components
	 * that allows the user to choose between a restricted and an unrestricted
	 * game.
	 * 
	 * @return preGameBox Box
	 */
	private Box setupPreGameItems() {

		preGameBox.add(new Box.Filler(new Dimension(50, 20), new Dimension(50,
				25), new Dimension(50, 40))); // filler for aesthetics

		// Add choices buttons: view character list, or go to game
		preGameBox.add(initButtonBox);

		preGameBox.add(new Box.Filler(new Dimension(5, 10), // for aesthetics
				new Dimension(5, 10), new Dimension(10, 10)));

		preGameBox.add(setupCharTextPane()); // Adds pane that display char list

		preGameBox.add(new Box.Filler(minEmptyArea, prefEmptyArea, maxEmpArea)); // Aesthetics

		setupPreGameTextItems(); // setup text areas

		return preGameBox;
	}

	/**
	 * Method that sets up text areas to the starting game instructions
	 */
	private void setupPreGameTextItems() {
		galadMsg.setText(startGameText); // Set Galadriels' initial instructions
		charListTextArea.setText(""); // Remove previous string
	}

	/**
	 * Method that records info into an array
	 * 
	 * @param index
	 *            int
	 */
	private void recordInfo(int index) {

		// Update Galadriel's instructions
		galadMsg.setText(recordInfoText[index]);

		// Save info from userInput text area into an array
		newCharInfo[index] = userInput.getText();

	}

	/**
	 * Method to update info into gameTree
	 */
	private void updateKnownCharacters() {

		// Read characters we have, and get new character name from userInput
		String curString = LotrCharListReader.parseFile() + newCharInfo[0]
				+ "*"; // add the delimiter to the string (see
						// LotrCharListReader class)

		// Save the string into the file
		LotrCharListWriter
				.writeFile(curString, new File("KnownCharacters.txt"));

		// Save the tree [model handles saving tree]
		model.saveTree(newCharInfo);
	}

	/**
	 * If new game button is pressed. Reset everything.
	 */
	private void resetGame() {
		// Set pointer for instructions back to 0
		infoIndex = 0;

		// Determine the new game tree (in case a new character has been added)
		model.setGameTree(LotrTreeReader.readExpr("CharacterTree.xml"));
		model.setCurNode(model.getGameTree().getRoot());

		// Go back to pre-game setup: want to allow user to select between the
		// two variations of the game. Thus, remove responseBox, and place
		// preGameBox
		gameArea.remove(responseBox);
		validate();
		gameArea.add(preGameBox);
		validate();

		setupPreGameTextItems(); // update text areas to initial instructions
	}

	/**
	 * Method that handles action events
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton buttonPressed = (JButton) e.getSource();

		// If user needs char list
		if (buttonPressed.equals(initialButtons[0])) {

			charList = LotrCharListReader.getCharList(); // Read character list
															// from file

			charListTextArea.setText(charList); // Update the textarea

		} // If user chooses to start answering questions/Start game
		else if (buttonPressed.equals(initialButtons[1])) {
			// Remove preGame box to move into game itself
			gameArea.remove(preGameBox);
			validate();

			// Add buttons and userInput text area to allow user to interact
			// with Galadriel in responding to questions
			gameArea.add(responseBox);
			validate();

			// Create new model. Read new tree.
			model = new LotrModel();

			// Get instructions from the model
			galadMsg.setText(model.getGameString());

		} // If ButtonPressed is YES
		else if (buttonPressed.equals(responseButtons[0])) {

			galadMsg.setText(model.answerYes());

		} // if buttonPressed is NO
		else if (buttonPressed.equals(responseButtons[1])) {

			galadMsg.setText(model.answerNo());

			// If reached the end of the YES/NO sequence, want to start learning
			// new character. Check with gameString.
			if (model.getGameString().equals("Strange.")) {

				galadMsg.setText("\n\n Strange. Who were you thinking of? (Respond below)");
				userInput.setEditable(true);
			}

		} // if user enters text
		else if (buttonPressed.equals(responseButtons[2])) {
			// Only listen to info when text area is editable
			if (userInput.isEditable()) {

				// Start recording info and update infoIndex pointer
				recordInfo(infoIndex);
				infoIndex = (infoIndex + 1) % (recordInfoText.length);

				// After 3 rounds, userInput should not be editable
				if (infoIndex == 3 % (recordInfoText.length)) {
					userInput.setEditable(false);
					userInput.setText(""); // update text area

					updateKnownCharacters(); // start saving info

				}
			}
		} // if new game is pressed
		else if (buttonPressed.equals(responseButtons[3])) {
			resetGame();
		}
	}

}

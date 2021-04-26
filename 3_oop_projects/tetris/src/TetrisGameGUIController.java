/** 
 * TetrisGameGUIController.java
 * @author adelyn.yeoh CS 201 - Tetris
 */

// awt
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
// swing
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class TetrisGameGUIController extends JPanel implements KeyListener {

	// assigning integers to each move
	private static int DOWN = 0;
	private static int RIGHT = 1;
	private static int LEFT = 2;
	private static int CW = 3;
	private static int CCW = 4;

	// sets default drop rate, level rate, and sets starting level
	private static final int DEFAULT_DROP_RATE = 2000;
	private static final int LEVEL_RATE = 10;
	private static int whatLevel = 1;

	// sets gametimer and droprate (an updated drop rate) for the timer
	private int dropRate;
	private Timer gameTimer;

	private static TetrisGrid model;
	private static TetrisGameGUIView view;
	private static TetrisGameGUIViewPreview preview;

	private static JTextArea info;

	private static Color TET_PIECE_COLOR_1 = Color.PINK;
	private static Color TET_BG = Color.gray;

	/**
	 * Controller - creates new border layout and adds model, view Starts off
	 * with adding a new piece, starts timer, and counts level.
	 * 
	 */
	public TetrisGameGUIController() {
		super(new BorderLayout());

		model = new TetrisGrid();

		preview = new TetrisGameGUIViewPreview(model);
		view = new TetrisGameGUIView(model);

		add(gameInfo(), BorderLayout.NORTH);
		add(setupGameArea(), BorderLayout.CENTER);

		// listen for keystrokes
		setFocusable(true);
		addKeyListener(this);

		TetrisGrid.addNewPiece();

		refreshDisplay();
		countLevel();
		setupTimer();

	}

	/**
	 * Method to setup game area -- view and preview piece area
	 * 
	 * @return gameArea JPanel
	 */
	private JPanel setupGameArea() {

		JPanel gameArea = new JPanel(new GridLayout(1, 3));
		gameArea.add(view);
		gameArea.add(setupPreviewPane());

		return gameArea;
	}

	/**
	 * Method to setup preview piece area
	 * 
	 * @return previewPane JPanel
	 */
	private JPanel setupPreviewPane() {
		JPanel previewPane = new JPanel(new GridLayout(7, 1));

		JLabel empty = new JLabel("   " + " ");
		JLabel infoLabel = new JLabel("Your next piece: ");

		// for sizing and formatting
		for (int i = 0; i < 5; i++) {
			previewPane.add(empty);
		}

		// adds infolabel and preview pane
		previewPane.add(infoLabel);
		previewPane.add(preview);

		return previewPane;
	}

	/**
	 * Method to create a JTextArea that holds the game info -- instructions,
	 * score, current level, etc.
	 * 
	 * @return info a JTextArea
	 */
	private JTextArea gameInfo() {

		info = new JTextArea();
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setEditable(false);
		info.setText("Welcome to Tetris. Press down, right, left keys to move Tetris Pieces, and 'x' or 'z' keys to rotate. Press 'r' to restart current level."
				+ "\n"
				+ "\n"
				+ "Number of lines cleared: "
				+ TetrisGrid.getGameScore()
				+ "\n"
				+ "Number of Tetris Pieces: " + TetrisGrid.getNumTetrises());

		return info;

	}

	/**
	 * Method to execute move
	 * 
	 * @param moveType
	 *            int
	 */
	public static void attemptMove(int moveType) {

		TetrisGrid.tryMove(moveType);
	}

	/**
	 * when a piece cannot move down anymore
	 */
	public void endRound() {
		model.endOfRound();
	}

	public boolean gameLost() {
		boolean gameLost;
		if (model.isAddedPiece() == true) {
			gameLost = false;
		} else {
			gameLost = true;
		}

		return gameLost;
	}

	/**
	 * Method that keeps track of what level we are at
	 * 
	 */
	private static int countLevel() {
		int numlinescleared = TetrisGrid.getGameScore();
		whatLevel = numlinescleared / LEVEL_RATE + 1;
		// whatLevel = numlinescleared + 1;
		return whatLevel;
	}

	/**
	 * Method to setup timer
	 */
	private void setupTimer() {

		gameTimer = new Timer(DEFAULT_DROP_RATE, new ActionListener() {
			/**
			 * Invoked every time the timer finishes
			 */
			public void actionPerformed(ActionEvent e) {
				if (gameLost() == false) {
					// when timer starts move down
					attemptMove(DOWN);
					// repaint();
					view.repaint();
				}

			}
		});

		// start the timer
		gameTimer.start();
	}

	/**
	 * Refresh view - updates the score, changes level, and checks to see if
	 * timer delay needs to be updated
	 */
	private void refreshDisplay() {
		// prints grid from the view
		repaint();
		preview.repaint();
		// update score

		if (gameLost() == true) {
			gameTimer.stop();
			System.out.println("End game");
			info.setText("SORRY! GAME OVER! Press 'r' to restart game !" + "\n"
					+ "\n" + "Number of lines cleared: "
					+ TetrisGrid.getGameScore() + "\n"
					+ "Number of Tetris Pieces: " + TetrisGrid.getNumTetrises()
					+ "\n" + "Current Level: " + whatLevel);
		} else {
			info.setText("Welcome to Tetris. Press down, right, left keys to move Tetris Pieces, and 'x' or 'z' keys to rotate. Press 'r' to restart current level."
					+ "\n"
					+ "\n"
					+ "Number of lines cleared: "
					+ TetrisGrid.getGameScore()
					+ "\n"
					+ "Number of Tetris Pieces: "
					+ TetrisGrid.getNumTetrises()
					+ "\n" + "Current Level: " + whatLevel);
			updateTimer();
		}
	}

	/**
	 * Method to update delay if level has increased
	 */
	private void updateTimer() {
		// check level
		int testLevel = TetrisGrid.getGameScore() / LEVEL_RATE + 1;

		// delay of timer
		if (testLevel > whatLevel) {
			countLevel();

			gameTimer.setDelay(DEFAULT_DROP_RATE - whatLevel * 150);

			System.out.println(" NEW LEVEL ACHIEVED! " + whatLevel);
			System.out.println("new rate = " + gameTimer.getDelay());
		}
	}

	/**
	 * Method to restart level
	 */
	public void restartLevel() {
		// whatLevel ?

		TetrisGrid.setupBoard();
		setupTimer();
	}

	/*************** LISTEN FOR THE KEYSTROKE **************/
	/** Handle the key typed event. */
	public void keyTyped(KeyEvent e) {
		// System.out.println("KEY TYPED: "+ e.getKeyCode() );

	}

	/** Handle the key-pressed event. */
	public void keyPressed(KeyEvent e) {

		if (gameLost() == false) {
			// use switch statements
			switch (e.getKeyCode()) {
			case (KeyEvent.VK_DOWN):
				// moves down when down key pressed
				attemptMove(DOWN);
				break;
			case (KeyEvent.VK_RIGHT):
				// moves right when right key pressed
				attemptMove(RIGHT);
				break;
			case (KeyEvent.VK_LEFT):
				// moves right when right key pressed
				attemptMove(LEFT);
				break;
			case (KeyEvent.VK_X):
				// rotates clockwise when x key pressed
				attemptMove(CW);
				break;
			case (KeyEvent.VK_Z):
				// rotates counter clockwise when z key pressed
				attemptMove(CCW);
				break;
			case (KeyEvent.VK_UP):
				// rotates clockwise when up key pressed
				attemptMove(CW);
				break;
			case (KeyEvent.VK_R):
				System.out.println("Restarting level");
				restartLevel();
				break;
			case (KeyEvent.VK_P):
				gameTimer.stop();
				break;
			case (KeyEvent.VK_S):
				gameTimer.start();
				break;
			default:
				System.out.println("KEY RELEASED: " + e.getKeyCode());

			}
		}

		else if (gameLost() == true) {

			switch (e.getKeyCode()) {
			case (KeyEvent.VK_R):
				whatLevel = 1;
				restartLevel();
				break;
			default:
				System.out.println("KEY RELEASED: " + e.getKeyCode());
			}
		}
		// refresh display after event occurs
		refreshDisplay();
	}

	/** Handle the key-released event. */
	public void keyReleased(KeyEvent e) {
		// System.out.prinln("KEY RELEASED: "+ e.getKeyCode() );
	}
}

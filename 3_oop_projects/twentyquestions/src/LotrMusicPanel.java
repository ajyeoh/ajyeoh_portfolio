import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * LotrMusicPanel.java
 * 
 * This class creates a panel that holds the music associated with the game. New
 * classes explored here: looked at MidiSystem, Sequencer
 * 
 * @author adelyn.yeoh
 *
 *         CS201 - FINAL PROJECT
 */
public class LotrMusicPanel extends JPanel implements ActionListener {

	/**
	 * serial
	 */
	private static final long serialVersionUID = -3960739920413903684L;

	// Variables for song
	private Sequencer lotrSequencer;
	private String[] songButtonsText = { "Play song", "Stop song" };
	private JButton[] songButtons = new JButton[songButtonsText.length];

	// JPanel to hold buttons for panel
	private JPanel songButtonsPanel;

	/**
	 * Constructor sets up buttons, adds buttons to panel, sets up midi
	 * sequencer, and reads midi file
	 */
	public LotrMusicPanel() {
		super();
		setupMidiSequencer(); // setup midi sequencer
		readMidi("lothlorien.mid"); // read midi file
		add(addSongButtons()); // add buttons to panel
	}

	/**
	 * Method to setup buttons
	 */
	private void setupButtons() {
		for (int i = 0; i < songButtonsText.length; i++) {
			// Create buttons and add action listener
			songButtons[i] = new JButton(songButtonsText[i]);
			songButtons[i].addActionListener(this);
		}
	}

	/**
	 * Setup buttons and add buttons to panel
	 * 
	 * @return
	 */
	private JPanel addSongButtons() {

		setupButtons(); // setup buttons

		songButtonsPanel = new JPanel(); // new JPanel

		songButtonsPanel.add(songButtons[0]); // add to panel
		songButtonsPanel.add(songButtons[1]); // add to panel

		return songButtonsPanel;
	}

	/**
	 * Method to read Midi files
	 * 
	 * @param filePath
	 *            String
	 * @return playSong Sequence
	 */
	public Sequence readMidi(String filePath) {

		Sequence playSong = null;
		try {
			// Try to read file
			playSong = MidiSystem.getSequence(new File(filePath));

		} catch (InvalidMidiDataException imde) {
			imde.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return playSong;
	}

	/**
	 * Method to setup midi sequencer
	 */
	public void setupMidiSequencer() {
		try {

			// http://stackoverflow.com/questions/16428098/groovy-shell-warning-could-not-open-create-prefs-root-node
			// Had a similar warning. Problem should not occur on a Mac.
			// Try to set sequencer to default system
			lotrSequencer = MidiSystem.getSequencer();

			// Open sequencer
			lotrSequencer.open();
		} catch (MidiUnavailableException mue) {
			mue.printStackTrace();
			System.out.println("midi unavailable");
		}
	}

	/**
	 * Method to handle actionEvent
	 * 
	 * @param e
	 *            ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton buttonPressed = (JButton) e.getSource();

		// If play song is pressed
		if (buttonPressed.equals(songButtons[0])) {

			// Start playing song. Loop continuously.
			try {
				lotrSequencer.setSequence(readMidi("lothlorien.mid"));
				lotrSequencer.start();
				lotrSequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			} catch (InvalidMidiDataException e1) {

				e1.printStackTrace();
			}

		} // If stop song is pressed
		else if (buttonPressed.equals(songButtons[1])) {
			// Stop playing music
			lotrSequencer.stop();

		}

	}

}

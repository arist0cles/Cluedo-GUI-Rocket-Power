package view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.activity.InvalidActivityException;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * The popup that gets the info from the players, such as the number, individual
 * names and characters they will be represented by. Ties in with the controller
 * as it has a number of ActionListeners
 * 
 * @author Patrick and Kirita
 *
 */
public class SetupPopup extends JOptionPane {
	private static final long serialVersionUID = 1L;
	private JFrame parent = new JFrame();
	private JButton button;
	private JTextField numOfPlayers;
	private List<JRadioButton> buttons;
	private JButton doneButton;
	private JTextField playerName;
	private ButtonGroup bg;

	public SetupPopup() {
		button = new JButton("Enter");
		button.setToolTipText("Enter the number of players");
		numOfPlayers = new JTextField(5);
		buttons = new ArrayList<>();
		setupRadioButtons();
	}

	public List<JRadioButton> getButtons() {
		return this.buttons;
	}

	/**
	 * Pops up the first window to ask how many players will be playing the game
	 */
	public void run() {
		parent.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		parent.add(new JLabel("How many players?"), c);
		c.gridx = 1;
		c.gridy = 0;
		parent.add(numOfPlayers, c);
		parent.add(button);
		parent.setPreferredSize(new Dimension(250, 250));
		parent.pack();
		parent.setVisible(true);
	}

	/**
	 * Opens a new window for each player to enter their name and select a char
	 * to be represented by
	 */
	public void setupEachPlayer() {
		// Closes the previous players window, or initially the number of
		// players window
		closeWindow();
		playerName = new JTextField(10);
		parent = new JFrame();
		parent.add(new JLabel("Enter your name:"));
		parent.add(playerName);
		bg = new ButtonGroup();
		parent.setLayout(new GridLayout(6, 1));
		for (JRadioButton b : buttons) {
			parent.add(b);
			bg.add(b);
		}
		doneButton = new JButton("Done");
		parent.add(doneButton);
		parent.setPreferredSize(new Dimension(300, 300));
		parent.pack();
		parent.setVisible(true);
	}

	public void closeWindow() {
		parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
	}

	public String getPlayerName() {
		return playerName.getText();
	}

	/**
	 * Gets the string attached to the currently selected radiobutton
	 * 
	 * @return the name of the character that is currently selected
	 */
	public String getSelectedButtonText() {

		try {
			for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();
				if (button.isSelected()) {
					this.buttons.remove(button);
					return button.getText();
				}
			}
			throw new InvalidActivityException();
		} catch (InvalidActivityException e) {
			JOptionPane.showMessageDialog(this, "Please select a player using the radiobuttons.");
			//setupEachPlayer();
		}
		// should never get here
		return null;
	}

	public void addNumberOfPlayersListener(ActionListener a) {
		button.addActionListener(a);
	}

	public void addPlayerInfoListener(ActionListener done) {
		doneButton.addActionListener(done);
	}

	public String getNumOfPlayers() {
		return numOfPlayers.getText();
	}

	public String getCharacter() {
		return getSelectedButtonText();
	}

	public void setupRadioButtons() {
		buttons.add(new JRadioButton("Miss Scarlett"));
		buttons.add(new JRadioButton("Professor Plum"));
		buttons.add(new JRadioButton("Colonel Mustard"));
		buttons.add(new JRadioButton("Mrs Peacock"));
		buttons.add(new JRadioButton("Mrs White"));
		buttons.add(new JRadioButton("Reverend Green"));
	}
}
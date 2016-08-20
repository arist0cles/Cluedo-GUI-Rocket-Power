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

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		numOfPlayers = new JTextField(5);
		buttons = new ArrayList<>();
		setupRadioButtons();
	}

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

	public void setupEachPlayer() {
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
	
	public void closeWindow(){
		parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
	}

	public String getPlayerName() {
		return playerName.getText();
	}

	public String getSelectedButtonText() {
		for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
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
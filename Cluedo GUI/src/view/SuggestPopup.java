package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import cards.Card;
import cards.CharacterCard;
import cards.RoomCard;
import cards.WeaponCard;
import core.Player;
import model.Model;
import squares.RoomSquare;

import javax.activity.InvalidActivityException;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SuggestPopup extends JFrame {

	private Model model;
	private JPanel hand;
	private JPanel weaponButtons;
	private JPanel charButtons;
	private JPanel roomButtons;
	private JPanel weapons;
	private JPanel chars;
	private JPanel rooms;
	private JPanel donePanel;
	private JButton done;
	private List<ImageIcon> images;
	private int IMAGE_X = 80;
	private int IMAGE_Y = IMAGE_X + 70;
	private ButtonGroup weaponBG = new ButtonGroup();
	private ButtonGroup charBG = new ButtonGroup();
	private ButtonGroup roomBG = new ButtonGroup();

	public SuggestPopup(Model m) {
		images = new ArrayList<>();
		hand = new JPanel();
		done = new JButton("Done");
		donePanel = new JPanel();
		charButtons = new JPanel();
		roomButtons = new JPanel();
		weaponButtons = new JPanel();
		hand.add(charButtons);
		hand.add(weaponButtons);
		hand.add(roomButtons);
		this.model = m;
		this.setPreferredSize(new Dimension(700, 800));
		hand.setPreferredSize(new Dimension(700, 600));
		donePanel.setPreferredSize(new Dimension(700, 100));
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("Make a suggestion from room: "+((RoomSquare)(model.getSquares()[model.getCurrentPlayer().getLocation().getX()][model.getCurrentPlayer().getLocation().getY()])).getName());
		this.add(hand, BorderLayout.NORTH);
		done.setPreferredSize(new Dimension(50, 20));
		donePanel.add(done);
		this.add(donePanel, BorderLayout.SOUTH);
		this.pack();
		hand.setToolTipText("Pick a weapon and a char");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		displayCards(m.getCurrentPlayer());
	}

	public void displayCards(Player p) {
		for (String c : model.getCharacters()) {
			JRadioButton b = new JRadioButton(c);
			charButtons.add(b);
			charBG.add(b);
			switch (c) {
			case ("Colonel Mustard"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_colonel_mustard.jpg").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Miss Scarlett"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_miss_scarlett.jpg").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Mrs Peacock"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_mrs_peacock.jpg").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Mrs White"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_mrs_white.jpg").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Professor Plum"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_professor_plum.jpg").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Reverend Green"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_rev_green.jpg").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			default:
				throw new IllegalArgumentException("Illegal Character Card");
			}
		}
		for (String c : model.getWeapons()) {
			JRadioButton b = new JRadioButton(c);
			weaponButtons.add(b);
			weaponBG.add(b);
			switch (c) {
			case ("Candlestick"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_candlestick.png").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Dagger"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_dagger.png").getImage().getScaledInstance(IMAGE_X,
						IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Leadpipe"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_lead_piping.png").getImage()
						.getScaledInstance(IMAGE_X, IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Revolver"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_revolver.png").getImage().getScaledInstance(IMAGE_X,
						IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			case ("Rope"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_rope.png").getImage().getScaledInstance(IMAGE_X, IMAGE_Y,
						Image.SCALE_DEFAULT)));
				break;
			case ("Spanner"):
				images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_spanner.png").getImage().getScaledInstance(IMAGE_X,
						IMAGE_Y, Image.SCALE_DEFAULT)));
				break;
			default:
				throw new IllegalArgumentException("Illegal Weapon Card");
			}
		}

		showImages();
	}
	
	public void addDoneButtonListener(ActionListener l) {
		done.addActionListener(l);
	}

	private void showImages() {
		for (ImageIcon i : images) {
			JLabel j = new JLabel();
			j.setIcon(i);
			hand.add(j);
		}
	}

	public String getWeapon() {
		try {
			for (Enumeration<AbstractButton> buttons = weaponBG.getElements(); buttons.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();
				if (button.isSelected()) {
					return button.getText();
				}
			}
			throw new InvalidActivityException();
		} catch (InvalidActivityException e) {
			JOptionPane.showMessageDialog(this, "Please select a player using the radiobuttons.");
		}
		// should never get here
		return null;
		
	}

	
	public String getChar() {
		try {
			for (Enumeration<AbstractButton> buttons = charBG.getElements(); buttons.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();
				if (button.isSelected()) {
					return button.getText();
				}
			}
			throw new InvalidActivityException();
		} catch (InvalidActivityException e) {
			JOptionPane.showMessageDialog(this, "Please select a player using the radiobuttons.");
		}
		// should never get here
		return null;
		
	}

}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cards.Card;
import cards.CharacterCard;
import core.Player;
import model.Model;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class HandPopup extends JFrame {

	private Model model;
	private JPanel hand;
	private List<ImageIcon> images;

	public HandPopup(Model m) {
		images = new ArrayList<>();
		hand = new JPanel();
		this.model = m;
		this.setPreferredSize(new Dimension(500, 500));
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("Hand");
		this.add(hand);
		this.pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		displayHand(m.getPlayers().get(0));
	}

	public void displayHand(Player p) {
		for (Card c : p.getHand()) {
			if (c instanceof CharacterCard) {
				switch (c.getName()) {
				case ("Colonel Mustard"):
					break;
				case ("Miss Scarlett"):
					break;
				case ("Mrs Peacock"):
					break;
				case ("Mrs White"):
					break;
				case ("Professor Plum"):
					break;
				case ("Reverend Green"):
					break;

				}
			}

		}
	}

}

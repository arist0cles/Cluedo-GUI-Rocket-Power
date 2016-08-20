package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cards.Card;
import cards.CharacterCard;
import cards.RoomCard;
import cards.WeaponCard;
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
		p.showHand();
		for (Card c : p.getHand()) {
			if (c instanceof CharacterCard) {
				switch (c.getName()) {
				case ("Colonel Mustard"):
					images.add(new ImageIcon
							(new ImageIcon("Chars\\Black\\card_colonel_mustard.jpg").getImage().getScaledInstance
							(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Miss Scarlett"):
					images.add(new ImageIcon
							(new ImageIcon("Chars\\Black\\card_miss_scarlett.jpg").getImage().getScaledInstance
							(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Mrs Peacock"):
					images.add(new ImageIcon
							(new ImageIcon("Chars\\Black\\card_mrs_peacock.jpg").getImage().getScaledInstance
							(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Mrs White"):
					images.add(new ImageIcon
							(new ImageIcon("Chars\\Black\\card_mrs_white.jpg").getImage().getScaledInstance
							(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Professor Plum"):
					images.add(new ImageIcon
							(new ImageIcon("Chars\\Black\\card_professor_plum.jpg").getImage().getScaledInstance
							(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Reverend Green"):
					images.add(new ImageIcon
							(new ImageIcon("Chars\\Black\\card_rev_green.jpg").getImage().getScaledInstance
							(150, 220, Image.SCALE_DEFAULT)));
					break;
				}
				throw new IllegalArgumentException("Illegal Character Card");
			}
			else if (c instanceof WeaponCard){
				switch(c.getName()){
				case ("Candlestick"):
					break;
				case ("Dagger"):
					break;
				case ("Leadpipe"):
					break;
				case ("Revolver"):
					break;
				case ("Rope"):
					break;
				case ("Spanner"):
					break;
				}
				throw new IllegalArgumentException("Illegal Weapon Card");
			}
			else if (c instanceof RoomCard){
				switch(c.getName()){
				case ("Kitchen"):
					break;
				case ("Ballroom"):
					break;
				case ("Dining"):
					break;
				case ("Conservatory"):
					break;
				case ("Billiard"):
					break;
				case ("Library"):
					break;
				case ("Study"):
					break;
				case ("Lounge"):
					break;
				case ("Garage"):
					break;
				}
				throw new IllegalArgumentException("Illegal Room Card");
			}
		}
	}

}

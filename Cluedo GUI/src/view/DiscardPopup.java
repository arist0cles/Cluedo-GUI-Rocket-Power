package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class DiscardPopup extends JFrame {
	
	private Model model;
	private JPanel hand;
	private List<ImageIcon> images;

	public DiscardPopup(Model m) {
		images = new ArrayList<>();
		hand = new JPanel();
		this.model = m;
		this.setPreferredSize(new Dimension(700, 700));
		this.getContentPane().setLayout(new BorderLayout());
		this.setTitle("Discarded");
		this.add(hand);
		this.pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		displayHand();
	}

	public void displayHand() {
		System.out.println("adflk = "+model.getDiscarded().size());
		for (Card c:model.getDiscarded()) {
			if (c instanceof CharacterCard) {
				switch (c.getName()) {
				case ("Colonel Mustard"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"//Chars/Black/card_colonel_mustard.jpg").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Miss Scarlett"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"//Chars/Black/card_miss_scarlett.jpg").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Mrs Peacock"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_mrs_peacock.jpg").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Mrs White"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_mrs_white.jpg").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Professor Plum"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_professor_plum.jpg").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Reverend Green"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Chars/Black/card_rev_green.jpg").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				default:
					throw new IllegalArgumentException("Illegal Character Card");
				}
			} else if (c instanceof WeaponCard) {
				switch (c.getName()) {
				case ("Candlestick"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_candlestick.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Dagger"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_dagger.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Leadpipe"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_lead_piping.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Revolver"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_revolver.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Rope"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_rope.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Spanner"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Weapons/card_spanner.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				default:
					throw new IllegalArgumentException("Illegal Weapon Card");
				}
			} else if (c instanceof RoomCard) {
				switch (c.getName()) {
				case ("Kitchen"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_kitchen.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Ballroom"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_ballroom.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Dining"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_dining_room.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Conservatory"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_conservatory.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Billiard"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_billiard_room.png").getImage()
							.getScaledInstance(150, 220, Image.SCALE_DEFAULT)));
					break;
				case ("Library"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_library.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Study"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_study.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Lounge"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_lounge.png").getImage().getScaledInstance(150,
							220, Image.SCALE_DEFAULT)));
					break;
				case ("Garage"):
					images.add(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/Rooms/card_hall.png").getImage().getScaledInstance(150, 220,
							Image.SCALE_DEFAULT)));
					break;
				default:
					throw new IllegalArgumentException("Illegal Room Card");
				}
			}
		}
		showImages();
	}

	private void showImages() {
		for (ImageIcon i : images) {
			JLabel j = new JLabel();
			j.setIcon(i);
			hand.add(j);
		}
	}

}

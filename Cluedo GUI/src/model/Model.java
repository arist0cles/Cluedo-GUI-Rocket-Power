package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import cards.Card;
import cards.CharacterCard;
import cards.RoomCard;
import cards.WeaponCard;
import characters.CluedoCharacter;
import characters.ColonelMustard;
import characters.MissScarlett;
import characters.MrsPeacock;
import characters.MrsWhite;
import characters.ProfessorPlum;
import characters.ReverendGreen;
import colorschemes.ColorScheme;
import colorschemes.Emo;
import colorschemes.Kirita;
import core.Board;
import core.Player;
import squares.Square;

/**
 * The model represents data and the rules that govern access to and updates of
 * this data. In enterprise software, a model often serves as a software
 * approximation of a real-world process.
 * 
 * @author Patrick and Kirita
 *
 */
public class Model {
	private ColorScheme colorScheme = new Kirita();
	private Color startingColor;
	private Board board;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ArrayList<Card> allCards; // all cards in the game
	private ArrayList<Card> solution; // three solution cards
	private ArrayList<Card> ruledOut; // cards ruled out during the game
	
	//arrays used to make and deal out all the cards
	private String [] rooms = {"Kitchen", "Ballroom", "Conservatory", "Dining", "Garage", "Library", "Lounge", "Billiard", "Study"};
	String [] weapons = {"Candlestick", "Dagger", "Leadpipe", "Revolver", "Rope", "Spanner"};
	String [] characters = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Reverend Green", "Mrs Peacock", "Professor Plum"};
	
	private boolean finished = false;
	
	public Model(){
		this.players = new ArrayList<Player>();
		this.allCards = intialiseCards();
		this.solution = new ArrayList<Card>();
		this.ruledOut = new ArrayList<Card>();
	}

	public void createPlayer(String name, String character, int ID) {
		//create player + character 
		//add to list of players
		CluedoCharacter charac = characterSelection(character);
		players.add(new Player(ID, name, charac));
		
	}
	
	private CluedoCharacter characterSelection(String character){
		switch (character){
		case "Colonel Mustard":
			return new ColonelMustard(character);
		case "Miss Scarlett":
			return new MissScarlett(character);
		case "Mrs Peacock":
			return new MrsPeacock(character);
		case "Mrs White":
			return new MrsWhite(character);
		case "Professor Plum":
			return new ProfessorPlum(character);
		case "Reverend Green":
			return new ReverendGreen(character);
		}
		throw new IllegalArgumentException("Invalid Character");
	}
	
	 public ArrayList<Card> intialiseCards() { 
		 // make the character + room + weapon cards 
		//add them in to collection of Card objects 
		 ArrayList<Card>all = new ArrayList();
		 for (int i = 0; i < 6; i++) {
			 all.add(new WeaponCard(weapons[i])); 
			 all.add(new CharacterCard(characters[i])); } 
		 for (int j = 0; j < 9; j++) {
			 all.add(new RoomCard(getRooms()[j])); 
		 } 
		 return all;
	}
	
	
	public void dealCards() {
		int wepIdx = new Random().nextInt(weapons.length); 
		String solWeapon = (weapons[wepIdx]); 
		this.solution.add(new WeaponCard(solWeapon)); 
		int charIdx = new Random().nextInt(characters.length); 
		String solCharacter =  (characters[charIdx]);
		this.solution.add(new CharacterCard(solCharacter));
		int romIdx = new Random().nextInt(getRooms().length); 
		String solRoom =(getRooms()[romIdx]); 
		this.solution.add(new RoomCard(solRoom));
		  
		  // removes solution cards from allCards
		 Iterator<Card> it = allCards.iterator(); 
		  while (it.hasNext()) { 
			  Card c = it.next();
			  if (c.getName().equals(solWeapon)) { it.remove(); } 
			  if (c.getName().equals(solCharacter)){ it.remove(); } 
			  if (c.getName().equals(solRoom)){ it.remove(); } }
		  
		  // makes a copy of the cards. 
		  // deals out the cards randomly until they are all gone. 
		  this.ruledOut = intialiseCards();
		  // number of players/21 
		  int numCards = this.ruledOut.size()%players.size();
		  int bound; 
		  if (numCards>0){ bound = ruledOut.size()/players.size();}
		   else {bound = this.ruledOut.size();}
		  System.out.println("Bound "+bound);
		  for(int i = 0; i < bound ; i++){ 
			 for(Player p : this.players){
				 if(this.ruledOut.size() > numCards){ 
					 int cardIdx = new Random().nextInt(this.ruledOut.size()); 
					 Card c = this.ruledOut.get(cardIdx);
					 p.addCard(c);
					 this.ruledOut.remove(cardIdx); 
				 } 
			} 
		} 
}
	
	public void makeBoard(){
		this.board = new Board(colorScheme);
	}

	public void setScheme(ColorScheme c) {
		this.colorScheme = c;
	}

	public ColorScheme getColorScheme() {
		return colorScheme;
	}

	public Color getStartingColor() {
		return startingColor;
	}

	public void setStartingColor(Color startingColor) {
		this.startingColor = startingColor;
	}

	public Square[][] getSquares() {
		return this.board.getSquares();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String [] getRooms() {
		return rooms;
	}

	public void setRooms(String [] rooms) {
		this.rooms = rooms;
	}

	public String [] getCharacters() {
		// TODO Auto-generated method stub
		return characters;
	}

	public String [] getWeapons() {
		// TODO Auto-generated method stub
		return weapons;
	}
	
	public ArrayList<Card> getDiscarded() {
		// TODO Auto-generated method stub
		return ruledOut;
	}
}

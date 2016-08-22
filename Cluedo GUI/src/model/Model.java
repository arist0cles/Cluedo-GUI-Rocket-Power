package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import squares.RoomSquare;
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

	// arrays used to make and deal out all the cards
	private String[] rooms = { "Kitchen", "Ballroom", "Conservatory", "Dining", "Garage", "Library", "Lounge",
			"Billiard", "Study" };
	String[] weapons = { "Candlestick", "Dagger", "Leadpipe", "Revolver", "Rope", "Spanner" };
	String[] characters = { "Miss Scarlett", "Colonel Mustard", "Mrs White", "Reverend Green", "Mrs Peacock",
			"Professor Plum" };
	//is the game finished? 
	private boolean finished = false;

	public Model() {
		this.players = new ArrayList<Player>();
		this.allCards = intialiseCards();
		this.solution = new ArrayList<Card>();
		this.ruledOut = new ArrayList<Card>();
	}
	/**
	 * create player + character
	 * add to list of players
	 * @param name, character, ID
	 * */
	public void createPlayer(String name, String character, int ID) {
		CluedoCharacter charac = characterSelection(character);
		players.add(new Player(ID, name, charac));

	}
	
	/**
	 * selects and returns a specific cluedocharacter object 
	 * dependent upon the string value passed in as the players chosen 
	 * character name
	 * @param character
	 * */
	private CluedoCharacter characterSelection(String character) {
		switch (character) {
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
	/**
	 * make the character + room + weapon cards
	 * add them in to collection of Card objects
	 * @return all
	 * */
	public ArrayList<Card> intialiseCards() {
		ArrayList<Card> all = new ArrayList<Card>();
		for (int i = 0; i < 6; i++) {
			all.add(new WeaponCard(weapons[i]));
			all.add(new CharacterCard(characters[i]));
		}
		for (int j = 0; j < 9; j++) {
			all.add(new RoomCard(rooms[j]));
		}
		return all;
	}
	/**
	 * makes the solution which is an arraylist of 3 cards
	 * then deals the remaining cards out evenly to the players
	 * any left over cards are added to the ruledOut
	 * */
	public void dealCards() {
		int wepIdx = new Random().nextInt(weapons.length);
		String solWeapon = (weapons[wepIdx]);
		this.solution.add(new WeaponCard(solWeapon));
		int charIdx = new Random().nextInt(characters.length);
		String solCharacter = (characters[charIdx]);
		this.solution.add(new CharacterCard(solCharacter));
		int romIdx = new Random().nextInt(rooms.length);
		String solRoom = (rooms[romIdx]);
		this.solution.add(new RoomCard(solRoom));

		// removes solution cards from allCards
		Iterator<Card> it = allCards.iterator();
		while (it.hasNext()) {
			Card c = it.next();
			if (c.getName().equals(solWeapon)) {
				it.remove();
			}
			if (c.getName().equals(solCharacter)) {
				it.remove();
			}
			if (c.getName().equals(solRoom)) {
				it.remove();
			}
		}

		// makes a copy of the cards.
		// deals out the cards randomly until they are all gone.
		for (Card c : this.allCards){
			this.ruledOut.add(c);
		}
		
		// number of players/21
		int numCards = this.ruledOut.size() % players.size();
		int bound;
		
		if (numCards > 0) {
			bound = numCards * players.size();
		} else {bound = this.ruledOut.size();}
		
		for (int i = 0; i < bound; i++) {
			for (Player p : this.players) {
				if (this.ruledOut.size() > numCards) {
					int cardIdx = new Random().nextInt(this.ruledOut.size());
					Card c = this.ruledOut.get(cardIdx);
					p.addCard(c);
					this.ruledOut.remove(cardIdx);
				}
			}
		}
	}
	/**
	 * makes the board and gives it the colorscheme
	 * */
	public void makeBoard() {
		this.board = new Board(colorScheme);
	}
	/**
	 * sets the colorscheme of the model
	 * @param c
	 * */
	public void setScheme(ColorScheme c) {
		this.colorScheme = c;
	}
	/**
	 * returns the current colorscheme of the model
	 * @return colorScheme
	 * */
	public ColorScheme getColorScheme() {
		return colorScheme;
	}
	/**
	 * returns the starting color
	 * @return startingColor
	 * */
	public Color getStartingColor() {
		return startingColor;
	}
	/**
	 * sets the starting color
	 * @param startingColor
	 * */
	public void setStartingColor(Color startingColor) {
		this.startingColor = startingColor;
	}
	/**
	 * returns the array of squares stored in the board
	 * */
	public Square[][] getSquares() {
		return this.board.getSquares();
	}
	
	/**
	 * returns the string array of character names
	 * @return characters
	 * */
	public String [] getCharacters(){
		return this.characters;
	}
	
	/**
	 * returns the string array of weapon names
	 * @return weapons
	 * */
	public String [] getWeapons(){
		return this.weapons;
	}
	
	/**
	 * returns the string array of character names
	 * @return rooms
	 * */
	public String [] getRooms(){
		return this.rooms;
	}
	
	/**
	 * returns the list of players
	 * @return players
	 * */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	/**
	 * returns the current player
	 * @return currentPlayer
	 * */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * sets the current player
	 * @param currentPlayer
	 * */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	/**
	 * returns the list of discarded cards 
	 * @return ruledOut
	 * */
	public ArrayList<Card> getDiscarded() {
		return ruledOut;
	}
	
	/**
	 * checks the passed in suggestion against the players hands 
	 * if their hand contains one or more of the cards in the suggestion, they must
	 * discard at least one to the discard list ruledOut, this method just takes 
	 * the first found card in their hand
	 * throws an exception if the suggestion contains a null value
	 * @param cha, wep
	 * */
	public void checkSuggestion(String cha, String wep) {
		if (cha == null || wep == null) throw new NullPointerException();
		ArrayList<Card> suggest = new ArrayList<Card>();
		for (Card c : intialiseCards()) {
			if (c instanceof CharacterCard) {
				if (c.getName().equals(cha))
					suggest.add(c);
			} else if (c instanceof WeaponCard) {
				if (c.getName().equals(wep))
					suggest.add(c);
			} else if (c instanceof RoomCard) {
				if (c.getName()
						.equals(((RoomSquare) (getSquares()[getCurrentPlayer().getLocation().getX()][getCurrentPlayer()
								.getLocation().getY()])).getName()))
					suggest.add(c);
			}

		}

		//go through players cards 
		//if they have one or more of the suggested cards
		//add the first one to the discarded list
		//and remove from their hand
		for (int i = 0; i < players.size(); i++){
			Player play = players.get(i);
			if (!play.equals(currentPlayer)){
			List <Card> playHas = play.checkHand(suggest);
				if (!playHas.isEmpty()){
					this.ruledOut.add(playHas.get(0));
					play.removeFromHand(playHas.get(0));
				}
			}
		}
		
	}
	/**
	 * checks the accusation against the solution
	 * returns true if correct else returns false
	 * throws exception if accusation contains a null value
	 * */
	public boolean checkAccusaction(String charac, String weap, String room) {
		if (charac == null || weap == null || room == null){throw new NullPointerException();}
		// check accusation against solution
		ArrayList<Card> accuse = new ArrayList<Card>();
		for (Card c : intialiseCards()) {
			if (c instanceof CharacterCard) {
				if (c.getName().equals(charac))
					accuse.add(c);
			} else if (c instanceof WeaponCard) {
				if (c.getName().equals(weap))
					accuse.add(c);
			} else if (c instanceof RoomCard) {
				if (c.getName().equals(room)){
					accuse.add(c);
				}
			}

		}
		int count = 0;
		for (int i=0; i<accuse.size(); i++){
			if(accuse.get(i).equals(solution.get(i))) count++;
		}
		if (count==accuse.size()) return true;
	return false;
	}
	/**
	 * removes currentPlayer from game if accusation returns false
	 * adds their hand to the discard list ruledOut and removes
	 * them from players
	 * */
	public void removePlayerFromGame() {
		List<Card>fromEliminated = currentPlayer.setEliminated(true);
		for (Card ca : fromEliminated){
			this.ruledOut.add(ca);
		}
		this.players.remove(currentPlayer);
	}

}

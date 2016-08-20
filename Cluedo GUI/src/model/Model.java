package model;

import java.awt.Color;
import java.util.ArrayList;

import cards.Card;
import characters.CluedoCharacter;
import characters.ColonelMustard;
import characters.MissScarlett;
import characters.MrsPeacock;
import characters.MrsWhite;
import characters.ProfessorPlum;
import characters.ReverendGreen;
import colorschemes.ColorScheme;
import colorschemes.Emo;
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
	private ColorScheme colorScheme = new Emo();
	private Color startingColor;
	private Board board;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ArrayList<Card> allCards; // all cards in the game
	private ArrayList<Card> solution; // three solution cards
	private ArrayList<Card> ruledOut; // cards ruled out during the game
	
	//arrays used to make and deal out all the cards
	String [] rooms = {"Kitchen", "Ballroom", "Conservatory", "Dining", "Garage", "Library", "Lounge", "Billiard", "Study"};
	String [] weapons = {"Candlestick", "Dagger", "Leadpipe", "Revolver", "Rope", "Spanner"};
	String [] characters = {"Miss Scarlett", "Colonel Mustard", "Mrs White", "Reverend Green", "Mrs Peacok", "Professor Plum"};
	
	private boolean finished = false;
	
	public Model(){
		this.players = new ArrayList<Player>();
		this.allCards = new ArrayList<Card>();
		this.solution = new ArrayList<Card>();
		this.ruledOut = new ArrayList<Card>();
		this.finished = false;
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
}

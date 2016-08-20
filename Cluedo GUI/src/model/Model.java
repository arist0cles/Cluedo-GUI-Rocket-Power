package model;

import java.awt.Color;
import java.util.ArrayList;

import cards.Card;
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
	private boolean finished;
	
	public Model(){
		this.players = new ArrayList<Player>();
		this.allCards = new ArrayList<Card>();
		this.solution = new ArrayList<Card>();
		this.ruledOut = new ArrayList<Card>();
		this.finished = false;
		createCharacters();
	}

	private void createCharacters() {
		// create character objects? - do this when players choose their character?
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
}

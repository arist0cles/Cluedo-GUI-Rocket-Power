package core;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import core.Location;
import squares.RoomSquare;
import squares.Square;

/**
 * This class represents a player in the game. It holds the players name,
 * their hand, their location and gives an indication whether or not they
 * are eliminated.
 * 
 * @author 
 */
public class Player {
	
	private int id;
	private String name;
	private ArrayList<Card> hand;
	private Location location;
	private String [] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"
	, "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
	private boolean eliminated = false;
	private Character c;
	
	public Player(int id, String name, Character c){
		this.c = c;
		this.id = id;
		this.name = name;
		this.hand = new ArrayList<Card>();	
	}
	
	/** Sets players initial location
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Location setStartLoc(int x, int y){
		Location loc = new Location(x, y);
		this.location = loc;
		return loc;
	}
	/** 
	 * returns the player's location on the board. e.g "A5"
	 * @return
	 */
	public String getPlayersPosition(){
		String s = "";
		s = (alpha[location.getX()]) + (location.getY());
		return s;
	}
	/**
	 * 
	 * @param suggestion
	 * @return
	 */
	public List<Card> checkHand(List<Card>suggestion){
		List<Card> result = new ArrayList<Card>();
		for (Card c : this.hand){
			if (suggestion.contains(c)){
				result.add(c);
			}
		}
		return result;
	}
	
	public void removeFromHand(Card c){
		this.hand.remove(c);
	}
	
	/**
	 * get players location on the board
	 * @return
	 */
	public Location getPlayerLocation(){
		return location;
	}
	
	/** Updates the players location.
	 * 
	 * @param new location
	 */
	public void updateLocation(int x, int y){
		Location loc = new Location(x, y);
		this.location = loc;
	}
	
	/**
	 *  gets the players name.
	 *  
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 *  gets the players id.
	 *  
	 * @return
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * gets the players hand.
	 * 
	 * @return
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/** 
	 * adds card to players hand.
	 * 
	 * @param c
	 */
	public void addCard(Card c){
		hand.add(c);
	}
	
	/**
	 * shows players hand.
	 * 
	 */
	public void showHand(){
		System.out.println();
		System.out.print("Hand:\n");
		for(Card c : hand){
			System.out.print("   " + c.getName() + "\n");
		}
		System.out.println("");

	}
	
	/**
	 * Get current location of player.
	 */
	public Location getLocation(){
		return location;
	}
	
	/**
	 * returns eliminated t/f
	 * @return
	 */
	public boolean getEliminated(){
		return eliminated;
	}
	
	/**
	 * sets eliminated.
	 * 
	 */
	public List<Card> setEliminated(boolean b){
		this.eliminated = b;
		return this.hand;
	}
	
	/**
	 * getter for the string array "alpha"
	 * @return
	 */
	public String [] getAlpha(){
		return alpha;
	}
}

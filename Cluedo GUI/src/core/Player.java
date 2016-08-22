package core;

import java.util.ArrayList;
import java.util.List;

import cards.Card;
import characters.CluedoCharacter;
import core.Location;


/**
 * This class represents a player in the game. It holds the players name,
 * their hand, their location and gives an indication whether or not they
 * are eliminated.
 * 
 * @author kirita escott and patrick ryan
 */
public class Player {
	
	private int id;
	private String name;
	private ArrayList<Card> hand;
	private Location location;
	private boolean eliminated = false;
	private CluedoCharacter c;
	
	public Player(int id, String name, CluedoCharacter c){
		this.c = c;
		//initial location on the board depends upon character choice
		this.location = c.getLocation();
		this.name = name;
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 * List of cards in the suggestion passed to player to 
	 * compare against players hand
	 * @param suggestion
	 * @return result
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
	
	/**
	 * player is passed a card object to remove from their hand
	 * @param c
	 * */
	public void removeFromHand(Card c){
		this.hand.remove(c);
	}
	
	/**
	 * get players location on the board
	 * @return location
	 */
	public Location getPlayerLocation(){
		return location;
	}
	
	/** 
	 * Updates the players location.
	 * @param l
	 */
	public void updateLocation(Location l){
		this.location = l;
	}
	
	/** 
	 * Gets the players character
	 * @return c
	 */
	public CluedoCharacter getCharacter(){
		return this.c;
	}
	
	/**
	 *  gets the players name.
	 *  
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 *  gets the players id.
	 *  
	 * @return id
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * gets the players hand.
	 * 
	 * @return hand
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	/** 
	 * adds card to players hand
	 * @param c
	 */
	public void addCard(Card c){
		hand.add(c);
	}
	
	
	/**
	 * Get current location of player.
	 * @return location
	 */
	public Location getLocation(){
		return location;
	}
	
	/**
	 * returns eliminated t/f
	 * @return eliminated
	 */
	public boolean getEliminated(){
		return this.eliminated;
	}
	
	/**
	 * sets eliminated to be true then returns
	 * hand
	 * @param b
	 * @return hand
	 */
	public List<Card> setEliminated(boolean b){
		this.eliminated = b;
		return this.hand;
	}
	
}

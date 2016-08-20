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
 * @author 
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
		System.out.println("Created player: "+this.name+" with character: "+this.c.getName());
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
		this.location = new Location(x,y);
	}
	
	/** Gets the players character
	 * 
	 * @return c
	 */
	public CluedoCharacter getCharacter(){
		return this.c;
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
	 * change to show the images 
	 * 
	 */
	public void showHand(){
		System.out.println();
		System.out.print(this.hand.size()+" Hand:\n");
		for(Card c : hand){
			System.out.print("card is: " + c.getName() + "\n");
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
		return this.eliminated;
	}
	
	/**
	 * sets eliminated.
	 * 
	 */
	public List<Card> setEliminated(boolean b){
		this.eliminated = b;
		return this.hand;
	}
	
}

package cards;

/**
 * Interface all card objects must implement, stores the name of the card 
 * */

public interface Card {
	
	String name = "";
	
	/**
	 * returns the name of the card
	 * @return name
	 * */
	public String getName();

}

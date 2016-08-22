package core;

/**
 * This class represents the x, y coordinate of an object in the game
 * Can be set on initialization, and updated (within a player) throughout the game
 * 
 * @author kirita escott and patrick ryan
 * 
 */

public class Location {
	
	private int x=0;
	private int y=0; 
	
	public Location (int x, int y){
		this.x=x; this.y=y;
	}
	/**
	 * updates the x and y coordinates of this object
	 * @param x,y
	 * */
	public void updateLocation(int x, int y){
		this.x=x; this.y=y;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	/**
	 * determines whether this obj is equal to the parameter obj 
	 * passed in returns a boolean 
	 * @param l
	 * */
	public boolean equals(Location l){
		return this.x==l.getX() && this.y==l.getY();
	}
	
}

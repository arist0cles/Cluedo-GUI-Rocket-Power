package core;

/**
 * This class represents the x, y coordinate of an object in the game
 * Can be set on initialization, and updated (within a player) throughout the game
 * 
 * @author kirita
 * 
 */

public class Location {
	
	private int x=0;
	private int y=0; 
	
	public Location (int x, int y){
		this.x=x; this.y=y;
	}
	
	public void updateLocation(int x, int y){
		this.x=x; this.y=y;
	}
	
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	public boolean equals(Location l){
		return this.x==l.getX() && this.y==l.getY();
	}
	
}

package squares;

import java.awt.Color;

import core.Location;

public class RoomSquare extends Square{
	String name="";
	String oppRoom="";
	Location location = null;
	Location oppLoc = null;
	String weapon = null;
	boolean stairs = false;
	
	public RoomSquare(String name, Location location, Color c) {
		super(name, location, c);
		setName(name);
	}
	
	public void setName(String s){
		switch(s){
		case "K":
			this.name = "KITCHEN";
			this.stairs = true;
			this.oppRoom = "STUDY";
			this.oppLoc = new Location(22, 22); 
			break;
		case "B":
			this.name = "BALLROOM";
			break;
		case "C":
			this.name = "CONSERVATORY";
			this.stairs = true;
			this.oppRoom = "LOUNGE";
			this.oppLoc = new Location(3, 22); 
			break;
		case "d":
			this.name = "DINING";
			break;
		case "G":
			this.name = "GAMESROOM";
			break;
		case "L":
			this.name = "LIBRARY";
			break;
		case "l":
			this.name = "LOUNGE";
			this.stairs = true;
			this.oppRoom = "CONSERVATORY";
			this.oppLoc = new Location(22, 3); 
			break;
		case "b":
			this.name = "BEDROOM";
			break;
		case "S":
			this.name = "STUDY";
			this.stairs = true;
			this.oppRoom = "KITCHEN";
			this.oppLoc = new Location(3, 3); 
			break;
		}
			
	}
	
	public boolean getStairs(){
		return this.stairs;
	}
	
	public String getOpposite(){
		return this.oppRoom;
	}
	
	public Location getOppLoc(){
		return this.oppLoc;
	}
	
	public void setWeapon(String s){
		this.weapon = s;
	}
	
	public String getWeapon(){
		return this.weapon;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean equals(Square square){
		return super.equals(square);
	}

}

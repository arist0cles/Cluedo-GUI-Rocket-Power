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
			this.name = "Kitchen";
			this.stairs = true;
			this.oppRoom = "Study";
			this.oppLoc = new Location(22, 22); 
			break;
		case "B":
			this.name = "Ballroom";
			break;
		case "C":
			this.name = "Conservatory";
			this.stairs = true;
			this.oppRoom = "Lounge";
			this.oppLoc = new Location(3, 22); 
			break;
		case "d":
			this.name = "Dining";
			break;
		case "G":
			this.name = "Garage";
			break;
		case "L":
			this.name = "Library";
			break;
		case "l":
			this.name = "Lounge";
			this.stairs = true;
			this.oppRoom = "Conservatory";
			this.oppLoc = new Location(22, 3); 
			break;
		case "b":
			this.name = "Billiard";
			break;
		case "S":
			this.name = "Study";
			this.stairs = true;
			this.oppRoom = "Kitchen";
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

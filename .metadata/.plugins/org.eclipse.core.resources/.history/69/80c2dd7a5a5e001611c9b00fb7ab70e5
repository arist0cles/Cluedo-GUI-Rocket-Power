package squares;

import core.Location;

public class DoorSquare extends Square {
	String name=""; 
	Location location = null;
	

	public DoorSquare(String name, Location location) {
		super(name, location);
		setName();
	}
	
	public void setName(){
		this.name="Door";
	}
	
	@Override
	public void draw(){
		System.out.print(" =");
	}
	
	public String getName(){
		return this.name;
	}

	public boolean equals(Square square){
		return super.equals(square);
	}
}

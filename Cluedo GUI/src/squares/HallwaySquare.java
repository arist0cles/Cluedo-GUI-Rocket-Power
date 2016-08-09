package squares;

import core.Location;

public class HallwaySquare extends Square {
	String name="";
	Location location=null;

	public HallwaySquare(String name, Location location) {
		super(name, location);
		setName();
	}
	
	public void setName(){
		this.name = "Hall";
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public void draw(){
		System.out.print(" *");
	}

	public boolean equals(Square square){
		return super.equals(square);
	}
	
}

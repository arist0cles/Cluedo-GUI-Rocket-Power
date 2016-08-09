package squares;

import core.Location;

public class WallSquare extends Square{
	String name="";
	Location location=null;

	public WallSquare(String name, Location location) {
		super(name, location);
		setName();
	}
	
	public void setName(){
		this.name="Wall";
	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public void draw(){
		System.out.print(" #");
	}
	
	public boolean equals(Square square){
		return super.equals(square);
	}
	
}
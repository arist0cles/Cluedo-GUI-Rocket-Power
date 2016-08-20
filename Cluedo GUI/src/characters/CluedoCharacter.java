package characters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import core.Location;
import squares.Square;

public abstract class CluedoCharacter {
	
	private Square current;
	private String name;
	private Color c;
	private BufferedImage pic;
	private Location loc;
	
	public CluedoCharacter(Square starting, String name, Color c, String picLocation, Location location){
		setImage(picLocation);
		this.current = starting;
		this.name = name;
		this.c = c;
		this.loc = location;
	}
	
	public Location getLocation(){
		return this.loc;
	}

	private void setImage(String picLocation) {
		File f = new File(picLocation);
		//set to pic to be a buffered image based on file f
	}
}

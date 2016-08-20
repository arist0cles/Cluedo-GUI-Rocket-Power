package characters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import core.Location;
import squares.Square;

public abstract class CluedoCharacter {
	
	
	private String name;
	private Color c;
	private BufferedImage pic;
	private Location loc;
	
	public CluedoCharacter(String name, Color c, Location location){
		this.name = name;
		this.c = c;
		this.loc = location;
	}
	
	public Location getLocation(){
		return this.loc;
	}
	
	public String getName(){
		return this.name;
	}

	private void setImage() {
		//set to pic to be a buffered image based on file f
	}
}

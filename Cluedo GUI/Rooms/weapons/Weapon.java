package weapons;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import squares.Square;

public abstract class Weapon {
	
	private Square current;
	private String name;
	private Color c;
	private BufferedImage pic;
	
	public Weapon(Square starting, String name, Color c, String picLocation){
		setImage(picLocation);
		this.current = starting;
		this.name = name;
		this.c = c;
	}

	private void setImage(String picLocation) {
		File f = new File(picLocation);
		//set to pic to be a buffered image based on file f
	}
}

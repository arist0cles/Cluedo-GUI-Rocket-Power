package characters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import core.Location;
import squares.Square;

/**
 * This abstract class is extended by all cluedo character objects eg./ColonelMustard
 * Each character stores their name, color, symbol(for visual representation on the board) and location
 * they always begin at on the board
 * */

public abstract class CluedoCharacter {

	private String name;
	private Color c;
	private Location loc;
	private String symbol;

	public CluedoCharacter(String name, Color c, Location location) {
		this.name = name;
		this.c = c;
		this.loc = location;
	}
	
	/**
	 * returns the starting location
	 * @return loc
	 * */
	public Location getLocation() {
		return this.loc;
	}
	/**
	 * returns the name of character
	 * @return name
	 * */
	public String getName() {
		return this.name;
	}
	/**
	 * returns the color of character
	 * @return c
	 * */
	public Color getColor() {
		return this.c;
	}

	/**
	 * returns the symbol of character
	 * @return symbol
	 * */
	public String getSymbol(){
		return symbol;
	}
	
	public abstract String getID();
}

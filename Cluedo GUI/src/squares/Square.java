package squares;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import core.Location;

/**
 * This class represents a square on the board 
 * All square types extend this abstract class
 * 
 * @author kirita escott and patrick ryan
 * */

public abstract class Square {
	private String name;
	private Location location;
	private Color color;
	
	public Square(String name, Location location, Color color){
		this.color = color;
		this.name=name; 
		this.location=location;
	}
	/**
	 * returns the name of the square
	 * @return name
	 * */
	public String getName(){
		return name;
	}
	/**
	 * returns the location of the square
	 * @return location
	 * */
	public Location getLoc(){
		return location;
	}
	/**
	 * returns the color of the square
	 * @return color
	 * */
	public Color getColor(){
		return color;
	}
	/**
	 * sets the color of the square
	 * @param c
	 * */
	public void setColor(Color c){
		color = c;
	}

}

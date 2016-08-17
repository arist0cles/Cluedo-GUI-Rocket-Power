package squares;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import core.Location;

public abstract class Square {
	private String name;
	private Location location;
	private Color color;
	
	public Square(String name, Location location, Color color){
		this.color = color;
		this.name=name; 
		this.location=location;
	}
	
	public String getName(){
		return name;
	}
	
	public Location getLoc(){
		return location;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}

}

package squares;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import core.Location;

public abstract class Square {
	private String name;
	private Location location;
	private Color color;
	private Graphics g;
	private final int SQUARE_SIZE = 15;
	
	public Square(String name, Location location, Color color){
		this.name=name; 
		this.location=location;
		this.color = color;
	}
	
	public void draw(int x1, int y1){
		g.setColor(color);
		g.fillRect(x1, y1, SQUARE_SIZE, SQUARE_SIZE);
	}

	public void setGraph(Graphics g) {
		this.g = g;
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

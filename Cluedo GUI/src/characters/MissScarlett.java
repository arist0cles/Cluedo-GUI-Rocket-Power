package characters;

import java.awt.Color;

import core.Location;


public class MissScarlett extends CluedoCharacter{
	//initial start position character always starts on
	private static Location start = new Location(24,19);
	private static Color color = Color.red;
	protected String symbol = "MS";
	protected String ID = "1";

	public MissScarlett(String name) {
		super(name, color, start);
	}
	/**
	 * returns the symbol of character
	 * @return symbol
	 * */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * returns the ID NO of character
	 * @return ID
	 * */
	@Override
	public String getID() {
		return ID;
	}
	
}

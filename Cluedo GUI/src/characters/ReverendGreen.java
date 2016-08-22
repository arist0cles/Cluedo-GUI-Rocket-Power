package characters;

import java.awt.Color;

import core.Location;


public class ReverendGreen extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(7,24);
		private static Color color = Color.green;
		protected String symbol = "RG";
		protected String ID = "5";

	public ReverendGreen(String name) {
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


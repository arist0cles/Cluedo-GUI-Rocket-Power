package characters;

import java.awt.Color;

import core.Location;


public class ReverendGreen extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(7,24);
		private static Color color = Color.green;
		protected String symbol = "RG";

	public ReverendGreen(String name) {
		super(name, color, start);
	}

	public String getSymbol() {
		return symbol;
	}

}


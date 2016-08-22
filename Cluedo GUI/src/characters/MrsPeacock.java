package characters;

import java.awt.Color;

import core.Location;

public class MrsPeacock extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(17,24);
		private static Color color = Color.blue;
		protected String symbol = "MP";
		protected String ID = "2";

	public MrsPeacock(String name) {
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

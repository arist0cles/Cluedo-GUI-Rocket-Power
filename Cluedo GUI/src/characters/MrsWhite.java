package characters;

import java.awt.Color;

import core.Location;

public class MrsWhite extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(7,0);
		private static Color color = Color.LIGHT_GRAY;
		protected String symbol = "MW";
		protected String ID = "3";

	public MrsWhite(String name ){
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

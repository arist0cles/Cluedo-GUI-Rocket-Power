package characters;

import java.awt.Color;

import core.Location;

public class ProfessorPlum extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(0,17);
		private static Color color = Color.magenta;
		protected String symbol = "PP";
		protected String ID = "4";

	public ProfessorPlum(String name) {
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

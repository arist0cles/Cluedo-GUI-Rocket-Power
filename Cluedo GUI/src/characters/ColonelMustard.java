package characters;

import java.awt.Color;

import core.Location;


public class ColonelMustard extends CluedoCharacter{
	//initial start position character always starts on 
	private static Location start = new Location(17,0);
	private static Color color = Color.yellow;
	protected String symbol = "CM";
	protected String ID = "0";

	public ColonelMustard(String name) {
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

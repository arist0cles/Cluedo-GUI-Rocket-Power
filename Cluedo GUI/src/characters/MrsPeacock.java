package characters;

import java.awt.Color;

import core.Location;

public class MrsPeacock extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(17,24);
		private static Color color = Color.blue;
		protected String symbol = "MP";

	public MrsPeacock(String name) {
		super(name, color, start);
	}

	public String getSymbol() {
		return symbol;
	}

}

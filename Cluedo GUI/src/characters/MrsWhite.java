package characters;

import java.awt.Color;

import core.Location;

public class MrsWhite extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(7,0);
		private static Color color = Color.white;
		protected String symbol = "MW";

	public MrsWhite(String name ){
		super(name, color, start);
	}

	public String getSymbol() {
		return symbol;
	}

}

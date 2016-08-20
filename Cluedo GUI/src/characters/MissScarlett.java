package characters;

import java.awt.Color;

import core.Location;


public class MissScarlett extends CluedoCharacter{
	//initial start position character always starts on
	private static Location start = new Location(24,16);
	private static Color color = Color.red;

	public MissScarlett(String name) {
		super(name, color, start);
	}


}

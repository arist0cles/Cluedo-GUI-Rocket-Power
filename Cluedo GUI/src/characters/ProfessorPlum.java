package characters;

import java.awt.Color;

import core.Location;
import squares.Square;

public class ProfessorPlum extends CluedoCharacter{
	//initial start position character always starts on
		private static Location start = new Location(0,16);

	public ProfessorPlum(Square starting, String name, Color c, String picLocation) {
		super(starting, name, c, picLocation, start);
	}
}

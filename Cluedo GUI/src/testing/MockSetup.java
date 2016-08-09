package testing;

import java.util.ArrayList;
import java.util.List;

import core.Player;

public class MockSetup {

	Player setMockPlayer(){
		return new Player(1, "Kirita");
	}
	
	ArrayList<Player> setMockPlayers(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(1, "Kirita"));
		players.add(new Player(2, "Tayler"));
		players.add(new Player(3, "Louis"));
		return players;
	}
	
	List<String> rooms(){
		ArrayList <String> rooms = new ArrayList<String>();
		rooms.add("KITCHEN");
		rooms.add("BALLROOM");
		rooms.add("CONSERVATORY");
		rooms.add("DINING");
		rooms.add("GAMESROOM");
		rooms.add("LIBRARY");
		rooms.add("LOUNGE");
		rooms.add("BEDROOM");
		rooms.add("STUDY");
		return rooms;
	}
	
	List<String>weapons(){
		ArrayList <String> weapons = new ArrayList<String>();
		weapons.add("CANDLESTICK");
		weapons.add("DAGGER");
		weapons.add("LEADPIPE");
		weapons.add("REVOLVER");
		weapons.add("ROPE");
		weapons.add("SPANNER");
		return weapons;
	}
	
	List<String>characters(){
		List <String> characters = new ArrayList<>();
		characters.add("MISS SCARLETT");
		characters.add("COLONEL MUSTARD");
		characters.add("MRS WHITE");
		characters.add("THE REVEREND GREEN");
		characters.add("MRS PEACOCK");
		characters.add("PROFESSOR PLUM");
		return characters;
	}
	
}

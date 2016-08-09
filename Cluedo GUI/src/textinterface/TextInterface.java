package textinterface;
import java.io.*;
import java.util.ArrayList;

import core.Game;
import core.Player;
import core.Location;



/**
 * This class contains the code for the Text based interface for the Cluedo game. It
 * contains the controls for how the user can interact.
 *
 * @author
 */
public class TextInterface {

	/**
	 * Get string from System.in
	 */
	public static String inputString(String msg) {
		System.out.print(msg + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				return input.readLine();
			} catch (IOException e) {
				System.out.println("I/O Error ... please try again!");
			}
		}
	}
	
	/**
	 * Get integer from System.in
	 */
	public static int inputNumber(String msg) {
		System.out.print(msg + " ");
		while (true) {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				int v = Integer.parseInt(input.readLine());
				return v;
			} catch (IOException e) {
				System.out.println("Please enter a number!");
			}
		}
	}
	
	/**
	 * Input player details from System.in
	 */
	private static ArrayList<Player> getPlayers(int numOfplayers) {
		
		// input data
		ArrayList<Player> players = new ArrayList<Player>();
		String name = "";
		for (int i = 1; i <= numOfplayers; ++i) {
			name = inputString("Player #" + i + " name?");
			players.add(new Player(i, name));
		}
		return players;
	}
	
	public static void main(String args[]) {
		// Banner
		System.out.println("___ CLUEDO ___");
		System.out.println("By Kirita-Rose Escott and Tayler Barnett");
		System.out.println("");
		
		//new game object
		Game game = new Game();
		//make characters + weapons + rooms (CARDS)
		
		//draw the initial board - without the players
		game.getBoard().draw();
		
		// input player info
		int numOfPlayers = inputNumber("How many players?");
		while (numOfPlayers<3 || numOfPlayers>6) numOfPlayers = inputNumber("Must be between 3 and 6 players only.");
		
		//make the player objects 
		ArrayList<Player> players = getPlayers(numOfPlayers);
		
		game.setPlayers(players);

		//initialise the game (shuffle and deal cards, etc before running actual game)
		game.intialise();
		game.dealCards();
		game.placePlayers();	//fine
		game.playGame();			//fine
	}
	
}



package model;

import controller.Controller;
import view.View;

/**
 * Represents a game of cluedo
 * Creates the model, view and controller in the constructor
 * Which then runs the game
 * @author kirita escott and patrick ryan
 *
 */
public class Game {
	/**
	 * Game Constructor.
	 */
	public Game() {
		Model m = new Model();
		View v = new View(m);
		Controller c = new Controller(m, v);
	}
	/**
	 * starts the game
	 * @param args
	 * */
	public static void main(String args[]) {
		new Game();
	}
	
}

package controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import colorschemes.BW;
import colorschemes.Emo;
import colorschemes.Kirita;
import colorschemes.Pastel;
import core.Player;
import model.Model;
import view.SetupPopup;
import view.View;

/**
 * The controller translates the user's interactions with the view into actions
 * that the model will perform. In a stand-alone GUI client, user interactions
 * could be button clicks or menu selections. Depending on the context, a
 * controller may also select a new view -- for example, a web page of results
 * -- to present back to the user.
 * 
 * @author Patrick and Kirita
 *
 */
public class Controller {
	private Model model;
	private View view;
	private SetupPopup pop = new SetupPopup();
	private int numOfPlayers;
	private int count=0;
	private boolean finished = false; //is the game finished? 
	private Player currentPlayer;	//current player

	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		addNumberOfPlayersListener();
		addStartListener();
		addQuitMenuListener();
		addShowHandMenuListener();
	}

	public void addStartListener() {
		view.addStartButtonListener(e -> {
			setup();
		});
	}
	
	public void addQuitMenuListener() {
		view.addQuitMenuListener(e -> {
			view.quit();
		});
	}
	
	public void addShowHandMenuListener() {
		view.addShowHandMenuListener(e -> {
			view.showHand();
		});
	}

	public void addNumberOfPlayersListener() {
		pop.addNumberOfPlayersListener(e -> {
			String players = pop.getNumOfPlayers();
			numOfPlayers = Integer.parseInt(players);
			pop.setupEachPlayer();
			count++;
			addPlayerInfoListener();
		});
	}

	public void addPlayerInfoListener() {
		pop.addPlayerInfoListener(e -> {
			if (count >= numOfPlayers){
				String playerName = pop.getPlayerName();
				String charName = pop.getSelectedButtonText();
				this.model.createPlayer(playerName, charName, count);
				pop.closeWindow();
				start();
				return;
			}
			String playerName = pop.getPlayerName();
			String charName = pop.getSelectedButtonText();
			this.model.createPlayer(playerName, charName, count);
			count++;
			pop.setupEachPlayer();
			addPlayerInfoListener();
		});
	}
	
	/**
	 * Pops up the setup windows which get the info on all the players
	 */
	public void setup(){
		pop.run();
	}
	
	//CALL THIS TO START GAME ONCE SETUP IS WORKING!!!!
	/**
	 * Starts the game. Gets the selected colorscheme, creates the board
	 */
	public void start() {
		String scheme = view.getScheme();
		switch (scheme) {
		case "Kirita":
			model.setScheme(new Kirita());
			break;
		case "Emo":
			model.setScheme(new Emo());
			break;
		case "Pastel":
			model.setScheme(new Pastel());
			break;
		case "BW":
			model.setScheme(new BW());
			break;
		}
		model.makeBoard();
		view.setGridPaneStarted();
		view.redraw();
	}

	private void play() {
		// begin play
		//got all the players, make the solution and deal the cards
		model.dealCards();
		currentPlayer = model.getPlayers().get(0); //first playaaa
	}
	
	public void currentPlayerTurn(){
		//player whoevers turn 
		
	}
}

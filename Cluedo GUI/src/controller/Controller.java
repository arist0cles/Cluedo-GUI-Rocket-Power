package controller;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JOptionPane;

import astarpathfind.AStarPath;
import astarpathfind.pathNode;
import colorschemes.BW;
import colorschemes.Emo;
import colorschemes.Kirita;
import colorschemes.Pastel;
import core.Die;
import core.Location;
import core.Player;
import model.Model;
import view.AccusePopup;
import view.DiscardPopup;
import view.HandPopup;
import squares.RoomSquare;
import squares.Square;
import view.SetupPopup;
import view.SuggestPopup;
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
	private SuggestPopup suggest;
	private AccusePopup accuse;
	private int numOfPlayers;
	private int count=0;
	private boolean firstTurn = false;
	private boolean finished = false; //is the game finished? 
	private int currentRoll;


	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		addNumberOfPlayersListener();
		addStartListener();
		addQuitMenuListener();
		addShowHandMenuListener();
		addShowDicardMenuListener();
	}

	public void addStartListener() {
		view.addStartButtonListener(e -> {
			setup();
		});
	}
	
	public void addAccuseButtonListener() {
		view.addAccuseButtonListener(e -> {
			accuse = new AccusePopup(model);
			addAccuseDoneButtonListener();
		});
	}
	
	public void addSuggestButtonListener() {
		view.addSuggestButtonListener(e -> {
			suggest = new SuggestPopup(model);
			addDoneButtonListener();
		});
	}
	
	public void addEndTurnButtonListener() {
		view.addEndTurnButtonListener(e -> {
			endTurn();
		});
	}

	/**
	 * Ends the turn of the the current player. If that player is the last in the arraylist
	 * start again from players[0]
	 */
	private void endTurn() {
		int idx = model.getPlayers().indexOf(model.getCurrentPlayer());
		if ((idx+1)>=model.getPlayers().size()){
			model.setCurrentPlayer(model.getPlayers().get(0));
			currentPlayerTurn();
		} else {
			model.setCurrentPlayer(model.getPlayers().get(idx+1)); 
			currentPlayerTurn();
			}
	}

	public void addQuitMenuListener() {
		view.addQuitMenuListener(e -> {
			view.quit();
		});
	}

	public void addShowHandMenuListener() {
		view.addShowHandMenuListener(e -> {
			HandPopup h = new HandPopup(model);
		});
	}
	
	public void addShowDicardMenuListener() {
		view.addShowDiscardMenuListener(e -> {
			DiscardPopup d = new DiscardPopup(model);
		});
	}
	
	public void addDoneButtonListener(){
		suggest.addDoneButtonListener(e -> {
			System.out.println(suggest.getWeapon());
			System.out.println(suggest.getRoom());
			System.out.println(suggest.getChar());
		});
	}
	
	public void addAccuseDoneButtonListener(){
		accuse.addAccuseDoneButtonListener(e -> {
			System.out.println(accuse.getWeapon());
			System.out.println(accuse.getRoom());
			System.out.println(accuse.getChar());
		});
	}

	public void addGridMouseListener() {
		view.addGridMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getX()>60+15*25 || me.getY()>50+15*25) return;
				if (me.getX()<60 || me.getY()<50) return;
				int x = (me.getX()-60)/15;
				int y = (me.getY()-50)/15;
				Location l = new Location(x, y);
				
				if (tryMove(l)){
					//can move so do so!! 
					
					model.getCurrentPlayer().updateLocation(l);
					currentRoll=0;
					
					view.redraw();
				}
			}
		});
	}
	
	/**
	 * Returns whether the given move is valid
	 * 
	 * @param l the location they are trying to move to
	 * @return whether or not the move is valid
	 */
	public boolean tryMove(Location l){
		Square local = model.getSquares()[model.getCurrentPlayer().getLocation().getX()][model.getCurrentPlayer().getLocation().getY()];
		Square target = model.getSquares()[l.getX()][l.getY()];
		pathNode start = new pathNode(local, model);
		pathNode end = new pathNode(target, model);
		AStarPath aStar = new AStarPath(start, end);
		
		if (aStar.findPath() <= this.currentRoll){ return true;}
		
		return false;
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
			if (count >= numOfPlayers) {
				//when the last player is done setting up, start the game
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
	public void setup() {
		pop.run();
	}

	// CALL THIS TO START GAME ONCE SETUP IS WORKING!!!!
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
		view.addPlayButtons();
		addEndTurnButtonListener();
		addAccuseButtonListener();
		addGridMouseListener();
		view.setGridPaneStarted();
		play();
	}

	private void play() {
		// begin play
		// got all the players, make the solution and deal the cards
		model.dealCards();
		model.setCurrentPlayer(model.getPlayers().get(0));
		firstTurn = true;
		currentPlayerTurn();
	}
	
	private void rollDice(){
		Die d1 = new Die();
		Die d2 = new Die();
		currentRoll = d1.getRoll()+d2.getRoll();
		view.addDiceToPane(d1, d2);
		view.redraw();
	}
	
	public void currentPlayerTurn(){
		if (!firstTurn) view.removeDice();
		view.redraw();
		firstTurn = false;
		Square local = model.getSquares()[model.getCurrentPlayer().getLocation().getX()][model.getCurrentPlayer().getLocation().getY()];
		if (local instanceof RoomSquare){
			//check if corner square for stairways
			if (((RoomSquare)local).getStairs()){
				//am able to move via stairs
				view.addSuggestButton();
				addSuggestButtonListener();
				view.redraw();
				if(view.moveDiagonal()==0){
					model.getCurrentPlayer().updateLocation(((RoomSquare)local).getOppLoc());
					view.redraw();
					currentRoll=0;
				}
				else {rollDice();}
				} else {rollDice();}
		} 
		else {rollDice();}
		
	} 
	
}


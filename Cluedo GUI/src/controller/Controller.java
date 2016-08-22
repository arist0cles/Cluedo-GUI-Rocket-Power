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
	/**
	 * This method adds the start button listener to the view
	 * */
	public void addStartListener() {
		view.addStartButtonListener(e -> {
			setup();
		});
	}
	/**
	 * This method adds the accuse button listener to the view
	 * and creates and accusePopup object which allows the user to 
	 * make an accusation, also adds a done button listener to record and
	 * responds to players accusation
	 * */
	public void addAccuseButtonListener() {
		view.addAccuseButtonListener(e -> {
			accuse = new AccusePopup(model);
			addAccuseDoneButtonListener();
		});
	}
	/**
	 * This method adds the suggestion button listener to the view
	 * and creates and suggestPopup object which allows the user to 
	 * make an suggestion, also adds a done button listener to record and
	 * responds to players suggestion
	 * */
	public void addSuggestButtonListener() {
		view.addSuggestButtonListener(e -> {
			suggest = new SuggestPopup(model);
			addDoneButtonListener();
		});
	}
	/**
	 * This method adds an end turn button in the view and responds to 
	 * it being pressed by calling the end turn method below
	 * */
	public void addEndTurnButtonListener() {
		view.addEndTurnButtonListener(e -> {
			endTurn();
		});
	}

	/**
	 * Ends the turn of the the current player. If that player is the last in the arraylist
	 * start again from players[0]
	 * Also removes the the suggest button from the pane
	 */
	private void endTurn() {
		view.removeSuggest();
		int idx = model.getPlayers().indexOf(model.getCurrentPlayer());
		if ((idx+1)>=model.getPlayers().size()){
			model.setCurrentPlayer(model.getPlayers().get(0));
			currentPlayerTurn();
		} else {
			model.setCurrentPlayer(model.getPlayers().get(idx+1)); 
			currentPlayerTurn();
			}
	}
	/**
	 * adds quit listener to the GUI menu in view and responds to it by exiting
	 * game via a method int the view
	 * */
	public void addQuitMenuListener() {
		view.addQuitMenuListener(e -> {
			view.quit();
		});
	}
	/**
	 * creates a show hand listener in the GUI menu and responds by creating a 
	 * handpopup object which displays the currentplayers hand
	 * */
	public void addShowHandMenuListener() {
		view.addShowHandMenuListener(e -> {
			HandPopup h = new HandPopup(model);
		});
	}
	/**
	 * creates a discard listener in the GUI menu and responds by creating a 
	 * discardpopup object which displays the discarded hand
	 * */
	public void addShowDicardMenuListener() {
		view.addShowDiscardMenuListener(e -> {
			DiscardPopup d = new DiscardPopup(model);
		});
	}
	/**
	 * creates a done button listener in the view and responds by getting 
	 * the character name and weapon name from the suggestpopup obj and sends 
	 * it to the model which checks it against the players' hands
	 * advises players to check the menu option 'discarded hand' before ending their turn
	 * */
	public void addDoneButtonListener(){
		suggest.addDoneButtonListener(e -> {
			try{
				model.checkSuggestion(suggest.getChar(), suggest.getWeapon());
			} catch(NullPointerException nullsug){
				view.invalidPlayerMessage();
				return;
			}
			suggest.closeWindow();
			view.discardMessage();
			view.removeSuggest();
			view.redraw();
		});
	}
	/**
	 * creates a accuse done button listener in the view and responds by getting 
	 * the character name, room name and weapon name from the accusepopup obj and sends 
	 * it to the model which checks it against the solution
	 * advises player of win or loss and deals with the former by ending the game, deals with the latter
	 * by removing the player from the game and adding their hand to the discard hand
	 * */
	public void addAccuseDoneButtonListener(){
		accuse.addAccuseDoneButtonListener(e -> {
			boolean hasWon=false;
			try{
				hasWon = model.checkAccusaction(accuse.getChar(), accuse.getWeapon(), accuse.getRoom());
			} catch (NullPointerException nullacc){
				view.invalidPlayerMessage();
				return;
			}
			//player has won, let them know!
			if (hasWon == true) {
				finished = true;
				view.wonGame();
				accuse.closeWindow();
				view.quit();
			}
			//player has lost, delete them from the game
			else {
				view.lostGame();
				accuse.closeWindow();
				model.removePlayerFromGame();
				endTurn();
			}
		});
	}
	/**
	 * adds mouselistener to the grid (middletop) panel in the view
	 * responds to user clicking an area on the board
	 * checks possible to move by calling trymove(Location l) on the location clicked
	 * updates users location or advises them of invalid movement attempt
	 * */
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
					//animation logic? 
					model.getCurrentPlayer().updateLocation(l);
					Square moved = model.getSquares()[l.getX()][l.getY()];
					if (moved instanceof RoomSquare && view.getSuggest()==null){
						view.addSuggestButton();
						addSuggestButtonListener();
					}
					currentRoll=0;
					view.removeDice();
					view.redraw();
				} else {view.removeSuggest(); view.invalidMove(); addGridMouseListener();} 
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
	
	/**
	 * add number of players pop up created and asks user to input a number between 3 and 6
	 * throws error if string entered or number not within bounds
	 * then sets up each player 
	 * */
	public void addNumberOfPlayersListener() {
		pop.addNumberOfPlayersListener(e -> {
			String players = pop.getNumOfPlayers();
			try{
				numOfPlayers = Integer.parseInt(players);
			}
			catch (NumberFormatException num){ 
				view.mustEnterNumberMessage();
				return;
			}
			if (numOfPlayers < 3 || numOfPlayers > 6){
				view.incorrectPlayersMessage();
				return;
			}
			pop.setupEachPlayer();
			count++;
			addPlayerInfoListener();
		});
	}
	/**
	 * up until the entered number of players, get information from player and then create them 
	 * in the model
	 * information needed: character name (eg miss scarlett) and player name (eg patrick)
	 * closes window and beings the game by calling start
	 * */
	public void addPlayerInfoListener() {
		pop.addPlayerInfoListener(e -> {
			if (count >= numOfPlayers) {
				//when the last player is done setting up, start the game
				String playerName = pop.getPlayerName();
				String charName = pop.getSelectedButtonText();
				try {
					this.model.createPlayer(playerName, charName, count);
				}
				catch (NullPointerException nullpoint){
					view.invalidPlayerMessage();
					return;
				}
				pop.closeWindow();
				start();
				return;
			}
			String playerName = pop.getPlayerName();
			String charName = pop.getSelectedButtonText();
			try {
				this.model.createPlayer(playerName, charName, count);
			}
			catch (NullPointerException nullpoint){
				view.invalidPlayerMessage();
				return;
			}
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

	/**
	 * Starts the game. Gets the selected colorscheme, creates the board
	 * Adds button listeners and begins play 
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
	/**
	 * begin play got all the players, make the solution and deal the cards
	 * first players turn
	 * */
	private void play() {
		model.dealCards();
		model.setCurrentPlayer(model.getPlayers().get(0));
		firstTurn = true;
		currentPlayerTurn();
	}
	/**
	 * creates two die objects and adds images of their value to the view to let the 
	 * player know how far they can move
	 * */
	private void rollDice(){
		Die d1 = new Die();
		Die d2 = new Die();
		currentRoll = d1.getRoll()+d2.getRoll();
		view.addDiceToPane(d1, d2);
		view.redraw();
	}
	/**
	 * main logic of the current players turn
	 * checks there are enough players to play
	 * and that the current player is not eliminated
	 * if they are in a room show them the suggest button, if it is a corner room ask them
	 * if they want to move to the opposite room
	 * if they dont/arent in a corner room (or any room) roll the dice
	 * */
	public void currentPlayerTurn(){
		if (model.getPlayers().size()<2){view.endGameMessage(); view.quit();}
		if (!firstTurn) view.removeDice(); 
		view.removeSuggest();
		view.redraw();
		firstTurn = false;
		if (model.getCurrentPlayer().getEliminated()) endTurn();
		Square local = model.getSquares()[model.getCurrentPlayer().getLocation().getX()][model.getCurrentPlayer().getLocation().getY()];
		if (local instanceof RoomSquare){
			view.addSuggestButton();
			addSuggestButtonListener();
			view.redraw();
			//check if corner square for stairways
			if (((RoomSquare)local).getStairs()){
				//am able to move via stairs
				if(view.moveDiagonal()==0){
					model.getCurrentPlayer().updateLocation(((RoomSquare)local).getOppLoc());
					view.removeSuggest();
					view.redraw();
					currentRoll=0;
				}else {rollDice();}
				} else {rollDice();}
		} 
		else {
			rollDice();
			}
		}
		
	}
	


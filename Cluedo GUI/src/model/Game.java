package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import astarpathfind.AStarPath;
import astarpathfind.pathNode;
import cards.Card;
import cards.Weapon;
import colorschemes.ColorScheme;
import colorschemes.Emo;
import controller.Controller;
import core.Board;
import core.Die;
import core.Player;
import squares.DoorSquare;
import squares.RoomSquare;
import squares.Square;
import view.View;
import cards.Character;
import cards.Room;

/**
 * Represents a game of cluedo. This contains a back-end representation of the
 * game board, it holds the list of the players, it controls who turn it is and
 * provides methods to control the movement of the players.
 *
 * @author
 *
 */
public class Game {
	/**
	 * Game Constructor.
	 */
	public Game() {
		Model m = new Model();
		Controller c = new Controller(m);
		View v = new View(c, m.squares, m.getColorScheme());
	}

	/*	
		*//**
			 * Lets the current player suggest a solution when entering a room
			 */
	/*
	 * public void suggest(){ List<Card>suggestion = new ArrayList<>(); Card
	 * room = new
	 * Room(this.squares[currentPlayer.getLocation().getX()][currentPlayer.
	 * getLocation().getY()].getName()); Card character, weapon; //character
	 * selection
	 * System.out.println("Please suggest a character using the # value: "); for
	 * (int i = 0; i<this.characters.length; i++){
	 * System.out.println(i+" : "+this.characters[i]); } int index =
	 * TextInterface.inputNumber("What character would you like to suggest? ");
	 * while (index<0 || index>5){ index =
	 * TextInterface.inputNumber("Invalid index please try again: "); }
	 * character = new Character(this.characters[index]); //weapon selection
	 * System.out.println("Please suggest a weapon using the # value: "); for
	 * (int i = 0; i<this.weapons.length; i++){
	 * System.out.println(i+" : "+this.weapons[i]); } int indx =
	 * TextInterface.inputNumber("What weapon would you like to suggest? ");
	 * while (indx<0 || indx>5){ indx =
	 * TextInterface.inputNumber("Invalid index please try again: "); } weapon =
	 * new Weapon(this.weapons[indx]); suggestion.add(room);
	 * suggestion.add(character); suggestion.add(weapon); //go through players
	 * and if one or more of the suggestion are contained in hand // at least
	 * one MUST be discarded to ruledOut and removed from hand
	 * 
	 * System.out.println("YOUR SUGGESTION IS: "); for (Card c : suggestion){
	 * System.out.print(" "+c.getName()); } System.out.println("");
	 * List<Card>result=new ArrayList<Card>(); for (Player p : this.players){ if
	 * (!p.equals(currentPlayer)){ //check hand result =
	 * p.checkHand(suggestion); if (!result.isEmpty()){
	 * System.out.println(p.getName()
	 * +" must choose one of the following to discard: "); for(int i=0;
	 * i<result.size(); i++){
	 * System.out.println(i+" : "+result.get(i).getName()); } int removeidx =
	 * TextInterface.inputNumber("What card would you like to remove? "); while
	 * (removeidx < 0 || removeidx >result.size()){ removeidx =
	 * TextInterface.inputNumber("Invalid index please try again: "); }
	 * 
	 * Card toDiscard = result.remove(removeidx);
	 * System.out.println(toDiscard.getName()+" gonna be removed.");
	 * this.ruledOut.add(toDiscard); p.removeFromHand(toDiscard); } } }
	 * 
	 * String accuse =
	 * TextInterface.inputString("Would you like to accuse Y/N ?").toUpperCase()
	 * ; while (!accuse.equals("Y") && !accuse.equals("N")){ accuse =
	 * TextInterface.inputString("Please enter 'Y' or 'N' : ").toUpperCase(); }
	 * if(accuse.equals("Y")){ accuse(); } else {
	 * System.out.println("-------------END OF TURN-----------------"); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 *//**
		 * Lets current player accuse after a suggestion
		 */
	/*
	 * public void accuse(){ Card character; Card weapon; Card room;
	 * 
	 * boolean ch=false; boolean we=false; boolean ro=false;
	 * 
	 * //character selection
	 * System.out.println("Accuse a character using the # value: "); for (int i
	 * = 0; i<this.characters.length; i++){
	 * System.out.println(i+" : "+this.characters[i]); } int index =
	 * TextInterface.inputNumber("What character would you like to accuse? ");
	 * while (index<0 || index>5){ index =
	 * TextInterface.inputNumber("Invalid index please try again: "); }
	 * character = new Character(this.characters[index]); //weapon selection
	 * System.out.println("Accuse a weapon using the # value: "); for (int i =
	 * 0; i<this.weapons.length; i++){
	 * System.out.println(i+" : "+this.weapons[i]); } int indx =
	 * TextInterface.inputNumber("What weapon would you like to accuse? ");
	 * while (indx<0 || indx>5){ indx =
	 * TextInterface.inputNumber("Invalid index please try again: "); } weapon =
	 * new Weapon(this.weapons[indx]); //room selection
	 * System.out.println("Accuse a room using the # value: "); for (int i = 0;
	 * i<this.rooms.length; i++){ System.out.println(i+" : "+this.rooms[i]); }
	 * int roomindx =
	 * TextInterface.inputNumber("What room would you like to accuse? "); while
	 * (roomindx<0 || roomindx>8){ roomindx =
	 * TextInterface.inputNumber("Invalid index please try again: "); } room =
	 * new Room(this.rooms[roomindx]);
	 * 
	 * System.out.println("YOUR ACCUSATION: "+character.getName()+" "+weapon.
	 * getName()+" "+room.getName()); System.out.println("");
	 * 
	 * for (Card card : this.solution){ if (card instanceof Character){
	 * if(card.equals(character)){ ch=true; } }else if (card instanceof Weapon){
	 * if(card.equals(weapon)){ we=true; } }else if (card instanceof Room){ if
	 * (card.equals(room)){ ro=true; } } }
	 * 
	 * if (!ch || !we || !ro){
	 * System.out.println("INCORRECT SOLUTION "+currentPlayer.getName()
	 * +" WILL BE ELIMINATED."); List <Card> fromEliminated =
	 * currentPlayer.setEliminated(true); for (Card c : fromEliminated){
	 * this.ruledOut.add(c); } this.players.remove(currentPlayer); } else{
	 * System.out.println("CORRECT SOLUTION "+currentPlayer.getName()+" WINS.");
	 * this.setGameFinished(true); }
	 * 
	 * 
	 * }
	 * 
	 *//**
		 * Sets up the move for the current player, how many squares they move
		 * is defined by the roll.
		 * 
		 * @param roll
		 */
	/*
	 * public void move(){ int roll = this.rollDice(); int numberX = 0; int
	 * numberY = 0; Location newLoc = currentPlayer.getLocation(); boolean moved
	 * = false; //Square [][] b = this.board.getBoard(); Square target=null;
	 * Square player = this.squares[newLoc.getX()][newLoc.getY()];
	 * 
	 * if (player instanceof RoomSquare){ if (((RoomSquare)
	 * player).getStairs()){ String stair =
	 * TextInterface.inputString("Would you like to move to "+((RoomSquare)
	 * player).getOpposite()+" via the stairs? Y/N").toUpperCase(); while
	 * (!stair.equals("Y") && !stair.equals("N")){stair =
	 * TextInterface.inputString("Please enter 'Y' or 'N' : ").toUpperCase();}
	 * if (stair.equals("Y")){ newLoc = ((RoomSquare)player).getOppLoc(); target
	 * = this.squares[newLoc.getX()][newLoc.getY()];
	 * currentPlayer.updateLocation(newLoc.getX(), newLoc.getY());
	 * System.out.println("You have moved to: "+this.squares[currentPlayer.
	 * getLocation().getX()][currentPlayer.getLocation().getY()].getName());
	 * moved = true; } else { System.out.println("You are allowed to move " +
	 * roll + " spaces!"); } } else {
	 * System.out.println("You are allowed to move " + roll + " spaces!"); } }
	 * else {System.out.println("You are allowed to move " + roll +
	 * " spaces!");}
	 * 
	 * while(!moved){ // player's requested position String letter =
	 * TextInterface.inputString("What letter would you like to move to? ").
	 * toUpperCase(); while (letter.equals("Z")){ letter =
	 * TextInterface.inputString("Z is out of bounds, please try again: ").
	 * toUpperCase(); } numberY =
	 * TextInterface.inputNumber("What number would you like to move to? ");
	 * while (numberY < 0 || numberY > 24){ numberY =
	 * TextInterface.inputNumber(numberY+" is out of bounds, please try again: "
	 * ); }
	 * 
	 * // position -> location for(int i = 1; i <
	 * currentPlayer.getAlpha().length; i++){
	 * if(currentPlayer.getAlpha()[i].equals(letter)){ numberX = i; newLoc = new
	 * Location(numberX, numberY); } }
	 * 
	 * 
	 * // VALID MOVE CHECK: is that position the correct amount of squares away?
	 * (a* algorithm) target = this.squares[newLoc.getX()][newLoc.getY()];
	 * 
	 * if (!target.getName().equals("Wall")){ // a star search from here
	 * pathNode start = new
	 * pathNode(this.squares[currentPlayer.getLocation().getX()][currentPlayer.
	 * getLocation().getY()], this); pathNode end = new pathNode(target,this);
	 * AStarPath aStar = new AStarPath(start, end); int numMoves =
	 * aStar.findPath();
	 * 
	 * if (numMoves <= roll){ moved = true;
	 * currentPlayer.updateLocation(numberX, numberY); System.out.println("");
	 * System.out.println("You have moved to position "+currentPlayer.
	 * getPlayersPosition());
	 * System.out.println("You are in the "+target.getName());
	 * System.out.println(""); } else { System.out.println("");
	 * System.out.println("You cannot move to that location, try again:"); } }
	 * 
	 * } if (moved && target != null){ // checking if new Location is a room
	 * if(target instanceof RoomSquare || target instanceof DoorSquare){ String
	 * YN =
	 * TextInterface.inputString("Would you like to suggest Y/N ?").toUpperCase(
	 * ); while (!YN.equals("Y") && !YN.equals("N")){ YN =
	 * TextInterface.inputString("Please enter 'Y' or 'N' : ").toUpperCase(); }
	 * if(YN.equals("Y")){ suggest(); } else if (YN.equals("N")){ String accuse
	 * =
	 * TextInterface.inputString("Would you like to accuse Y/N ?").toUpperCase()
	 * ; while (!accuse.equals("Y") && !accuse.equals("N")){ accuse =
	 * TextInterface.inputString("Please enter 'Y' or 'N' : ").toUpperCase(); }
	 * if(accuse.equals("Y")){ accuse(); } else {
	 * System.out.println("-------------END OF TURN-----------------"); } }
	 * 
	 * } else { System.out.println("-------------END OF TURN-----------------");
	 * } }
	 * 
	 * 
	 * }
	 * 
	 *//**
		 * provides useful information to the currentPlayer of the game.
		 */
	/*
	 * public void currentPlayerTurn(){ Location play =
	 * this.currentPlayer.getLocation(); Square squareType =
	 * this.squares[play.getX()][play.getY()];
	 * 
	 * System.out.println("");
	 * 
	 * // Prints CurrentPlayer's Turn System.out.println(currentPlayer.getName()
	 * + "'s turn. " + "(Player " + currentPlayer.getId() + ")"); // Prints
	 * CurrentPlayer's Location
	 * System.out.println("You are currently on position " +
	 * currentPlayer.getPlayersPosition());
	 * System.out.println("You are in the "+squareType.getName());
	 * 
	 * // Prints CurrentPlayer's Cards currentPlayer.showHand();
	 * 
	 * // Print discarded cards if there are any if (this.ruledOut.size()!=0){
	 * this.printRuledOut(); }
	 * 
	 * this.move(); }
	 * 
	 *//**
		 * creates the weapons, characters and rooms. adds all the cards to a
		 * list. calls methods needed for game to be initialised.
		 */
	/*
	 * public void intialise() { // make the character + room + weapon cards //
	 * add them in to collection of Card objects for (int i = 0; i < 6; i++) {
	 * this.allCards.add(new Weapon(weapons[i])); this.allCards.add(new
	 * Character(characters[i])); } for (int j = 0; j < 9; j++) {
	 * this.allCards.add(new Room(rooms[j])); } }
	 * 
	 * 
	 *//**
		 * makes the solution. randomly deals the cards out to the players.
		 * 
		 * @param weapons
		 * @param characters
		 * @param rooms
		 */
	/*
	 * public void dealCards() { // shuffle cards + get solution from arrays
	 * passed in // adds random weapon/character/room card to solution int
	 * wepIdx = new Random().nextInt(weapons.length); String solWeapon =
	 * (weapons[wepIdx]); this.solution.add(new Weapon(solWeapon)); int charIdx
	 * = new Random().nextInt(characters.length); String solCharacter =
	 * (characters[charIdx]); this.solution.add(new Character(solCharacter));
	 * int romIdx = new Random().nextInt(rooms.length); String solRoom =
	 * (rooms[romIdx]); this.solution.add(new Room(solRoom));
	 * 
	 * // removes solution cards from allCards Iterator<Card> it =
	 * allCards.iterator(); while (it.hasNext()) { Card c = it.next(); if
	 * (c.getName().equals(solWeapon)) { it.remove(); } if
	 * (c.getName().equals(solCharacter)){ it.remove(); } if
	 * (c.getName().equals(solRoom)){ it.remove(); } }
	 * 
	 * // makes a copy of the cards. // deals out the cards randomly until they
	 * are all gone. ruledOut = new ArrayList<Card>(); for(Card c : allCards){
	 * ruledOut.add(c); } // number of players/21 int numCards =
	 * allCards.size()%players.size(); int bound; if (numCards>0){bound =
	 * numCards * players.size();} else {bound = ruledOut.size();}
	 * 
	 * for(int i = 0; i < bound-1 ; i++){ for(Player p : players){
	 * if(ruledOut.size() > numCards){ int cardIdx = new
	 * Random().nextInt(ruledOut.size()); Card c = ruledOut.get(cardIdx);
	 * p.addCard(c); ruledOut.remove(cardIdx); } } } }
	 * 
	 *//**
		 * loops through each player while the game is not finished.
		 */
	/*
	 * public void playGame(){ while(!finished){ if (players.size()<2){break;}
	 * for(int i = 0; i != players.size(); i++){ if (players.size()<2){break;}
	 * currentPlayer = players.get(i); if(!currentPlayer.getEliminated()){
	 * currentPlayerTurn(); } if(finished == true){ break; } } } endGame(); }
	 * 
	 * public void endGame(){ System.out.println("Game Over"); }
	 * 
	 * 
	 * 
	 *//**
		 * places players in random rooms at start of game
		 * 
		 * @param rooms
		 */
	/*
	 * public void placePlayers(){ // make a copy ArrayList<String> roomsCopy =
	 * new ArrayList<String>(Arrays.asList(rooms)); Location loc = new
	 * Location(0, 0); int startSize = roomsCopy.size(); while(roomsCopy.size()
	 * >= (startSize - (players.size() - 1))){ for(Player p : this.players){ int
	 * romIdx = new Random().nextInt(roomsCopy.size()); String playerRoom =
	 * roomsCopy.get(romIdx); // room name if(playerRoom == null){ break; } //
	 * location switch(playerRoom){ case "KITCHEN": loc = new Location(4 , 4);
	 * break; case "BALLROOM": loc = new Location(13 , 4); break; case
	 * "CONSERVATORY": loc = new Location(22 , 3); break; case "DINING": loc =
	 * new Location(4 , 13); break; case "GAMESROOM": loc = new Location(23 ,
	 * 10); break; case "LIBRARY": loc = new Location(23 , 16); break; case
	 * "LOUNGE": loc = new Location(4 , 22); break; case "BEDROOM": loc = new
	 * Location(13 , 23); break; case "STUDY": loc = new Location(22 , 23);
	 * break; }
	 * 
	 * p.setStartLoc(loc.getX(), loc.getY()); roomsCopy.remove(romIdx); } } }
	 * 
	 * 
	 *//**
		 * sets finished
		 */
	/*
	 * public boolean setGameFinished(boolean b){ return finished; }
	 * 
	 *//**
		 * Get the current game board.
		 *
		 * @return
		 */
	/*
	 * public Board getBoard() { return board; }
	 * 
	 *//**
		 * Get the current game board.
		 *
		 * @return
		 */
	/*
	 * public ArrayList <Player> getPlayers() { return this.players; }
	 * 
	 *//**
		 * returns the current player in the game
		 * 
		 * @return
		 */
	/*
	 * public Player getCurrentPlayer(){ return currentPlayer; }
	 * 
	 *//**
		 * returns the currently discarded cards
		 * 
		 * @return
		 */
	/*
	 * public ArrayList <Card> getRuledOut(){ return this.ruledOut; }
	 * 
	 * public ArrayList <Card> getSolution(){ return this.solution; }
	 * 
	 *//**
		 * prints the discarded hand
		 * 
		 * @void
		 */

	/*
	 * public void printRuledOut(){ System.out.println("Discarded: "); for(Card
	 * c : this.ruledOut){ System.out.println(c.getName()); } }
	 * 
	 *//**
		 * sets the players
		 * 
		 * @param players
		 *//*
		 * public void setPlayers(ArrayList<Player> players) { this.players =
		 * players; }
		 */

	public static void main(String args[]) {
		new Game();
	}
	
}

package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import astarpathfind.AStarPath;
import astarpathfind.pathNode;
import cards.Card;
import cards.Room;
import cards.Weapon;
import core.Game;
import core.Player;
import squares.Square;

public class CluedoTests {
	
	private MockSetup mock = new MockSetup();
	
	/**
	 * test adding arraylist of players to the game
	 */
	@Test
	public void test_1(){
		Game game = new Game();
		game.setPlayers(mock.setMockPlayers());
		assertEquals(game.getPlayers().size(), mock.setMockPlayers().size());
	}
	/**
	 * test making the solution
	 */
	@Test
	public void test_2(){
		Game game = new Game();
		game.setPlayers(mock.setMockPlayers());
		game.intialise();
		game.dealCards();
		assert(game.getSolution().size()==3);
		for (Card c : game.getSolution()){
			if (c instanceof Room){ assert(mock.rooms().contains(c.getName()));}
			else if (c instanceof Weapon){ assert(mock.weapons().contains(c.getName()));}
			else {assert(mock.characters().contains(c.getName()));}
		}
	}
	
	/**
	 * test that in the event of an uneven number of players
	 * the leftover cards are added to the "discard" pile
	 * checks players hand isn't empty
	 */
	@Test
	public void test_3(){
		Game game= new Game();
		ArrayList<Player> uneven = mock.setMockPlayers(); 
		uneven.add(new Player(4, "Brooke"));
		game.setPlayers(uneven);
		game.intialise();
		game.dealCards();
		assertFalse(game.getRuledOut().isEmpty());
		for (Player p : game.getPlayers()){
			assertFalse(p.getHand().isEmpty());
		}
	}
	
	/**
	 *	place players on the board
	 *	assert their locations are valid
	 */
	@Test
	public void test_4(){
		Game game = new Game();
		ArrayList <Player> players = mock.setMockPlayers();
		game.setPlayers(players);
		game.intialise();
		game.placePlayers();
		Square [][] squares = game.getBoard().getSquares();
		for (Player p : game.getPlayers()){
			assert(p.getLocation() != null);
			Square current = squares[p.getLocation().getX()][p.getLocation().getY()];
			assert(mock.rooms().contains(current.getName()));
		}	
	}
	
	/**
	 * test that can't play game if 
	 * game has finished
	 */
	@Test
	public void test_5(){
		Game game = new Game();
		game.setPlayers(mock.setMockPlayers());
		game.intialise();
		game.setGameFinished(true);
		assert(game.getCurrentPlayer()==null);
	}
	
	/**
	 * simulate and test the path finding algorithm with one player
	 * aStar returns -1 integer as a fail case
	 */
	@Test 
	public void test_6(){
		Game game = new Game();
		Player p = mock.setMockPlayer();
		p.updateLocation(5, 5);
		
		Square pSQ = game.getBoard().getSquares()[5][5];
		Square tSQ = game.getBoard().getSquares()[22][16];
		
		pathNode start = new pathNode(pSQ, game);
		pathNode end = new pathNode(tSQ, game);
		
		AStarPath aStar = new AStarPath(start, end);
		
		int roll = 100;
		int pathLength = aStar.findPath();
		
		assert(pathLength>0);
		assert(pathLength<roll);
	}
	
	
}

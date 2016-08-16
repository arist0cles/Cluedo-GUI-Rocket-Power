package astarpathfind;

import java.util.ArrayList;
import java.util.List;

import core.Board;
import model.Game;
import squares.Square;

public class pathNode {
	private Square sqaure;
	private pathNode parent;
	private double costToHere, costToGoal, totalCost;
	private Game game;
	
	public pathNode(Square square, Game game){
		this.game=game; this.sqaure=square;
	}
	
	public List<pathNode> findNeighbours(){
		List<pathNode> neighbours = new ArrayList<>();
		Board board = this.game.getBoard();
		Square [][] squares = board.getSquares();
		
		if (this.sqaure.getLoc().getX()-1 >= 0){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()-1][this.sqaure.getLoc().getY()],this.game));
		}
		if (this.sqaure.getLoc().getX()+1 < 25){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()+1][this.sqaure.getLoc().getY()],this.game));
		}
		if (this.sqaure.getLoc().getY()-1 >= 0){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()][this.sqaure.getLoc().getY()-1],this.game));
		}
		if (this.sqaure.getLoc().getY()+1 < 25){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()][this.sqaure.getLoc().getY()+1],this.game));
		}
		return neighbours;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sqaure == null) ? 0 : sqaure.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		pathNode other = (pathNode) obj;
		if (sqaure == null) {
			if (other.sqaure != null)
				return false;
		} else if (!sqaure.equals(other.sqaure))
			return false;
		return true;
	}

	public Square getSqaure() {
		return sqaure;
	}

	public void setSqaure(Square sqaure) {
		this.sqaure = sqaure;
	}

	public pathNode getParent() {
		return parent;
	}

	public void setParent(pathNode parent) {
		this.parent = parent;
	}

	public double getCostToHere() {
		return costToHere;
	}

	public void setCostToHere(double costToHere) {
		this.costToHere = costToHere;
	}

	public double getCostToGoal() {
		return costToGoal;
	}

	public void setCostToGoal(double costToGoal) {
		this.costToGoal = costToGoal;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	

}

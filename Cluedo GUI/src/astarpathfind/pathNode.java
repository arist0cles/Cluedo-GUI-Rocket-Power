package astarpathfind;

import java.util.ArrayList;
import java.util.List;

import model.Model;
import squares.Square;


/**
 * This class represents a single pathNode object, these objects are used only by the view in the controller 
 * when determining whether the user can move to the locaiton they have clicked on or not
 * 
 * */
public class pathNode {
	private Square sqaure;
	private pathNode parent;
	private double costToHere, costToGoal, totalCost;
	private Model model;
	
	public pathNode(Square square, Model model){
		this.model=model; this.sqaure=square;
	}
	
	/**
	 * 
	 * */
	
	public List<pathNode> findNeighbours(){
		List<pathNode> neighbours = new ArrayList<>();
		Square [][] squares = model.getSquares();
		
		if (this.sqaure.getLoc().getX()-1 >= 0){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()-1][this.sqaure.getLoc().getY()],this.model));
		}
		if (this.sqaure.getLoc().getX()+1 < 25){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()+1][this.sqaure.getLoc().getY()],this.model));
		}
		if (this.sqaure.getLoc().getY()-1 >= 0){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()][this.sqaure.getLoc().getY()-1],this.model));
		}
		if (this.sqaure.getLoc().getY()+1 < 25){
			neighbours.add(new pathNode(squares[this.sqaure.getLoc().getX()][this.sqaure.getLoc().getY()+1],this.model));
		}
		return neighbours;
	}
	
	

	/**
	 * generated hashcode method necessary for AStar
	 * @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sqaure == null) ? 0 : sqaure.hashCode());
		return result;
	}

	/** 
	 * generated equals method
	 * @param obj
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
	/**
	 * returns the current square
	 * @return square
	 * */
	public Square getSqaure() {
		return sqaure;
	}
	/**
	 * sets the square
	 * @param square
	 * */
	public void setSqaure(Square sqaure) {
		this.sqaure = sqaure;
	}
	/**
	 * returns the parent pathnode
	 * @return parent
	 * */
	public pathNode getParent() {
		return parent;
	}
	/**
	 * sets the this parent
	 * @param parent
	 * */
	public void setParent(pathNode parent) {
		this.parent = parent;
	}
	/**
	 * return the cost to this node
	 * @return costToHere
	 * */
	public double getCostToHere() {
		return costToHere;
	}
	/**
	 * sets the costToHere
	 * @param costToHere
	 * */
	public void setCostToHere(double costToHere) {
		this.costToHere = costToHere;
	}
	/**
	 * return the cost to this goal
	 * @return costToGoal
	 * */
	public double getCostToGoal() {
		return costToGoal;
	}
	/**
	 * sets the cost to the goal
	 * @param costToGoal
	 * */
	public void setCostToGoal(double costToGoal) {
		this.costToGoal = costToGoal;
	}
	/**
	 * return the total cost to this node
	 * @return totalCost
	 * */
	public double getTotalCost() {
		return totalCost;
	}
	/**
	 * sets the total cost
	 * @param totalCost
	 * */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}	

}

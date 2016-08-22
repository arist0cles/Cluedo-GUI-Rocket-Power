package astarpathfind;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import squares.DoorSquare;
import squares.HallwaySquare;
import squares.RoomSquare;

/**
 * This class represents an AStar object which 
 * determines the cost of the path between two pathNode objects
 * in terms of location coordinates stored within those pathNodes
 * 
 * @author kirita escott and patrick ryan
 * */

public class AStarPath {
	private PriorityQueue<pathNode> fringe;
	private Set<pathNode>visited;
	private pathNode start, end;
	
	
	
	public AStarPath(pathNode start, pathNode end){
		this.start=start; 
		this.end=end;
		this.visited = new HashSet<>();
		
		fringe = new PriorityQueue<>(25, new Comparator<pathNode>() {
			@Override
			public int compare(pathNode p1, pathNode p2) {
				if(p1.getTotalCost()<p2.getTotalCost())return -1;
				else if (p1.getTotalCost()>p2.getTotalCost())return 1;
				return 0;
			}
		});

		start.setParent(null);
		start.setCostToHere(0);
		start.setCostToGoal(estimate(start, end));
	}
	
	/**
	 * determines the path (if any) and returns the integer cost
	 * of that path, if no path exists -1 is returned
	 * @return totalcost
	 * */
	public int findPath(){
		//add start node to the fringe
		fringe.add(this.start);
		while (!fringe.isEmpty()){
			//get node
			pathNode current = fringe.poll();
			//add current node to the visited set
			if (!visited.contains(current)){
				visited.add(current);
			}
			//found the end node
			if (current.equals(this.end)){
				int totalcost = (int) current.getTotalCost();
				return totalcost;
			}
			//go through the current nodes neighbours
			for (pathNode neigh : current.findNeighbours()){
				if (!visited.contains(neigh)){
					neigh.setParent(current);
					if (neigh.getSqaure() instanceof HallwaySquare || neigh.getSqaure() instanceof DoorSquare || neigh.getSqaure() instanceof RoomSquare){
						neigh.setCostToHere(current.getCostToHere() + 1);
						neigh.setTotalCost(neigh.getCostToHere() + estimate(neigh, this.end));
						fringe.add(neigh);
					}
				}
			}
		}
		return -1; //fail case
	}
	/**
	 * returns the estimated cost between start and end node
	 * */
	private double estimate(pathNode start, pathNode end) {
		return Math.abs(start.getSqaure().getLoc().getX() - end.getSqaure().getLoc().getX()) +
				Math.abs(start.getSqaure().getLoc().getX() - end.getSqaure().getLoc().getX());
	}
	
}

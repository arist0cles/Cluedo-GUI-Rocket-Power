package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import core.Location;
import squares.DoorSquare;
import squares.HallwaySquare;
import squares.RoomSquare;
import squares.Square;
import squares.WallSquare;


/**
 * Represents the Cluedo Board, which is made up from 25x25 Square objects.
 * The board simply provides access functions to determine the location object
 * at a given position on the board.
 *
 * @author Kirita Escott
 */
public class Board {
	//need a 2D array of Square objects 25 x 25 
	private Square [][] squares = new Square[25][25];
	
	public Board(){
		File file = new File("board.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			int x=-1; 
			int y=0;
			while (scan.hasNextLine()){
				String line = scan.nextLine();
				String[] ch = line.split(",");
				for (int i = 0; i < ch.length; i++){
					if (x==24){x = 0;}
					else {x++;}
					Square sq;
					if (ch[i].equals("K") || ch[i].equals("B") || ch[i].equals("C") || ch[i].equals("d")
							|| ch[i].equals("G") || ch[i].equals("L") || ch[i].equals("l") || ch[i].equals("b") || 
							ch[i].equals("S")){
						//all types of room
						sq = new RoomSquare(ch[i], new Location(x,y));
						this.squares[x][y] = sq;
					}
					else if (ch[i].equals("D")){
						//door
						sq = new DoorSquare(ch[i], new Location(x,y));
						this.squares[x][y] = sq;
					}
					else if (ch[i].equals("H")){
						//hallway
						sq = new HallwaySquare(ch[i], new Location(x,y));
						this.squares[x][y] = sq;
					}
					else if (ch[i].equals("W")){
						//wall - should ignore these when moving around the board
						sq = new WallSquare(ch[i], new Location(x,y));
						this.squares[x][y] = sq;
					}
					else {
						//Must be an error in text file
						System.out.println("Incorrect board.txt");
					}
				}
				y++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
	}
	
	public Square [][] getBoard(){return this.squares;}
	
	
	public void draw(){
		System.out.println("   A B C D E F G H I J K L M N O P Q R S T U V W X Y");
		
		for (int row=0; row<25; row++){
			if (row < 10) System.out.print(row+" ");
			else System.out.print(row);
			
			for (int col=0; col<25; col++){
				Square s = this.squares[col][row];
				if (col==24){
					s.draw();
					System.out.println("");
				}
				else {
					s.draw();
				}
			}
			
		}
		
		System.out.println("");
		System.out.println("______________");
	}
}

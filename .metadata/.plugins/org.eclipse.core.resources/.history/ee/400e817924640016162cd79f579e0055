package core;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import colorschemes.ColorScheme;
import core.Location;
import squares.DoorSquare;
import squares.HallwaySquare;
import squares.RoomSquare;
import squares.Square;
import squares.WallSquare;

/**
 * Represents the Cluedo Board, which is made up from 25x25 Square objects. The
 * board simply provides access functions to determine the location object at a
 * given position on the board.
 *
 * @author Kirita Escott
 */
public class Board {
	// need a 2D array of Square objects 25 x 25
	private Square[][] squares = new Square[25][25];
	private ColorScheme scheme;

	public Board(ColorScheme scheme) {
		this.setScheme(scheme);
		File file = new File("board.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			int y = 0;
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] ch = line.split(",");
				for (int i = 0; i < ch.length; i++) {
					Square sq;
					if (ch[i].equals("K") || ch[i].equals("B") || ch[i].equals("C") || ch[i].equals("d")
							|| ch[i].equals("G") || ch[i].equals("L") || ch[i].equals("l") || ch[i].equals("b")
							|| ch[i].equals("S")) {
						// all types of room
						sq = new RoomSquare(ch[i], new Location(i, y), scheme.ROOM);
						this.squares[i][y] = sq;
					} else if (ch[i].equals("D")) {
						// door
						sq = new DoorSquare(ch[i], new Location(i, y), scheme.DOOR);
						this.squares[i][y] = sq;
					} else if (ch[i].equals("H")) {
						// hallway
						sq = new HallwaySquare(ch[i], new Location(i, y), scheme.HALLWAY);
						this.squares[i][y] = sq;
					} else if (ch[i].equals("W")) {
						// wall - should ignore these when moving around the
						// board
						sq = new WallSquare(ch[i], new Location(i, y), scheme.WALL);
						this.squares[i][y] = sq;
					} else {
						// Must be an error in text file
						System.out.println("Incorrect board.txt");
					}
				}
				y++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Square[][] getSquares() {
		return this.squares;
	}

	public ColorScheme getScheme() {
		return scheme;
	}

	public void setScheme(ColorScheme scheme) {
		this.scheme = scheme;
	}

	public void updateScheme() {
		File file = new File("board.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			int y = 0;
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] ch = line.split(",");
				for (int i = 0; i < ch.length; i++) {
					Square sq;
					if (ch[i].equals("K") || ch[i].equals("B") || ch[i].equals("C") || ch[i].equals("d")
							|| ch[i].equals("G") || ch[i].equals("L") || ch[i].equals("l") || ch[i].equals("b")
							|| ch[i].equals("S")) {
						// all types of room
						sq = new RoomSquare(ch[i], new Location(i, y), scheme.ROOM);
						this.squares[i][y] = sq;
					} else if (ch[i].equals("D")) {
						// door
						sq = new DoorSquare(ch[i], new Location(i, y), scheme.DOOR);
						this.squares[i][y] = sq;
					} else if (ch[i].equals("H")) {
						// hallway
						sq = new HallwaySquare(ch[i], new Location(i, y), scheme.HALLWAY);
						this.squares[i][y] = sq;
					} else if (ch[i].equals("W")) {
						// wall - should ignore these when moving around the
						// board
						sq = new WallSquare(ch[i], new Location(i, y), scheme.WALL);
						this.squares[i][y] = sq;
					} else {
						// Must be an error in text file
						System.out.println("Incorrect board.txt");
					}
				}
				y++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

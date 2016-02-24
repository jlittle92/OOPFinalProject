package edu.neumont.csc150.finalproject.littlej;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame{

	private static final int ROWS = 8, COLUMNS = 8;
	private static JButton [][] board = new JButton[ROWS][COLUMNS];
	
	public Board(HashMap<String, Piece> pieces){
		
	}
	
	public void resetBoard()
	{
		for (int row = 0; row < ROWS; row++){
			for (int col = 0; col < COLUMNS; col++){
				if((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0)){
					getBoard()[row][col] = EMPTY;
				}
				else{
					getBoard()[row][col] = OTHEREMPTY;
				}
			}
		}
	}
	
	public void printBoard()
	{
		System.out.println("+---+---+---+---+---+---+---+---+");
		for (int row = 0; row < ROWS; row++){
			System.out.print("| ");
			for (int col = 0; col < COLUMNS; col++){
				System.out.print(getBoard()[row][col] + " | ");
			}
			System.out.println();
			System.out.println("+---+---+---+---+---+---+---+---+");
		}
	}

	public static char[][] getBoard() {
		return board;
	}

	public static void setBoard(char[][] board) {
		Board.board = board;
	}

	public static char getEmpty() {
		return EMPTY;
	}
	
}

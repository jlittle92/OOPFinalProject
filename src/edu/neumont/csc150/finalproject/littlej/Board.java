package edu.neumont.csc150.finalproject.littlej;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame{

	private static final int ROWS = 8, COLUMNS = 8;
	private static final Color WHITE = Color.WHITE, BLACK = Color.BLACK;
	private JButton [][] board;
	private HashMap<Point, Piece> pieces;
	
	public Board(HashMap<Point, Piece> pieces){
		this.pieces = pieces;
		
		GridLayout grid = new GridLayout(ROWS, COLUMNS);
		
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle("Chess");
		frame.setLayout(grid);
		
		board = new Square[ROWS][COLUMNS];
		
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLUMNS; j++){
				board[i][j] = new Square(i, j);
				frame.add(board[i][j]);/*tried moving this below conditional, to no visible effect*/
				if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)){
					board[i][j].setBackground(WHITE);
					placePiece(i, j);
				}
				else{
					board[i][j].setBackground(BLACK);
					placePiece(i, j);
				}
			}
		}
		
		frame.setVisible(true);
	}
	
	//why aren't pieces showing up in UI...reference issue?
	private void placePiece(int i, int j){
		Point pos = new Point(i, j);
		/**/System.out.println("I got called");/*Does get called*/
		if(pieces.containsKey(pos)){
			board[i][j].setIcon(new ImageIcon(pieces.get(pos).getSource()));
			/*System.out.println(pieces.get(pos).getSource());*/
			//Pieces are in correct place!!!
			/**/System.out.println("I set an icon");/*Does set*/
			/**/System.out.println(board[i][j].getIcon().toString());/**/
			//Reference is correct, Icon still not adding to button
			/*If you click in black squares where pieces are supposed to be a dot appears in center of square, but not 
			in squares that aren't supposed to have pieces. This leads me to believe pieces not only exist, but exist
			in the correct positions. So why don't they show up?*/
		}
	}
}

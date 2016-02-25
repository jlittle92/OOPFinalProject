package edu.neumont.csc150.finalproject.littlej;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame{

	private static final int ROWS = 8, COLUMNS = 8;
	private static final Color WHITE = Color.WHITE, BLACK = Color.BLACK;
	private static JButton [][] board;
	
	public Board(){
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
				frame.add(board[i][j]);
				if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)){
					board[i][j].setBackground(WHITE);
				}
				else{
					board[i][j].setBackground(BLACK);
				}
			}
		}
		
		//Put pieces on the board squares
		
		frame.setVisible(true);
	}
	
}

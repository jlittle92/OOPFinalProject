package edu.neumont.csc150.finalproject.littlej;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame{

	private static final int ROWS = 8, COLUMNS = 8;
	private static final Color OFF_WHITE = Color.decode("#F2F2F2"), BLUE_ISH = Color.decode("#74C3F6");
	private JButton [][] board;
	private HashMap<Point, Piece> pieces;
	private boolean activeSquare;
	private Point sourceLocation, destinationLocation;
	
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
				final int finalI = i;
				final int finalJ = j;
				board[i][j] = new Square(i, j);
				board[i][j].addActionListener(new ActionListener() {          
				    public void actionPerformed(ActionEvent e) {
				    	Piece piece = ((Square) board[finalI][finalJ]).getPiece();
				    	if(piece != null){
				    		setActiveSquare(true);
				    		if(isActiveSquare()){
						    	sourceClick();
						    	setActiveSquare(false);
					        }
					        else{
					        	destinationClick(piece);
					        	setActiveSquare(true);
					        }
				    	}
				    }
				});
				placePiece(i, j); 
				if((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)){
					board[i][j].setBackground(OFF_WHITE);
				}
				else{
					board[i][j].setBackground(BLUE_ISH);
				}
				frame.add(board[i][j]);
			}
			
		}
		
		frame.setVisible(true);
	}
	
	private void placePiece(int i, int j){
		Point pos = new Point(i, j);
		if(pieces.containsKey(pos)){
			((Square) board[i][j]).setPiece(pieces.get(pos));
			Image img;
			try {
				img = ImageIO.read(getClass().getResource(pieces.get(pos).getSource().toString()));
				board[i][j].setIcon((new ImageIcon(img)));
				System.out.println(i + " " + j);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public boolean isActiveSquare() {
		return activeSquare;
	}

	public void setActiveSquare(boolean activeSquare) {
		this.activeSquare = activeSquare;
	}
	
	public void sourceClick() {
        System.out.println("first click" + getLocation());
        setSourceLocation(getLocation());
	}
	
	public void destinationClick(Piece piece) {
		setDestinationLocation(getLocation());
		piece.move(getSourceLocation(), getDestinationLocation());
		System.out.println("second click" + getLocation());
	}
	

	public Point getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(Point sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public Point getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Point destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
}

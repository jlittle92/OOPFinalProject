package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Square extends JButton{

	private Point location;
	private Piece piece;
	
	public Square(int x, int y) {
		setLocation(new Point(x, y));
		setPiece(null);	
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
}

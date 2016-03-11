package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Square extends JButton{

	private Point location;
	private Piece piece;
	private final int WHITE_PROMOTE = 0, BLACK_PROMOTE = 7;
	
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
	
	public boolean promote(Piece p, Point destination){
		if(p.getTeam() == Team.WHITE){
			if(destination.x == WHITE_PROMOTE){
				return true;
			}
		}
		else if(p.getTeam() == Team.BLACK){
			if(destination.x == BLACK_PROMOTE){
				return true;
			}
		}
		return false;
	}
}

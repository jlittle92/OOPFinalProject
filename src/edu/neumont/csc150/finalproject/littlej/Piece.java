package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public class Piece {

	private Point position;
	private boolean isKing;
	
	public Piece(Point p){
		setPosition(p);
		setKing(false);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public boolean isKing() {
		return isKing;
	}

	public void setKing(boolean isKing) {
		this.isKing = isKing;
	}
	
}

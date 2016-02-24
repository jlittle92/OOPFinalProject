package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public abstract class Piece extends ImageComponent implements Movable{

	private Point position;
	/*How to deal with check/mate?*/
	
	public Piece(Point p){
		setPosition(p);
	}
	
	public Piece(int x, int y){
		Point p = new Point(x, y);
		setPosition(p);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
}

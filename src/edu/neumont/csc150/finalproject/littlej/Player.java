package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public abstract class Player {

	private HashMap<Point, Piece> pieces;
	
	public Player(HashMap<Point, Piece> lunchables){
		pieces = lunchables;
	}
	
	public abstract void move();
	
	public abstract void checkValid();
	
	public abstract void checkForCheck();
	
	public abstract void checkWin();
	
	public abstract void checkDraw();
	
}

package edu.neumont.csc150.finalproject.littlej;

import java.util.HashMap;

public abstract class Player {

	private HashMap<String, Piece> pieces;
	
	public Player(HashMap<String, Piece> lunchables){
		pieces = lunchables;
	}
	
	public abstract void move();
	
	public abstract void checkValid();
	
	public abstract void checkForCheck();
	
	public abstract void checkWin();
	
	public abstract void checkDraw();
	
}

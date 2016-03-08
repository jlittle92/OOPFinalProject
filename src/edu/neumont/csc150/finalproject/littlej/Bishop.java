package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public class Bishop extends Piece{

	public Bishop(int x, int y, Team t, String pieceName) {
		super(x, y, t, pieceName);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if(source.x - destination.x == source.y - destination.y){
			return true;
		}
		else if(((source.x - destination.x) * -1) == source.y - destination.y){
			return true;
		}
		return false;
	}

}

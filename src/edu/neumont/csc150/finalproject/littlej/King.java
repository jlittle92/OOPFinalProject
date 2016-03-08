package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public class King extends Piece{

	public King(int x, int y, Team t, String pieceName) {
		super(x, y, t, pieceName);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if((source.x == destination.x) &&(source.y + 1 == destination.y || source.y - 1 == destination.y)){
			return true;
		}
		else if((source.y == destination.y) &&(source.x + 1 == destination.x || source.x - 1 == destination.x)){
			return true;
		}
		else if((source.x + 1 == destination.x && (source.y + 1 == destination.y || source.y - 1 == destination.y)) || (source.x - 1 == destination.x && (source.y + 1 == destination.y || source.y - 1 == destination.y))){
			return true;
		}
		return false;
	}

}

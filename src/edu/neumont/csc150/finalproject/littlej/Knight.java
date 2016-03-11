package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class Knight extends Piece{

	public Knight(int x, int y, Team t, PieceType type) {
		super(x, y, t, type);
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if((source.x - 2 == destination.x || source.x + 2 == destination.x) && (source.y - 1 == destination.y || source.y + 1 == destination.y)){
			return true;
		}
		else if((source.x - 1 == destination.x || source.x + 1 == destination.x) && (source.y - 2 == destination.y || source.y + 2 == destination.y)){
			return true;
		}
		return false; 
	}

	@Override
	public boolean inTheWay(HashMap<Point, Square> squares, Point kingPos) {
		return false;
	}

}

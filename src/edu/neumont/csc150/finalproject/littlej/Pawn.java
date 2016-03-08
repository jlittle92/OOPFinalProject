package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public class Pawn extends Piece{
	
	private boolean hasMoved;

	public Pawn(int x, int y, Team t, String pieceName) {
		super(x, y, t, pieceName);
		setHasMoved(false);
	}

	public void move() {
		
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if(!isHasMoved()){
			if((source.x - 2 == destination.x && source.y == destination.y) || (source.x - 1 == destination.x && source.y == destination.y)){
				setHasMoved(true);
				return true;
			}
			else{
				return false;
			}
		}
		else if(source.x == destination.x && source.y - 1 == destination.y){
			return true;
		}
		return false;
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}

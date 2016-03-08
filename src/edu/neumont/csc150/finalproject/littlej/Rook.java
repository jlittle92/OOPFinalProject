package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public class Rook extends Piece{

	private boolean hasMoved;

	public Rook(int x, int y, Team t, String pieceName) {
		super(x, y, t, pieceName);
		setHasMoved(false);
	}

	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if(!hasMoved){
			//castle eligible
			if(source.x == destination.x || source.y == destination.y){
				setHasMoved(true);
				return true;
			}
			return false;
		}
		else{
			if(source.x == destination.x || source.y == destination.y){
				return true;
			}
			return false;
		}
	}
	
	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}

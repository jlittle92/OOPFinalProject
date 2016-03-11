package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class King extends Piece implements Checkable{

	private boolean hasMoved;
	
	public King(int x, int y, Team t, PieceType type) {
		super(x, y, t, type);
		setHasMoved(false);
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if((source.x == destination.x) &&(source.y + 1 == destination.y || source.y - 1 == destination.y)){
			setHasMoved(true);
			return true;
		}
		else if((source.y == destination.y) &&(source.x + 1 == destination.x || source.x - 1 == destination.x)){
			setHasMoved(true);
			return true;
		}
		else if((source.x + 1 == destination.x && (source.y + 1 == destination.y || source.y - 1 == destination.y)) || (source.x - 1 == destination.x && (source.y + 1 == destination.y || source.y - 1 == destination.y))){
			setHasMoved(true);
			return true;
		}
		return false;
	}

	private boolean isHasMoved() {
		return hasMoved;
	}

	private void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public boolean isChecked(Point kingPos, HashMap<Point, Square> squares) {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Point temp = new Point(i, j);
				if(squares.get(temp).getIcon() != null){
					if(squares.get(temp).getPiece().getType() != PieceType.KING){
						if(squares.get(temp).getPiece().getTeam() != getTeam()){
							if(squares.get(temp).getPiece().killLogic(kingPos) && !squares.get(temp).getPiece().inTheWay(squares, kingPos)){
								System.out.println("Check!");
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isMate(Point kingPos, HashMap<Point, Square> squares) {
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				Point p = new Point(x, y);
				if(squares.get(p).getIcon() != null){
					if(squares.get(p).getPiece().getTeam() != getTeam()){
						if(isLegalForPiece(kingPos, p)){
							if(!isChecked(p, squares)){
								return false;
							}
						}
					}
				}
			}
		}
		System.out.println("Mate!");
		return true;
	}
	
	public boolean castle(Point source, Point destination){
		if(!isHasMoved() && source.x == destination.x && (source.y - 2 == destination.y || source.y + 2 == destination.y)){
			return true;
		}
		return false;
	}

	@Override
	public boolean inTheWay(HashMap<Point, Square> squares, Point kingPos) {
		return false;
	}

}

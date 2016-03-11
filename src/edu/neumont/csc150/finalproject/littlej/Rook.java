package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class Rook extends Piece{

	private boolean hasMoved;

	public Rook(int x, int y, Team t, PieceType type) {
		super(x, y, t, type);
		setHasMoved(false);
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if(source.x == destination.x || source.y == destination.y){
			setHasMoved(true);
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

	@Override
	public boolean killLogic(Point kingPos) {
		if(getPosition().x == kingPos.x || getPosition().y == kingPos.y){
			return true;
		}
		return false;
	}

	@Override
	public boolean inTheWay(HashMap<Point, Square> squares, Point kingPos) {
		Square s = null;
		if(isLegalForPiece(getLocation(), kingPos)){
			if(getPosition().x > kingPos.x){
				for(int x = getPosition().x - 1; x > kingPos.x; x--){
					s = squares.get(new Point(x, getPosition().y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
				}
			}
			else if(getPosition().x < kingPos.x){
				for(int x = getPosition().x + 1; x < kingPos.x; x++){
					s = squares.get(new Point(x, getPosition().y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
				}
			}
			else if(getPosition().y > kingPos.y){
				for(int y = getPosition().y - 1; y > kingPos.y; y--){
					s = squares.get(new Point(getPosition().x, y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
				}
			}
			else if(getPosition().y < kingPos.y){
				for(int y = getPosition().y + 1; y < kingPos.y; y++){
					s = squares.get(new Point(getPosition().x, y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
				}
			}
		}
		return false;
	}

}

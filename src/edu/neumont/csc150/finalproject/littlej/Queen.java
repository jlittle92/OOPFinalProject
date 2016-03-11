package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class Queen extends Piece{

	public Queen(int x, int y, Team t, PieceType type) {
		super(x, y, t, type);
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if(source.x == destination.x || source.y == destination.y){
			return true;
		}
		else if(source.x - destination.x == source.y - destination.y){
			return true;
		}
		else if(((source.x - destination.x) * -1) == source.y - destination.y){
			return true;
		}
		return false;
	}

	@Override
	public boolean inTheWay(HashMap<Point, Square> squares, Point kingPos) {
		Square s = null;
		int y = 0;
		if(isLegalForPiece(getPosition(), kingPos)){
			if(getPosition().x > kingPos.x && getPosition().y > kingPos.y){
				y = getPosition().y - 1;
				for(int x = getPosition().x - 1; x > kingPos.x; x--){
					s = squares.get(new Point(x, y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
					y--;
				}
			}
			else if(getPosition().x > kingPos.x && getPosition().y < kingPos.y){
				y = getPosition().y + 1;
				for(int x = getPosition().x - 1; x > kingPos.x; x--){
					s = squares.get(new Point(x, y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
					y++;
				}
			}
			else if(getPosition().x < kingPos.x && getPosition().y > kingPos.y){
				y = getPosition().y - 1;
				for(int x = getPosition().x + 1; x < kingPos.x; x++){
					s = squares.get(new Point(x, y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
					y--;
				}
			}
			else if(getPosition().x < kingPos.x && getPosition().y < kingPos.y){
				y = getPosition().y + 1;
				for(int x = getPosition().x + 1; x < kingPos.x; x++){
					s = squares.get(new Point(x, y));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
					y++;
				}
			}
			else if(getPosition().x > kingPos.x){
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
				for(int y1 = getPosition().y - 1; y1 > kingPos.y; y1--){
					s = squares.get(new Point(getPosition().x, y1));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
				}
			}
			else if(getPosition().y < kingPos.y){
				for(int y1 = getPosition().y + 1; y1 < kingPos.y; y1++){
					s = squares.get(new Point(getPosition().x, y1));
					if(s.getIcon() != null && s.getPiece().getType() != PieceType.KING){
						return true;
					}
				}
			}
		}
		return false;
	}

}

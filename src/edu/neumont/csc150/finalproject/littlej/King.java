package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;
/**
 * 
 * @author Joshua Little
 *
 */
public class King extends Piece implements Checkable{

	private boolean hasMoved;
	/**
	 * 
	 * @param x used as x coordinate to build Position of type Point
	 * @param y used as y coordinate to build Position of type Point
	 * @param t sets enumerated Team (WHITE OR BLACK)
	 * @param type sets enumerated piece type (i.e.- PAWN)
	 */
	public King(int x, int y, Team t, PieceType type) {
		super(x, y, t, type);
		setHasMoved(false);
	}

	/**
	 * @param source used as starting Point to determine legality of movement
	 * @param destination used as ending Point to determine legality of movement
	 */
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

	/**
	 * 
	 * @return tells whether or not the King in question has moved before
	 */
	private boolean isHasMoved() {
		return hasMoved;
	}

	/**
	 * 
	 * @param hasMoved set to true if King in question is moved
	 */
	private void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	/**
	 * @param kingPos used as destination Point for other pieces to tell whether or not they are checking the King
	 * @param squares HashMap of squares iterated through to find pieces potentially checking King in question
	 */
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

	/**
	 * @param kingPos used as starting point to determine legality of moving out of check
	 * @param squares used to determine if squares King can move to will still leave King in check
	 */
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
	
	/**
	 * 
	 * @param source used as Point to start castling movement for King in question
	 * @param destination used as Point to end castling movement for King in question
	 * @return determination of whether castling is legal for King in question
	 */
	public boolean castle(Point source, Point destination){
		if(!isHasMoved() && source.x == destination.x && (source.y - 2 == destination.y || source.y + 2 == destination.y)){
			return true;
		}
		return false;
	}

	/**
	 * method always returns false because king cannot put another king in check
	 * therefore this method is unneeded for determining whether or not another
	 * piece is blocking the check of this king
	 */
	@Override
	public boolean inTheWay(HashMap<Point, Square> squares, Point kingPos) {
		return false;
	}

}

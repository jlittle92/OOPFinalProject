package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public class Pawn extends Piece{
	
	private final int MOVE = 1, JUMP = 2;
	private boolean hasMoved, jumpWasLastMove;

	public Pawn(int x, int y, Team t, PieceType type) {
		super(x, y, t, type);
		setHasMoved(false);
		setJumpWasLastMove(false);
	}

	@Override
	public boolean isLegalForPiece(Point source, Point destination) {
		if(getTeam() == Team.WHITE){
			if(!isHasMoved()){
				if((source.x - JUMP == destination.x && source.y == destination.y) || (source.x - MOVE == destination.x && source.y == destination.y)){
					setHasMoved(true);
					setJumpWasLastMove(true);
					return true;
				}
				else{
					return false;
				}
			}
			else if(source.x - MOVE == destination.x && source.y == destination.y){
				setJumpWasLastMove(false);
				return true;
			}
			return false;
		}
		else{
			if(!isHasMoved()){
				if((source.x + JUMP == destination.x && source.y == destination.y) || (source.x + MOVE == destination.x && source.y == destination.y)){
					setHasMoved(true);
					setJumpWasLastMove(true);
					return true;
				}
				else{
					return false;
				}
			}
			else if(source.x + MOVE == destination.x && source.y == destination.y){
				setJumpWasLastMove(true);
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

	@Override
	public boolean killLogic(Point kingPos) {
		if(getTeam() == Team.WHITE){
			if(getPosition().x - MOVE == kingPos.x){
				if(getPosition().y - MOVE == kingPos.y || getPosition().y + MOVE == kingPos.y){
					return true;
				}
			}
		}
		else if(getTeam() == Team.BLACK){
			if(getPosition().x + MOVE == kingPos.x){
				if(getPosition().y - MOVE == kingPos.y || getPosition().y + MOVE == kingPos.y){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean enPassant(Point destination){
		boolean justDoIt = false;
		if(getTeam() == Team.WHITE){
			if(getPosition().x - MOVE == destination.x){
				if(getPosition().y + MOVE == destination.y || getPosition().y - MOVE == destination.y){
					justDoIt = true;
				}
			}
		}
		else if(getTeam() == Team.BLACK){
			if(getPosition().x + MOVE == destination.x){
				if(getPosition().y + MOVE == destination.y || getPosition().y - MOVE == destination.y){
					justDoIt = true;
				}
			}
		}
		return justDoIt;
	}

	public boolean isJumpWasLastMove() {
		return jumpWasLastMove;
	}

	public void setJumpWasLastMove(boolean jumpWasLastMove) {
		this.jumpWasLastMove = jumpWasLastMove;
	}

	@Override
	public boolean inTheWay(HashMap<Point, Square> squares, Point kingPos) {
		if(killLogic(kingPos)){
			return false;
		}
		return true;
	}

}

package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;
import java.util.HashMap;

public abstract class Piece extends ImageComponent{

	private Point position;
	private String source;
	private Team team;
	private PieceType type;
	
	public Piece(int x, int y, Team t, PieceType type){
		Point p = new Point(x, y);
		setPosition(p);
		setSource(t, type.toString());
		setTeam(t);
		setType(type);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getSource() {
		return source;
	}

	public void setSource(Team t, String source) {
		String src = "/" + source;
		this.source = "/edu/neumont/csc150/finalproject/littlej/" + t.toString() + src + ".png";
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean move(Point source, Point destination) {
		if(isValid(source, destination)){
			setPosition(destination);
			return true;
		}
		else{
			System.out.println("That's an invalid move. Try again.");
			return false;
		}
	}
	
	public boolean isValid(Point source, Point destination) {
		if(!isPathBlocked(source, destination) && isLegalForPiece(source, destination)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isPathBlocked(Point source, Point destination) {
		return false;
	}
	
	public abstract boolean isLegalForPiece(Point source, Point destination);
	
	public abstract boolean inTheWay(HashMap<Point, Square> squares, Point kingPos);

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}
	
	public boolean legalPawnCap(Piece source, Piece destination){
		if(source.getTeam().toString().equals(Team.WHITE.toString())){
			if(source.getPosition().x - 1 == destination.getPosition().x){
				if(source.getPosition().y - 1 == destination.getPosition().y || source.getPosition().y + 1 == destination.getPosition().y){
					return true;
				}
			}
		}
		else if(source.getTeam().toString().equals(Team.BLACK.toString())){
			if(source.getPosition().x + 1 == destination.getPosition().x){
				if(source.getPosition().y - 1 == destination.getPosition().y || source.getPosition().y + 1 == destination.getPosition().y){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean killLogic(Point kingPos){
		if(isLegalForPiece(getPosition(), kingPos)){
			return true;
		}
		return false;
	}
	
}

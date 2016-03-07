package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public abstract class Piece extends ImageComponent{

	private Point position;
	private String source;
	private Team team;
	
	public Piece(int x, int y, Team t, String pieceName){
		Point p = new Point(x, y);
		setPosition(p);
		setSource(t, pieceName);
		setTeam(t);
		System.out.println(getPosition());
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
}

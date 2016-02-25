package edu.neumont.csc150.finalproject.littlej;

import java.awt.Point;

public class Piece extends ImageComponent{

	private Point position;
	private String source;
	
	public Piece(int x, int y, Team t, String pieceName){
		Point p = new Point(x, y);
		setPosition(p);
		setSource(t, pieceName);
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
}
